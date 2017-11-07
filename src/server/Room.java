package server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room
{
	private String roomName;
	List<String> roomQueue = Collections.synchronizedList(new ArrayList<>());
	List<Connection> usersList = Collections.synchronizedList(new ArrayList<>());
	Sender roomSender = new Sender(roomQueue, usersList, roomName);
	
	public Room(String roomName)
	{
		this.roomName = roomName;
	}
	
	public String getName()
	{
		return roomName;
	}
	
	public void addUser(Connection con)
	{
		synchronized (usersList)
		{
			usersList.add(con);
			con.profile.currentRoom = roomName;
		}
	}
	
	public void removeUser(Connection con)
	{
		synchronized (usersList)
		{
			usersList.remove(con);
			con.profile.currentRoom = "";
		}
	}
	
	public List<Connection> getUsersList()
	{
		synchronized (usersList)
		{
			return usersList;
		}
	}
}