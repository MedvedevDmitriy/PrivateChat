package profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profiler_MySQL implements Profiler
{
	private Connection connection;

	private void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	private void close()
	{
		try
		{
			connection.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
//	@Override
//	public void create(Profile profile) 
//	{
//		try
//		{
//			connect();
//			String command = "INSERT INTO CHAT.ROOMS (id, roomName) VALUES (?, ?)";
//			PreparedStatement st = connection.prepareStatement(command);
//			st.setInt(1, profile.id);
//			st.setString(2, profile.roomName);
//			st.execute();
//			close();
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public ArrayList<Profile> read() 
	{
		ArrayList<Profile> ret = new ArrayList<>();
		try
		{
			connect();
			Statement st = connection.createStatement();
			ResultSet rs1 = st.executeQuery("SELECT * from CHAT.USERS");
			while(rs1.next())
			{			
				ret.add(new Profile(rs1.getString(2), rs1.getString(3)));
			}
//			while(rs1.next())
//			{			
//				Profile profile = new Profile(rs1.getString(2), rs1.getString(3));
//				Map<String, Integer> subscription = new HashMap<String, Integer>();
//				ResultSet rs2 = st.executeQuery("SELECT * from CHAT.ROOMS");
//				while(rs2.next())
//				{	
//					if ( rs2.getInt(1) == rs1.getInt(1) )
//					{
//						subscription.put(rs2.getString(2), 0);
//					}
//				}
//				profile.setHistory(subscription);
//				ret.add(profile);
//			}
			close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void update(Profile user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Profile user)
	{
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void updateChannels(String name, Map<String, Integer> channels)
//	{
//		for (UserMock user: users)
//		{
//			if (user.login.equals(name))
//			{
//				for (String entry : channels.keySet())
//				{
//					
//				}
//				break;
//			}
//		}
//	}

	@Override
	public void create(Profile user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChannels(String name, Map<String, Integer> channels)
	{
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void update(Profile profile) 
//	{
//		try
//		{
//			connect();
//			String command = "UPDATE CHAT.ROOMS SET roomName=? WHERE id=?";
//			PreparedStatement st = connection.prepareStatement(command);
//			st.setString(1, profile.roomName);
//			st.setInt(2, profile.id);
//			st.execute();
//			close();
//		} 
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}

//	@Override
//	public void delete(Profile profile) 
//	{
//		try
//		{
//			connect();
//			String command = "DELETE FROM CHAT.ROOMS WHERE id=?";
//			PreparedStatement st = connection.prepareStatement(command);
//			st.setInt(1, profile.id);
//			st.execute();
//			close();
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
}