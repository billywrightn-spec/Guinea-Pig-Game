package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	
 // SCREEN SETTINGS
	public final int originalTileSize = 32; // 32x32 tile
	public final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16; 
	public final int maxScreenRow = 9;
	public final int screenWidth = tileSize * maxScreenCol; // 1536 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 864 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public int cameraX, cameraY;

	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this,keyH);
	
	
	public GamePanel()  {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread () {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.01666seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		
		while(gameThread != null) {
			
		update();
		
		repaint();
		
		//player speed 
		
		try {
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime = remainingTime/10000000;
			
			if(remainingTime < 0) {
				remainingTime = 0;
			}
			
			Thread.sleep((long) remainingTime);
			
			nextDrawTime += drawInterval;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	public void update() {
		
		
		player.update();
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;

	    tileM.draw(g2);
	    player.draw(g2);

	    g2.dispose();
	}



public int getScreenCols() {
    int w = getWidth();
    if (w <= 0) w = screenWidth; // fallback before window is shown
    return (int) Math.ceil(w / (double) tileSize);
}

public int getScreenRows() {
    int h = getHeight();
    if (h <= 0) h = screenHeight; // fallback before window is shown
    return (int) Math.ceil(h / (double) tileSize);
    

}
}
