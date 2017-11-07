package server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rooms
{
	private static List<Room> lst;
	
	public static List<Room> getI()
	{
		if (lst == null)
		{
			lst = Collections.synchronizedList(new ArrayList<Room>());
		}
		return lst;
	}
	
	public static void addRoom(String roomName)
	{
		Room tmp = new Room(roomName);
		lst.add(tmp);
	}
	
	public static void removeRoom(String roomName)
	{
		Room tmp = findRoom(roomName);
		lst.remove(tmp);
	}
	
	public static Room findRoom(String roomName)
	{
		Room ret = null;
		
		for (Room room : lst)
		{
			if(room.getName().equals(roomName))
			{
				ret = room; break;
			}
		}
		
		return ret;	
	}
	
	public static String getRoomNames()
	{
		String ret = "";
		
		for (Room room : lst)
		{
			ret += room.getName() + " ,";
		}
		
		return ret;
	}
}