package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_Knife extends Item {

	public ITEM_Knife(int worldX, int worldY) {

		super(worldX, worldY);

		this.name = "Knife";
		try {

			this.itemImage = ImageIO.read(getClass().getResourceAsStream("/items/knife.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
