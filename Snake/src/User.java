
public class User {
	
	private String name;
	private String level;
	
	public User(String name, String level)
	{
		this.name = name;
		this.level = level;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLevel()
	{
		return level;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setLevel(String level)
	{
		this.level = name;
	}
	
	public String toString()
	{
		return this.name + " " + this.level;
	}

}
