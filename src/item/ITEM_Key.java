package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_Key extends Item {

	public ITEM_Key(int worldX, int worldY) {

		super(worldX, worldY);

		this.name = "Key";
		try {

			this.itemImage = ImageIO.read(getClass().getResourceAsStream("/items/key.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}
