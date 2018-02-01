
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GameScreen extends JPanel implements Runnable{
	
	static int[][] bg = new int[20][20];
	
	static int padding = 10;
	static int WIDTH = 400;
	static int HEIGHT = 400;
	static boolean isPlaying = false;
	static boolean enableTextGame = true;
	static boolean isGameOver = false;
	
	conRan ran;
	
	Thread thread;
	
	static int currentLevel = 1;
	static int score = 0;
	
	public GameScreen()
	{
		ran = new conRan();
		Data.loadImage();
		Data.loadAllAnimn();
		
		thread = new Thread(this);
		
		thread.start();
	
	}
	
	
	/*Run*/
	public void run()
	{
		long t = 0;
		long tText = 0;
		while(true)
		{
			if(System.currentTimeMillis() - tText > 300)
			{
				enableTextGame = !enableTextGame;
				tText = System.currentTimeMillis();
			}
			
			if(isPlaying)
			{
				if(System.currentTimeMillis() - t > 200)
				{
					Data.Worm.update();
					t = System.currentTimeMillis();
				}
				ran.update();
			}
			repaint();
			
			
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {}
			
		}
	}
	
	/*Paint*/
	public void paint(Graphics g)
	{
		//g.setColor(Color.blue);
		//.fillRect(0, 0, 500, 500);
		paintBg(g);
		ran.veRan(g);
		veKhung(g);
		
		if(!isPlaying && !isGameOver)
		{
			if(enableTextGame)
			{
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(18.0f));
				g.drawString("PRESS SPACE TO PLAY!", 110, 200);
			}
		}
		
		else if(isGameOver)
		{
				
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(18.0f));
				g.drawString("GAME OVER!", 110, 200);
		}
		
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString("LEVEL: " + currentLevel, 450, 50);
		
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString("Score: " + score, 450, 100);
		
		
		for(int i=0; i<RanAnMoi.users.size();i++)
		{
			g.drawString(RanAnMoi.users.get(i).toString(), 450, i*30 + 150);
		}
		
		
		
	}
	
	private void veKhung(Graphics g)
	{
		g.setColor(Color.orange);
		g.drawRect(0, 0, WIDTH+padding*2+300, HEIGHT+padding*2);
		g.drawRect(1, 1, WIDTH+padding*2-2+300, HEIGHT+padding*2-2);
		g.drawRect(2, 2, WIDTH+padding*2-4+300, HEIGHT+padding*2-4);
		
		g.setColor(Color.orange);
		g.drawRect(0, 0, WIDTH+padding*2, HEIGHT+padding*2);
		g.drawRect(1, 1, WIDTH+padding*2-2, HEIGHT+padding*2-2);
		g.drawRect(2, 2, WIDTH+padding*2-4, HEIGHT+padding*2-4);
	}
	
	public void paintBg(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH+padding*2+300, HEIGHT+padding*2);
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				
				//if(bg[i][j] == 2) g.setColor(Color.yellow);
				//g.fillRect(i*20+1, j*20+1, 18, 18);
				if(bg[i][j] == 2) 
				{
					
					g.drawImage(Data.Worm.getCurrentImage(), i*20-7+padding, j*20-7+padding,null);
					//g.setColor(Color.gray);
					
				}
			}
		}
	}

}
