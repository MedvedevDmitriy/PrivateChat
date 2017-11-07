package profile;
import java.util.ArrayList;
import java.util.Map;

public class Profile
{
	private String login;
	private String password;
	private boolean isActive;
	public Map<String, Integer> history;
	public String currentRoom;
	
	public Profile(String login)
	{
		this.login = login;
	}
	
	public Profile(String login, String password)
	{
		this.login = login;
		this.password = password;
	}
	
	public Profile(String login, String password, boolean isActive)
	{
		this.login = login;
		this.password = password;
		this.isActive = isActive;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public Map<String, Integer> getHistory()
	{
		return history;
	}
	
	public void setHistory(Map<String, Integer> history)
	{
		this.history = history;
	}
}