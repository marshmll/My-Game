package item;

import java.awt.Graphics2D;

import main.GamePanel;

public class ItemsManager {

	GamePanel gp;
	public Item items[] = new Item[10];

	public ItemsManager(GamePanel gp) {
		this.gp = gp;
	}

	public void createMapitems() {

		items[0] = new ITEM_Key(96 * gp.tileSize, 49 * gp.tileSize);
		items[1] = new ITEM_Knife(98 * gp.tileSize, 49 * gp.tileSize);
		items[2] = new ITEM_Pistol(99 * gp.tileSize, 49 * gp.tileSize);
		items[3] = new ITEM_Vaccine(95 * gp.tileSize, 49 * gp.tileSize);
		items[4] = new ITEM_Pistol(98 * gp.tileSize, 50 * gp.tileSize);
		items[5] = new ITEM_Key(99 * gp.tileSize, 50 * gp.tileSize);
		items[6] = new ITEM_Vaccine(100 * gp.tileSize, 50 * gp.tileSize);
		items[7] = new ITEM_Pistol(96 * gp.tileSize, 50 * gp.tileSize);
		items[8] = new ITEM_Pistol(100 * gp.tileSize, 49 * gp.tileSize);
		items[9] = new ITEM_Pistol(101 * gp.tileSize, 49 * gp.tileSize);
	}

	public void draw(Graphics2D g2) {

		for (int i = 0; i < items.length; i++) {

			if (items[i] != null) {

				items[i].draw(this.gp, g2);
			}
		}
	}
}
