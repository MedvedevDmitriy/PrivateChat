package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionList
{
	private static List<Connection> lst;
	
	public static List<Connection> getI()
	{
		if (lst == null)
		{
			lst = Collections.synchronizedList(new ArrayList<>());
		}
		return lst;
	}
	
	public static void remove(Connection con)
	{
		synchronized (getI())
		{
			lst.remove(con);
		}
	}
	
	public static Connection getCon(String name)
	{
		Connection res = null;
		
		for(Connection con : lst)
		{
			if (con.profile.getLogin().equals(name))
			{
				res = con; break;
			}
		}
		
		return res;
	}
}