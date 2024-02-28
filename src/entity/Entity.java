package entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int worldX, worldY;
	public int speed;
	public final int ASSETS_PER_DIRECTION = 4;

	public BufferedImage up[] = new BufferedImage[ASSETS_PER_DIRECTION];
	public BufferedImage down[] = new BufferedImage[ASSETS_PER_DIRECTION];
	public BufferedImage left[] = new BufferedImage[ASSETS_PER_DIRECTION];
	public BufferedImage right[] = new BufferedImage[ASSETS_PER_DIRECTION];

	public String direction;
	public int spriteUpdateCounter, currentSpriteIndex, spriteUpdateIterationCounter = 0; //
	public int spriteUpdateRate = 4;
	public int spriteAnimationMapping[] = { 1, 2, 3, 2 }; // This determines the order in that the sprites are rendered;
}
