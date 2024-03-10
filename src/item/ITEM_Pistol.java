package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_Pistol extends Item {

	public ITEM_Pistol(int worldX, int worldY) {
		
		super(worldX, worldY);
		
		this.name = "Pistol";
		try {

			this.itemImage = ImageIO.read(getClass().getResourceAsStream("/items/pistol.png"));

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		this.isCollideable = true;
	}
}
