package prop;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Prop {

	public BufferedImage image;
	public String name;
	public boolean isCollideable = false;
	public int worldX, worldY;

	public int hitBoxDefaultX = 6;
	public int hitBoxDefaultY = 6;
	public int hitBoxDefaultWidth = 45;
	public int hitBoxDefaultHeight = 45;
	
	public Rectangle hitBox = new Rectangle(hitBoxDefaultX, hitBoxDefaultY, hitBoxDefaultWidth, hitBoxDefaultHeight);

	public void draw(Graphics2D g2, GamePanel gp) {

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth && screenY > 0 - gp.tileSize
				&& screenY < gp.screenHeight) {

			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

		}
	}
}
