package profile;

import java.util.ArrayList;
import java.util.Map;

public interface Profiler
{
	public void create (Profile user);
	public ArrayList<Profile> read();
	public void update(Profile user);
	public void delete(Profile user);
	public void updateChannels(String name, Map<String, Integer> channels);
}