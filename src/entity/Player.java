package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
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

	private final String[] acceptedMoves = { "up", "down", "left", "right" };

	public Player(GamePanel gp, KeyHandler keyHandler) {

		this.gp = gp;
		this.keyHandler = keyHandler;

		this.screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
		this.screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

		this.width = originalPlayerWidth * gp.scale;
		this.height = originalPlayerHeight * gp.scale;

		this.images.put("up", new BufferedImage[ASSETS_PER_DIRECTION]);
		this.images.put("down", new BufferedImage[ASSETS_PER_DIRECTION]);
		this.images.put("left", new BufferedImage[ASSETS_PER_DIRECTION]);
		this.images.put("right", new BufferedImage[ASSETS_PER_DIRECTION]);

		this.hitBoxWidth = 6 * gp.scale;
		this.hitBoxHeight = 10 * gp.scale;

		this.hitBoxDefaultX = 6 * gp.scale;
		this.hitBoxDefaultY = 7 * gp.scale;

		this.hitBox = new Rectangle(hitBoxDefaultX, hitBoxDefaultY, hitBoxWidth, hitBoxHeight);

		this.hasCollided = false;

		this.spriteUpdateRate = 4;

		this.setDefaultValues();
		this.getPlayerSprites();
	}

	public void setDefaultValues() {

		worldX = 97 * gp.tileSize;
		worldY = 50 * gp.tileSize;
		speed = 5;
		direction = "down";
	}

	public void getPlayerSprites() {
		try {

			for (int i = 0; i < ASSETS_PER_DIRECTION; i++) {

				images.get("up")[i] = ImageIO.read(getClass().getResourceAsStream("/player/scarlet_up_" + i + ".png"));

				images.get("down")[i] = ImageIO
						.read(getClass().getResourceAsStream("/player/scarlet_down_" + i + ".png"));

				images.get("left")[i] = ImageIO
						.read(getClass().getResourceAsStream("/player/scarlet_left_" + i + ".png"));

				images.get("right")[i] = ImageIO
						.read(getClass().getResourceAsStream("/player/scarlet_right_" + i + ".png"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		checkForPlayerActions();
		runAllAnimations();
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		if (images.get(direction) != null) {

			image = images.get(direction)[currentSpriteIndex];

			g2.drawImage(image, screenX, screenY, this.width, this.height, null);
		}
	}

	private void checkForPlayerActions() {

		if (keyHandler.hasValidKeyPressed) {

			// INDIVIDUAL ACTIONS

			// Movement Action
			if (Arrays.asList(acceptedMoves).contains(keyHandler.pressedKey)) {

				this.direction = keyHandler.pressedKey;
				gp.colChecker.checkTileCollision(this);

				if (!this.hasCollided)
					movePlayer(this.direction);
			}

			// Pick up prop action
			int interactedPropIndex = gp.colChecker.checkPropCollision(this);
			pickUpProp(interactedPropIndex);
		}
	}

	private void movePlayer(String direction) {

		switch (direction) {
		case "up":
			worldY -= speed;
			break;
		case "down":
			worldY += speed;
			break;
		case "left":
			worldX -= speed;
			break;
		case "right":
			worldX += speed;
			break;
		default:
			break;
		}

	}

	private void pickUpProp(int propIndex) {

		if (propIndex != -1) {

			gp.props[propIndex] = null;
		}
	}

	private void runAllAnimations() {

		spriteUpdateCounter++;

		if (keyHandler.hasValidKeyPressed) {

			if (spriteUpdateCounter > spriteUpdateRate) {
				spriteUpdateIterationCounter++;

				// INDIVIDUAL ANIMATIONS

				// Movement Animation
				if (Arrays.asList(acceptedMoves).contains(keyHandler.pressedKey)) {
					runMovimentAnimation();
				}

				spriteUpdateCounter = 0;
			}
		} else
			currentSpriteIndex = 0;
	}

	private void runMovimentAnimation() {

		if (spriteUpdateIterationCounter > spriteMovementAnimationMapping.length - 1) {

			spriteUpdateIterationCounter = 0;
		}
		currentSpriteIndex = spriteMovementAnimationMapping[spriteUpdateIterationCounter];
	}
}
