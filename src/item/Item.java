package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Item {

	public BufferedImage shadowImage;
	public BufferedImage itemImage;
	public String name;
	public boolean isCollideable = false;
	public boolean isCollectible = true;
	public int worldX, worldY;

	public int hitBoxDefaultX = 6;
	public int hitBoxDefaultY = 6;
	public int hitBoxDefaultWidth = 45;
	public int hitBoxDefaultHeight = 45;

	public Rectangle hitBox = new Rectangle(hitBoxDefaultX, hitBoxDefaultY, hitBoxDefaultWidth, hitBoxDefaultHeight);

	public Item(int worldX, int worldY) {

		this.worldX = worldX;
		this.worldY = worldY;

		try {

			this.shadowImage = ImageIO.read(getClass().getResourceAsStream("/items/item_shadow.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void draw(GamePanel gp, Graphics2D g2) {

		int screenX = this.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = this.worldY - gp.player.worldY + gp.player.screenY;

		if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth && screenY > 0 - gp.tileSize
				&& screenY < gp.screenHeight) {

			g2.drawImage(shadowImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
			g2.drawImage(itemImage, screenX, screenY, gp.tileSize, gp.tileSize, null);

		}
	}
}
