package server;

import java.io.IOException;

public class GlobalSender extends Thread
{
	public GlobalSender()
	{
		start();
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				if (!MsgQueue.getI().isEmpty())
				{
					String str = MsgQueue.getI().remove();
					synchronized (ConnectionList.getI())
					{
						for (Connection con: ConnectionList.getI())
						{
							con.send(str);
						}
					}
				}
				Thread.sleep(10);
			} 
		}
		catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
	}
}