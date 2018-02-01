
public class playThread {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new LearnThread(" bro", 2000));
		Thread t2 = new Thread(new LearnThread(" man",1000));
		
		t1.start();
		
		t2.start();
		
		
			
		
		
		
	}

}
