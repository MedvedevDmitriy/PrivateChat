package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestClientImpl
{
	Socket socket;
	DataInputStream in;
	DataOutputStream out;


	public TestClientImpl(String host, int port)
	{
		try
		{
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			Scanner sc = new Scanner(System.in);
			new Receiver();
			boolean bFlag = true;
			while ( bFlag )
			{
				String line = sc.nextLine();
				out.writeUTF(line);
				out.flush();
				if ( line.equals("quit:") )
					bFlag = false;
				
			}
			socket.close();
			sc.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	class Receiver implements Runnable
	{

		public Receiver()
		{
			new Thread(this).start();
		}

		@Override
		public void run()
		{
			try
			{
				while ( !socket.isClosed() )
				{
					if (in.available() > 0)
					{
						System.out.println(in.readUTF());
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}