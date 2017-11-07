package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import profile.Profile;
import profile.ProfileList;

public class Connection
{
	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;
	Profile profile = null;
	
	public Connection(Socket socket)
	{
		try
		{
			this.socket = socket;
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void linkProfile(String login, String password)
	{
		Profile tmp = ProfileList.searchProfile(login);
		
		if(tmp != null)
		{
			System.out.println("User was being");
			if(tmp.getPassword().equals(password))
			{
				profile = tmp;
				profile.setActive(true);
			}
		}
		else
		{
			System.out.println("User wasn't being!");
			profile = new Profile(login, password);
			ProfileList.addProfile(profile);
			profile.setActive(true);
		}
	}

	public synchronized void send(String message) throws IOException
	{
		out.writeUTF(message);
		out.flush();
	}
	
	public void close()
	{
		try
		{
			socket.close();
			in.close();
			out.close();
			profile.setActive(false);
			ConnectionList.remove(this);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}