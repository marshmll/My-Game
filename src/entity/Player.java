package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyHandler;

	public final int screenX;
	public final int screenY;

	private int originalPlayerWidth = 16;
	private int originalPlayerHeight = 18;

	private int playerWidth;
	private int playerHeight;

	public Player(GamePanel gp, KeyHandler keyHandler) {

		this.gp = gp;
		this.keyHandler = keyHandler;

		this.screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
		this.screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

		this.playerWidth = originalPlayerWidth * gp.scale;
		this.playerHeight = originalPlayerHeight * gp.scale;

		this.spriteUpdateRate = 4;

		this.setDefaultValues();
		this.getPlayerSprites();
	}

	public void setDefaultValues() {

		worldX = 71 * gp.tileSize;
		worldY = 65 * gp.tileSize;
		speed = 5;
		direction = "down";
	}

	public void getPlayerSprites() {
		try {

			for (int i = 0; i < ASSETS_PER_DIRECTION; i++) {
				up[i] = ImageIO.read(getClass().getResourceAsStream("/player/scarlet_up_" + i + ".png"));
				down[i] = ImageIO.read(getClass().getResourceAsStream("/player/scarlet_down_" + i + ".png"));
				left[i] = ImageIO.read(getClass().getResourceAsStream("/player/scarlet_left_" + i + ".png"));
				right[i] = ImageIO.read(getClass().getResourceAsStream("/player/scarlet_right_" + i + ".png"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void moveUp() {
		direction = keyHandler.pressedKey;
		worldY -= speed;
	}

	private void moveDown() {
		direction = keyHandler.pressedKey;
		worldY += speed;
	}

	private void moveLeft() {
		direction = keyHandler.pressedKey;
		worldX -= speed;
	}

	private void moveRight() {
		direction = keyHandler.pressedKey;
		worldX += speed;
	}

	public void update() {

		if (keyHandler.pressedKey == "up")
			moveUp();
		else if (keyHandler.pressedKey == "down")
			moveDown();
		else if (keyHandler.pressedKey == "left")
			moveLeft();
		else if (keyHandler.pressedKey == "right")
			moveRight();

		spriteUpdateCounter++;

		if (keyHandler.pressedKey != null && keyHandler.releasedKey != keyHandler.pressedKey) {

			if (spriteUpdateCounter > spriteUpdateRate) {

				spriteUpdateIterationCounter++;

				if (spriteUpdateIterationCounter > spriteAnimationMapping.length - 1)
					spriteUpdateIterationCounter = 0;

				currentSpriteIndex = spriteAnimationMapping[spriteUpdateIterationCounter];
				spriteUpdateCounter = 0;
			}

		} else
			currentSpriteIndex = 0;
	}

	public void draw(Graphics2D g2d) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			image = up[currentSpriteIndex];
			break;
		case "down":
			image = down[currentSpriteIndex];
			break;
		case "left":
			image = left[currentSpriteIndex];
			break;
		case "right":
			image = right[currentSpriteIndex];
			break;
		}

		g2d.drawImage(image, screenX, screenY, this.playerWidth, this.playerHeight, null);
	}
}
