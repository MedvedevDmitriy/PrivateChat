package profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileList
{
	private static List<Profile> lst;
	
	public static List<Profile> getI()
	{
		if (lst == null)
		{
			lst = Collections.synchronizedList(new ArrayList<>());
		}
		return lst;
	}
	
	public static Profile searchProfile(String login)
	{
		Profile ret = null;
		
		for(Profile profile: lst)
		{
			if(profile.getLogin().equals(login))
			{
				ret = profile; break;
			}
		}
		
		return ret;
	}
	
	public static void addProfile(Profile profile)
	{
		lst.add(profile);
	}
	
	public static List<Profile> updateListOfProfiles()
	{
		Profiler_MySQL profileMySQL = new Profiler_MySQL();
		lst = profileMySQL.read();
		return lst;
	}
	
//	public static List<Profile> updateListOfProfiles()
//	{
//		Profiler_Mock profileMock = new Profiler_Mock();
//		lst = profileMock.read();
//		return lst;
//	}
}