package server;
import java.io.IOException;
import java.net.ServerSocket;

import profile.ProfileList;

public class Server extends Thread
{
	private ServerSocket serverSocket;
	private static final int PORT = 7777;

	public Server()
	{	
		ProfileList.updateListOfProfiles();
		new GlobalReceiver();
		start();
	}
	
	@Override
	public void run()
	{
		try
		{
			System.out.println("Server started on port " + PORT + "!");
			serverSocket = new ServerSocket(PORT);
			while (true)
			{
				Connection con = new Connection(serverSocket.accept());
				System.out.println("Connection was added from " + con.socket.getInetAddress().getHostName() + "!");
				synchronized (ConnectionList.getI())
				{
					ConnectionList.getI().add(con);
				}
			}
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}