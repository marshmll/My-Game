package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile tile[];
	int mapTileNumbers[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;
		tile = new Tile[20];
		mapTileNumbers = new int[gp.maxWorldRow][gp.maxWorldCol];

		this.getTileImage();
		this.loadMap("/maps/world_01.txt");
	}
	
	private Tile createTileFromPath(String path) {
		Tile tile = new Tile();
		try {
			tile.image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tile;
	}
	public void getTileImage() {
		
			tile[0] = createTileFromPath("/tiles/grass.png");
			tile[1] = createTileFromPath("/tiles/wallblock.png");
			tile[2] = createTileFromPath("/tiles/water.png");
			tile[3] = createTileFromPath("/tiles/dirt.png");
			tile[4] = createTileFromPath("/tiles/tree_dark_0.png");
			tile[5] = createTileFromPath("/tiles/sand.png");
			tile[6] = createTileFromPath("/tiles/radioactive_grass.png");
			tile[7] = createTileFromPath("/tiles/radioactive_grass_path.png");
			tile[8] = createTileFromPath("/tiles/radioactive_water.png");
			tile[9] = createTileFromPath("/tiles/dead_bush.png");
			tile[10] = createTileFromPath("/tiles/skull.png");
			tile[11] = createTileFromPath("/tiles/dry_tree.png");
	}

	public void loadMap(String filePath) {

		try {

			InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			for (int row = 0; row < gp.maxWorldRow; row++) {

				String numbers[] = bufferedReader.readLine().split(" ");

				for (int col = 0; col < gp.maxWorldCol; col++) {

					int number = Integer.parseInt(numbers[col]);
					mapTileNumbers[row][col] = number;
				}
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {

		for (int row = 0; row < gp.maxWorldRow; row++) {

			for (int col = 0; col < gp.maxWorldCol; col++) {

				int tileNumber = mapTileNumbers[row][col];

				int worldX = col * gp.tileSize;
				int worldY = row * gp.tileSize;

				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;

				if (screenX > 0 - gp.tileSize && screenX < gp.screenWidth && screenY > 0 - gp.tileSize
						&& screenY < gp.screenHeight) {
					
					g2d.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}

			}
		}
	}
}
