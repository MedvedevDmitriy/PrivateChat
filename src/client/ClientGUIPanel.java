package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ClientGUIPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	User user;

	JTextField msg;

	DefaultListModel<String> chatModel;
	DefaultListModel<String> allUsersModel;
	JList<String> chat_list;
	JList<String> all_users_list;

	public ClientGUIPanel()
	{
		setLayout(null);
		setSize(600,400);
		setBackground(Color.CYAN);

		Thread guiReciever = new Thread(new GUIReciever());
		guiReciever.start();
		
		loginUser();

		chatModel = new DefaultListModel<>();
		allUsersModel = new DefaultListModel<>();
		chat_list = new JList<String>(chatModel);
		all_users_list = new JList<>(allUsersModel);

		chat_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		all_users_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane chatScroller = new JScrollPane(chat_list);
		JScrollPane allUsersScroller = new JScrollPane(all_users_list);

		chatScroller.setBounds(230, 20, 200, 160);
		allUsersScroller.setBounds(20, 20, 200, 160);

		add(chatScroller);
		add(allUsersScroller);


		JButton bStart = new JButton("Join");
		JButton bSend = new JButton("Send");
		JButton bExit = new JButton("Exit");
		bStart.setBounds(20, 160, 200, 20);
		bSend.setBounds(230, 220, 200, 20);
		bExit.setBounds(440, 130, 100, 20);
		bStart.addActionListener(new ActionStart());
		bSend.addActionListener(new ActionSend());
		bExit.addActionListener(new ActionExit());

		add(bStart);
		add(bSend);
		add(bExit);

		msg = new JTextField();
		msg.setBounds(230,190,200,20);
		add(msg);
	}

	private void loginUser()
	{
		try
		{
			String login = JOptionPane.showInputDialog(null, "Enter your login:",
					"Authorization Part 1", JOptionPane.QUESTION_MESSAGE);
			String password = JOptionPane.showInputDialog(null, "Enter your password:",
					"Authorization Part 2", JOptionPane.QUESTION_MESSAGE);

			user = User.createUser();
			user.login(login,password);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	class ActionStart implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String selected_user = all_users_list.getSelectedValue();
				if (selected_user != null)
					user.private_room(selected_user);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	class ActionSend implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				user.sendMsg(msg.getText());
				msg.setText("");
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	class ActionExit implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				user.exit();
				System.exit(0);
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	class GUIReciever implements Runnable
	{
		@Override
		public void run()
		{
			while(true)
			{
				try
				{
					if (user == null)
						continue;
					
					String str = user.getMsg();
					if (!str.equals(""))
					{
						String cmd = str.substring(0,str.indexOf(":"));
						String msg = str.substring(str.indexOf(":") + 1);
						switch(cmd)
						{
							case "msg":
								chatModel.addElement(msg);
								break;
	
							case "users":
								String[] all_users = msg.split(",");
								allUsersModel.clear();
								for (String user : all_users)
									allUsersModel.addElement(user);
								break;
	
							case "login":
								if (!msg.equals("ok"))
								{
									String name = JOptionPane.showInputDialog(null, "Enter your nickname:",
											"New user detected!", JOptionPane.QUESTION_MESSAGE);
	
									user.newProfile(name);
								}
								else if (msg.equals("ok"))
									user.getUsers();
								else
								{
									JOptionPane.showMessageDialog(null, "Relogin, please!", "Error!", JOptionPane.ERROR_MESSAGE);
									loginUser();
								}
								break;

							case "error":
								JOptionPane.showMessageDialog(null, msg, "Œ¯Ë·Í‡!", JOptionPane.ERROR_MESSAGE);
								break;
								
							case "privateroom":
								String[] user_msg = msg.split(",");
								chatModel.clear();
								for (String hmsg : user_msg)
									chatModel.addElement(hmsg);
								break;
						}
					}


					Thread.sleep(100);
				}
				catch (InterruptedException | IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}