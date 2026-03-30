package tile;
import java.awt.image.BufferedImage;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[] [];
	private BufferedImage TREE;

	// each entry is {col,row} of the TOP-LEFT tile of a 5x5 tree
	public int[][] treeAnchors = {
	    {0, 0}
	};

	int maxWorldCol;
	int maxWorldRow;
	

	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[50];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap ();
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassgreen.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/watershade.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
			tile[2].collision = true;
		
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pondup.png"));
			tile[3].collision = true;
		
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));
			tile[4].collision = true;

			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fullblue.png"));
			tile[5].collision = true;
			

			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/PATHRIGHT.png"));
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/DIRT.png"));
			

			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pathleft.png"));
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/waterdirt.png"));
			tile[14].collision = true;
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt3.png"));
			tile[15].collision = true;
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/middle.png"));
			tile[16].collision = true;
	
			TREE = ImageIO.read(getClass().getResourceAsStream("/tiles/TREE.png"));

		}catch(IOException e) {
		e.printStackTrace();
		
	    }
	}
	public void loadMap() {
	    try {
	        InputStream is = getClass().getResourceAsStream("/maps/world.txt");
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
	        int col = 0;
	        int row = 0;
	       
	        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
	        	String line = br.readLine ();
	        	
	        	while (col < gp.maxWorldCol)  {
	            
	        		String numbers [] = line.split(" ");
	        	
	        	int num = Integer.parseInt(numbers[col]);
	        	
	        	mapTileNum[col] [row] = num;
	        	col++;
	        }
	        if(col == gp.maxWorldCol) {
	        	col = 0;
	        	row++;

	            }
	        }

	        br.close();
	    } catch (Exception e) {
	    
	    }
	}





	public void draw(Graphics2D g2) {

	  
	    int WorldCol = 0;
	    int WorldRow = 0;

	    while(WorldCol < gp.maxWorldCol && WorldRow< gp.maxWorldRow) {
	    	
	    	int tileNum = mapTileNum[WorldCol] [WorldRow];
	    	
	    	int worldX = WorldCol * gp.tileSize;
	    	int worldY = WorldRow * gp.tileSize;
	    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    	int screenY = worldY - gp.player.worldY + gp.player.screenY;
	    	
	    	if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
	    			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	    			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	    			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
	        	g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	    	}
	    	
	    	WorldCol++;
	    	
                if(WorldCol == gp.maxWorldCol) {
                	WorldCol = 0;
                	WorldRow++;
	        	
	        }
	    }
	
	// DRAW BIG 5x5 TREE AFTER TILES
	for (int i = 0; i < treeAnchors.length; i++) {

	    int col = treeAnchors[i][0];
	    int row = treeAnchors[i][1];

	    int worldX = col * gp.tileSize;
	    int worldY = row * gp.tileSize;

	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;

	    int size = gp.tileSize * 5;

	    g2.drawImage(TREE, screenX, screenY, size, size, null);
	}

	}
}
	   
	   