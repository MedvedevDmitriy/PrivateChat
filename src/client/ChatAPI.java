package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatAPI
{
	Socket cs = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	
	public ChatAPI() throws UnknownHostException, IOException
	{
		cs = new Socket("localhost", 7777);
		in = new DataInputStream(cs.getInputStream());
		out = new DataOutputStream(cs.getOutputStream());
	}

	public User createUser()
	{
		return null;
		//return new User();
	}
}
