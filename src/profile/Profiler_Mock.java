package profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profiler_Mock implements Profiler
{
	ArrayList<UserMock> users;
	ArrayList<RoomMock> rooms;
	int increment = 0;
	
	class UserMock
	{
		int id;
		String login;
		String password;
		boolean status;
		
		public UserMock(int id, String login, String password, boolean status)
		{
			this.id = id;
			this.login = login;
			this.password = password;
			this.status = status;
		}
	}
	
	class RoomMock
	{
		int id;
		String roomName;
		int unreceived;
		int userId;
		
		public RoomMock(int id, String roomName, int unreceived, int userId)
		{
			this.id = id;
			this.roomName = roomName;
			this.unreceived = unreceived;
			this.userId = userId;
		}
	}
	
	public Profiler_Mock()
	{
		users = new ArrayList<UserMock>();
		rooms = new ArrayList<RoomMock>();
		users.add(new UserMock(++increment, "Vasia", "1", false));
		users.add(new UserMock(++increment, "Masha", "2", false));
		users.add(new UserMock(++increment, "Petya", "3", false));
		users.add(new UserMock(++increment, "Vitya", "4", false));
		
	}
	
	@Override
	public void create ( Profile profile )
	{
		UserMock profileMock = new UserMock(++increment, profile.getLogin(), profile.getPassword(), profile.isActive());
		users.add(profileMock);
	}
	
	@Override
	public ArrayList<Profile> read()
	{
		ArrayList<Profile> ret = new ArrayList<Profile>();
		for (UserMock userMock : users)
		{
			Profile profile = new Profile(userMock.login, userMock.password, false);
			Map<String, Integer> subscription = new HashMap<String, Integer>();
			for (RoomMock roomMock : rooms)
			{
				if ( roomMock.userId == userMock.id )
				{
					subscription.put(roomMock.roomName, 0);
				}
			}
			profile.setHistory(subscription);
			ret.add(profile);
		}
		return ret;
	}
	
	@Override
	public void update( Profile profile )
	{
		for (UserMock profileMock : users)
		{
			if ( profileMock.login.equals(profile.getLogin()) )
			{
				profileMock.password = profile.getPassword();
				profileMock.status = profile.isActive();
			}
		}
	}
	
	@Override
	public void delete( Profile user )
	{
		for (UserMock userMock : users)
		{
			if ( userMock.login.equals(user.getLogin()) )
			{
				users.remove(userMock);
				break;
			}
		}
	}

	@Override
	public void updateChannels(String name, Map<String, Integer> channels)
	{
		for (UserMock user: users)
		{
			if (user.login.equals(name))
			{
				for (String entry : channels.keySet())
				{
					
				}
				break;
			}
		}
	}
}