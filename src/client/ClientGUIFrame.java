package client;

import javax.swing.JFrame;

public class ClientGUIFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public ClientGUIFrame()
	{
		setTitle("Chat with Rooms");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50, 50, 600, 400);
		add(new ClientGUIPanel());
		setVisible(true);
	}
}