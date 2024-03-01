package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Entity {

	public int worldX, worldY;
	public int speed;
	public int width;
	public int height;
	public final int ASSETS_PER_DIRECTION = 4;
	
	public Map<String, BufferedImage[]> images = new HashMap<String, BufferedImage[]>();

	public String direction;
	public int spriteUpdateCounter, currentSpriteIndex, spriteUpdateIterationCounter = 0; //
	public int spriteUpdateRate = 4;
	public int spriteMovementAnimationMapping[] = { 1, 2, 3, 2 }; // This determines the order in that the sprites are rendered.
	
	public Rectangle hitBox;
	public int hitBoxDefaultX;
	public int hitBoxDefaultY;
	public int hitBoxHeight;
	public int hitBoxWidth;
	
	public boolean hasCollided = false;
	
}
