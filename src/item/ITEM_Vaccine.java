package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_Vaccine extends Item {

	public ITEM_Vaccine(int worldX, int worldY) {

		super(worldX, worldY);

		this.name = "Vaccine";
		try {

			this.itemImage = ImageIO.read(getClass().getResourceAsStream("/items/vaccine.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}
