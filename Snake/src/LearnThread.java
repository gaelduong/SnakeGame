
// implements runnable, method run(), thread sleep, thread start

public class LearnThread implements Runnable{
	
	String name;
	int time;
	
	public LearnThread(String s, int t)
	{
		name = s;
		time = t;
	}
	
	
	
	public void run()
	{
		System.out.println("yo" + name);
		try 
		{
			Thread.sleep(time);
		} catch (InterruptedException e) {}
		
		System.out.println(name + " is done");
	}

}

/*display image
Image img = null;
try
{
	img = ImageIO.read(new File("res/snake_head.png"));
	g.drawImage(img, 0, 0, null);
}catch(Exception e) {}

*/
