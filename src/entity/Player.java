package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
    public  int screenX;
    public  int screenY;
	private double playerScale = 1;   // 1.0 = same as tile. 1.25 = 25% bigger
    private int drawWidth;
    private int drawHeight;
    
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
	    this.keyH = keyH;
	    
	    
	    drawWidth = (int)(gp.tileSize * playerScale);
	    drawHeight = (int)(gp.tileSize * playerScale);

	    solidArea = new Rectangle () ;
	    solidArea.x = 9;
	    solidArea.y = 16;
	    solidArea.width = 32;
	    solidArea.height = 32;
	    setDefaultValues();
	    getPlayerImage();
	}
	
	public void setDefaultValues() {
	
		worldX = gp.tileSize * 15;
		worldY = gp.tileSize * 5;
		speed = 5;
		direction = "down";
	}
	public void getPlayerImage() {
		
		try {
		
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-7.png (1).png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-8.png (2).png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-5.png.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-6.png.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-7.png (2).png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-10.png.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-9.png.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-10.png.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-2.png (3).png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-5.png (2).png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-4.png (3).png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/GUINEA PIG-5.png (2).png"));
		
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		screenX = gp.getWidth() / 2 - gp.tileSize / 2;
		screenY = gp.getHeight() / 2 - gp.tileSize / 2;

		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				
			}
		    else if(keyH.leftPressed == true) {
		    	direction = "left";
		    	
		    }
		    else if(keyH.rightPressed == true) {
		    	direction = "right";
				
			}
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// IF COLLISION IS FALSE PLAYER CAN MOVE
			if (collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case"down":
					worldY += speed;

					break;
				case "left":
					worldX -= speed; 
					break;
				case "right":
					worldX += speed; 
					break;
				}
				
				
			}
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
					
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				} 
				spriteCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":    image = (spriteNum == 1) ? up1 : up2; break;
            case "down":  image = (spriteNum == 1) ? down1 : down2; break;
            case "left":  image = (spriteNum == 1) ? left1 : left2; break;
            case "right": image = (spriteNum == 1) ? right1 : right2; break;
        }

        int offsetX = (drawWidth - gp.tileSize) / 2;
        int offsetY = (drawHeight - gp.tileSize) / 2;

        g2.drawImage(image, screenX - offsetX, screenY - offsetY, drawWidth, drawHeight, null);
       
    }
	}
	