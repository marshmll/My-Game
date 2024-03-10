package ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class UI_Hotbar extends UI_Frame {

	GamePanel gp;
	BufferedImage activeSlotImage;

	private final int selectionBorderWidth;
	private final int selectionBorderHeight;

	private final int hotbarSize = 9;
	public int slotSelected = 1;

	public UI_Hotbar(GamePanel gp) {

		this.gp = gp;

		this.selectionBorderWidth = 21 * this.gp.scale;
		this.selectionBorderHeight = 22 * this.gp.scale;

		this.width = 190 * this.gp.scale;
		this.height = 22 * this.gp.scale;

		this.screenX = 49 * this.gp.scale;
		this.screenY = 190 * this.gp.scale;

		try {

			this.image = ImageIO.read(getClass().getResourceAsStream("/ui/hotbar.png"));
			this.activeSlotImage = ImageIO.read(getClass().getResourceAsStream("/ui/hotbar_active_slot.png"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void update() {

		changeSlotSelection();
	}

	public void draw(Graphics2D g2) {

		drawSlotSelection(g2);
		drawCollectedItems(g2);
	}

	private void changeSlotSelection() {

		if (gp.keyHandler.hasValidKeyPressed) {

			for (int i = 1; i <= hotbarSize; i++) {

				String slotNumber = Integer.toString(i);

				if (gp.keyHandler.pressedKey.equals(slotNumber)) {

					this.slotSelected = i;
					return;
				}
			}
		}
	}

	public void drawCollectedItems(Graphics2D g2) {

		for (int i = 0; i < gp.player.inventory.size(); i++) {

			if (gp.player.inventory.get(i) != null) {

				BufferedImage itemImage = gp.player.inventory.get(i).itemImage;
				
				g2.drawImage(itemImage, screenX + gp.scale + (i * selectionBorderWidth), screenY + gp.scale,
						gp.tileSize, gp.tileSize, null);
			}
		}
	}

	public void drawSlotSelection(Graphics2D g2) {

		int activeSlotX = screenX + selectionBorderWidth * (slotSelected - 1);
		g2.drawImage(image, screenX, screenY, width, height, null);
		g2.drawImage(activeSlotImage, activeSlotX, screenY, selectionBorderWidth, selectionBorderHeight, null);
	}
}
