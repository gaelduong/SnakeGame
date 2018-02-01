import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

public class conRan {
	int dodai = 3;
	int[] x;
	int[] y;
	long t1 = 0;
	long t2 = 0;
	int speed = 200;
	int maxLen = 7;
	
	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;
	
	
	int vector = conRan.GO_DOWN;
	
	public static boolean upAfterChangevt = true;

	
	public conRan()
	{
		x = new int[400];
		y = new int[400];
		x[0] = 5;
		y[0] = 4;
		
		x[1] = 5;
		y[1] = 3;
		
		x[2] = 5;
		y[2] = 2;
		GameScreen.bg[10][10] = 2;
	}
	public void resetGame()
	{
		x = new int[400];
		y = new int[400];
		
		x[0] = 5;
		y[0] = 4;
		
		x[1] = 5;
		y[1] = 3;
		
		x[2] = 5;
		y[2] = 2;
		this.dodai = 3;
		vector = conRan.GO_DOWN;
		
		
	}
	
	public void setVector(int v)
	{
		if(this.vector != -v &&  upAfterChangevt) 
			{
				this.vector = v;
				 upAfterChangevt = false;
			}
	}
	
	public boolean sameSnakeXY(int x1, int y1)
	{
		for(int i = 0; i < dodai; i++)
		{
			if(x[i] == x1 && y[i] == y1) return true;
		}
		return false;
	}
	
	public Point getPrey()
	{
		Random r = new Random();
		
		int x;
		int y;
		do
		{
			x = r.nextInt(19);
			y = r.nextInt(19);
			
		}
		while(sameSnakeXY(x,y));
		return new Point(x,y);
	}
	
	public int getCurrentSpeed()
	{
		int speed = 200;
		for(int i = 0; i < GameScreen.currentLevel; i++)
		{
			speed *= 0.8;
		}
		return speed;
	}
	
	public void update()
	{
		if(dodai == maxLen)
		{
			GameScreen.isPlaying = false;
			resetGame();
			GameScreen.currentLevel++;
			maxLen += 5;
			speed = getCurrentSpeed();
		}
		
		for(int i = 1; i <dodai; i++)
		{
			if(x[0] == x[i] && y[0] == y[i])
			{
				String name = JOptionPane.showInputDialog("Enter your name: ");
				RanAnMoi.users.add(new User(name, (String.valueOf(GameScreen.currentLevel))));
				
				GameScreen.isPlaying = false;
				GameScreen.isGameOver = true;
				GameScreen.score = 0;
				GameScreen.currentLevel = 1;
			}
		}
		
		if(System.currentTimeMillis() - t2 > 200)
		{
			upAfterChangevt = true;
			Data.HeadGoUp.update();
			Data.HeadGoDown.update();
			Data.HeadGoLeft.update();
			Data.HeadGoRight.update();
			
			t2 = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - t1 > speed)
		{
			
			if(GameScreen.bg[x[0]][y[0]] == 2) 
			{
				this.dodai++;
				GameScreen.score++;
				GameScreen.bg[x[0]][y[0]] = 0;
				GameScreen.bg[getPrey().x][getPrey().y] = 2;
			}
			
			for(int i = dodai - 1; i > 0; i--)
			{
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			
			if(this.vector == conRan.GO_UP) y[0]--;
			else if(this.vector == conRan.GO_DOWN) y[0]++;
			else if(this.vector == conRan.GO_LEFT) x[0]--;
			else if(this.vector == conRan.GO_RIGHT) x[0]++;
			
			if(x[0] < 0) x[0] = 19;
			else if(x[0] > 19) x[0] = 0;
			if(y[0] < 0) y[0] = 19;
			else if(y[0] > 19) y[0] = 0;
			
			t1 = System.currentTimeMillis();
		}
	}
	
	public void veRan(Graphics g)
	{
		int p = GameScreen.padding;
		g.setColor(Color.red);
		for(int i = 1; i < dodai; i++)
		{
			g.drawImage(Data.imageBody,x[i]*20+p, y[i]*20+p,null);
			
			if(this.vector==conRan.GO_UP) g.drawImage(Data.HeadGoUp.getCurrentImage(),x[0]*20-6+p,y[0]*20-6+p,null);
			else if(this.vector==conRan.GO_DOWN) g.drawImage(Data.HeadGoDown.getCurrentImage(),x[0]*20-6+p,y[0]*20-6+p,null);
			else if(this.vector==conRan.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(),x[0]*20-6+p,y[0]*20-6+p,null);
			else if(this.vector==conRan.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(),x[0]*20-6+p,y[0]*20-6+p,null);
		}
	}

}
