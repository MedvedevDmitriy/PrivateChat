package server;

import java.io.IOException;
import java.util.List;

public class Sender extends Thread
{
	List<String> queue = null;
	List<Connection> list = null;
	private String roomName = "";
	private int counter = 0;

	public Sender(List<String> queue, List<Connection> list, String roomName)
	{
		this.queue = queue;
		this.list = list;
		this.roomName = roomName;
		start();
	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				if (counter < queue.size())
				{
					synchronized (queue)
					{
						for(int i = counter; i < queue.size(); i++)
						{
							int countMsg = 0;
							String message = queue.get(i);
							synchronized (list)
							{
								for(Connection con: list)
								{
									countMsg = con.profile.history.get(roomName);
									if (con.profile.currentRoom.equals(roomName))
									{
										if (countMsg == 0)
										{
											con.send(message);
										}
										else
										{
											for (int j = queue.size() - countMsg; j <= i; j++)
											{
												con.send(queue.get(j));
											}
											con.profile.history.replace(roomName, 0);
										}
									}
									else
									{
										int a = countMsg + queue.size() - counter;
										con.profile.history.replace(roomName, a);
										con.send("history:" + roomName + "," + a);
									}
								}
								counter++;
							}
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