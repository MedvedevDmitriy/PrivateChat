package server;
import java.io.IOException;

public class GlobalReceiver extends Thread
{	
	public GlobalReceiver()
	{
		start();
	}

	@Override
	public void run()
	{	
		try
		{
			while(true)
			{
				synchronized (ConnectionList.getI())
				{
					for(Connection con: ConnectionList.getI())
					{
						if(con.in.available() > 0)
						{
							String str = con.in.readUTF();
							String cmd = str.substring(0, str.indexOf(":"));
							String data = str.substring(str.indexOf(":") + 1);

							if (con.profile == null)
							{
								if (cmd.equals("login"))
								{
									String login = data.substring(0, data.indexOf(","));
									String password = data.substring(data.indexOf(",") + 1);
									con.linkProfile(login, password);
									System.out.println("Login was worked!");
								}
							}
							else
							{
								switch(cmd)
								{
								case "msg":
									Room currentRoom = Rooms.findRoom(con.profile.currentRoom);
									synchronized (currentRoom.roomQueue)
									{
										currentRoom.roomQueue.add(data);
									}
									break;
								case "exit":
									currentRoom = Rooms.findRoom(con.profile.currentRoom);
									currentRoom.removeUser(con);
									break;
								case "quit":
									con.close();
									break;
								case "joinDialog":
									String roomName = data.substring(data.indexOf(":") + 1);
									Rooms.findRoom(roomName).addUser(con);
									break;
								case "addDialog":
									String mateName = data.substring(data.indexOf(":") + 1);
									String myName = con.profile.getLogin();
									if (myName.compareTo(mateName) < 0)
									{
										roomName = myName + "-" + mateName;
									}
									else
									{
										roomName = mateName + "-" + myName;
									}
									Rooms.addRoom(roomName);
									Rooms.findRoom(roomName).addUser(con);
									Rooms.findRoom(roomName).addUser(ConnectionList.getCon(mateName));
									MsgQueue.getI().add("returnRooms:" + Rooms.getRoomNames());
									break;
								case "removeDialog":
									roomName = data.substring(data.indexOf(":") + 1);
									Rooms.removeRoom(roomName);
									break;
								default: break;
								}
							}
						}
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}