package client;

import java.io.IOException;

public class User
{
	//String name = "";
	ChatAPI cha;
	
	public User(ChatAPI cha)
	{ 
		this.cha = cha;
		//this.name = name;
	}
	
	public static User createUser()
	{
		ChatAPI cha = null;
		
		try
		{
			cha = new ChatAPI();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return new User(cha);
	}
	
	public void login(String login, String password) throws IOException
	{
		cha.out.writeUTF("login:" + login + "," + password);
	}
	public void sendMsg(String msg) throws IOException
	{
		cha.out.writeUTF("msg:" + msg);
	}
	public void exit() throws IOException
	{
		cha.out.writeUTF("exit:");
	}

	public String getMsg()
	{
		String result = "";
		
		try
		{
			if (cha.in.available() > 0)
				result = cha.in.readUTF();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}

	public void newProfile(String name) throws IOException
	{
		cha.out.writeUTF("newprofile:" + name);
	}

	public void private_room(String selected_user) throws IOException
	{
		cha.out.writeUTF("privateroom:" + selected_user);
	}

	public void getUsers() throws IOException
	{
		cha.out.writeUTF("users:");
	}

	/*public String getName()
	{
		return name;
	}*/
}
