package main;

import java.awt.Rectangle;

import entity.Entity;
import entity.Player;
import item.Item;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {

		this.gp = gp;
	}

	public void checkTileCollision(Entity entity) {

		// Get the world row in which the top and bottom of the hitbox are.
		int hitBoxTopRow = (entity.worldY + entity.hitBox.y) / gp.tileSize;
		int hitBoxBottomRow = (entity.worldY + entity.hitBox.y + entity.hitBoxHeight) / gp.tileSize;

		// Get the world column in which the top and bottom of the hitbox are.
		int hitBoxLeftColumn = (entity.worldX + entity.hitBox.x) / gp.tileSize;
		int hitBoxRightColumn = (entity.worldX + entity.hitBox.x + entity.hitBoxWidth) / gp.tileSize;

		int tileNum1, tileNum2;
		boolean isTile1Collideable, isTile2Collideable;

		switch (entity.direction) {
		case "up":
			// Predict next player position.
			hitBoxTopRow = (entity.worldY + entity.hitBox.y - entity.speed) / gp.tileSize;

			// Predict the tiles that the player is going to collide with.
			tileNum1 = this.gp.tileManager.mapTileNumbers[hitBoxTopRow][hitBoxLeftColumn];
			tileNum2 = this.gp.tileManager.mapTileNumbers[hitBoxTopRow][hitBoxRightColumn];

			// Check if the tiles collided have collision property.
			isTile1Collideable = this.gp.tileManager.tile[tileNum1].isCollideable;
			isTile2Collideable = this.gp.tileManager.tile[tileNum2].isCollideable;

			// If so, update player state hasCollided to true.
			if (isTile1Collideable || isTile2Collideable) {
				entity.hasCollided = true;
			} else {
				entity.hasCollided = false;
			}

			break;
		case "down":
			// Same logic to other directions.
			hitBoxBottomRow = (entity.worldY + entity.hitBox.y + entity.hitBoxHeight + entity.speed) / gp.tileSize;

			tileNum1 = this.gp.tileManager.mapTileNumbers[hitBoxBottomRow][hitBoxLeftColumn];
			tileNum2 = this.gp.tileManager.mapTileNumbers[hitBoxBottomRow][hitBoxRightColumn];

			isTile1Collideable = this.gp.tileManager.tile[tileNum1].isCollideable;
			isTile2Collideable = this.gp.tileManager.tile[tileNum2].isCollideable;

			if (isTile1Collideable || isTile2Collideable) {
				entity.hasCollided = true;
			} else {
				entity.hasCollided = false;
			}

			break;
		case "left":
			hitBoxLeftColumn = (entity.worldX + entity.hitBox.x - entity.speed) / gp.tileSize;

			tileNum1 = this.gp.tileManager.mapTileNumbers[hitBoxTopRow][hitBoxLeftColumn];
			tileNum2 = this.gp.tileManager.mapTileNumbers[hitBoxBottomRow][hitBoxLeftColumn];

			isTile1Collideable = this.gp.tileManager.tile[tileNum1].isCollideable;
			isTile2Collideable = this.gp.tileManager.tile[tileNum2].isCollideable;

			if (isTile1Collideable || isTile2Collideable) {
				entity.hasCollided = true;
			} else {
				entity.hasCollided = false;
			}

			break;
		case "right":
			hitBoxRightColumn = (entity.worldX + entity.hitBox.x + entity.hitBoxWidth + entity.speed) / gp.tileSize;

			tileNum1 = this.gp.tileManager.mapTileNumbers[hitBoxTopRow][hitBoxRightColumn];
			tileNum2 = this.gp.tileManager.mapTileNumbers[hitBoxBottomRow][hitBoxRightColumn];

			isTile1Collideable = this.gp.tileManager.tile[tileNum1].isCollideable;
			isTile2Collideable = this.gp.tileManager.tile[tileNum2].isCollideable;

			if (isTile1Collideable || isTile2Collideable) {
				entity.hasCollided = true;
			} else {
				entity.hasCollided = false;
			}

			break;
		}
	}

	public int checkPropCollision(Entity entity) {

		boolean isPlayerEntity = entity instanceof Player;

		int index = -1; // DEFAULT VALUE FOR NO PROP INTERACTION

		int entityHitBoxWorldX = entity.worldX + entity.hitBox.x;
		int entityHitBoxWorldY = entity.worldY + entity.hitBox.y;

		for (int i = 0; i < gp.itemsManager.items.length; i++) {
			Rectangle entityHitBox = new Rectangle(entityHitBoxWorldX, entityHitBoxWorldY, entity.hitBoxWidth,
					entity.hitBoxHeight);

			Item prop = gp.itemsManager.items[i];

			if (prop != null) {

				int propHitBoxWorldX = prop.worldX + prop.hitBox.x;
				int propHitBoxWorldY = prop.worldY + prop.hitBox.y;

				Rectangle propHitBox = new Rectangle(propHitBoxWorldX, propHitBoxWorldY, prop.hitBoxDefaultWidth,
						prop.hitBoxDefaultHeight);

				switch (entity.direction) {
				case "up":

					entityHitBox.y -= entity.speed;

					if (entityHitBox.intersects(propHitBox)) {

						if (prop.isCollideable)
							entity.hasCollided = true;

						if (isPlayerEntity)
							index = i;
					}

					break;
				case "down":

					entityHitBox.y += entity.speed;

					if (entityHitBox.intersects(propHitBox)) {

						if (prop.isCollideable)
							entity.hasCollided = true;

						if (isPlayerEntity)
							index = i;
					}

					break;
				case "left":

					entityHitBox.x -= entity.speed;

					if (entityHitBox.intersects(propHitBox)) {

						if (prop.isCollideable)
							entity.hasCollided = true;

						if (isPlayerEntity)
							index = i;
					}

					break;
				case "right":

					entityHitBox.x += entity.speed;

					if (entityHitBox.intersects(propHitBox)) {

						if (prop.isCollideable)
							entity.hasCollided = true;

						if (isPlayerEntity)
							index = i;
					}

					break;
				}
			}
		}
		return index;
	}
}