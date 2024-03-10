package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_Chest extends Item {

	public ITEM_Chest(int worldX, int worldY) {

		super(worldX, worldY);

		this.name = "Chest";
		try {

			this.itemImage = ImageIO.read(getClass().getResourceAsStream("/items/chest.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		this.isCollideable = true;
		this.isCollectible = false;
	}
}
