import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class RanAnMoi extends JFrame {
	
	GameScreen gameScreen;
	
	public static ArrayList<User> users;
	
	public RanAnMoi()
	{
		setSize(750,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		users = new ArrayList<User>();
		ReadData();
		
		gameScreen = new GameScreen();
		add(gameScreen);
		
		this.addKeyListener(new handler());
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e)
			{
				UpdateData();
			}
		
		});
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args)
	{
		RanAnMoi f = new RanAnMoi();
		
	}
	
	private class handler implements KeyListener
	{
		

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			{
				GameScreen.isPlaying = !GameScreen.isPlaying; 
				if(GameScreen.isGameOver) 
					{
						
						GameScreen.isGameOver = false;
						gameScreen.ran.resetGame();
					}
			}
			if(GameScreen.isPlaying)
			{
			
			if(e.getKeyCode() == KeyEvent.VK_UP) gameScreen.ran.setVector(conRan.GO_UP);
			if(e.getKeyCode() == KeyEvent.VK_DOWN) gameScreen.ran.setVector(conRan.GO_DOWN);
			if(e.getKeyCode() == KeyEvent.VK_LEFT) gameScreen.ran.setVector(conRan.GO_LEFT);
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) gameScreen.ran.setVector(conRan.GO_RIGHT);
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}
	
	public static void UpdateData()
	{
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter("data/data.txt");
			bw = new BufferedWriter(fw);
			
			for(User u: users)
			{
				bw.write(u.getName() + " " + u.getLevel());
				bw.newLine();
			}
			
		} catch (IOException e) {}
		finally{
			try {
				bw.close();
			} catch (IOException e) {}
		}
		
		
		
	}
	
	public static void ReadData()
	{
		try {
			FileReader fr = new FileReader("data/data.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			while((line = br.readLine()) != null)
			{
				String[] str = line.split(" ");
				users.add(new User(str[0],str[1]));
			}
			
			br.close();
		} catch (IOException e) {}
	}

}
