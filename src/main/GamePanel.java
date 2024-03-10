package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import ui.UIManager;

import entity.Player;
import item.ItemsManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	public final int originalTileSize = 18;
	public final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 54
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 864px
	public final int screenHeight = tileSize * maxScreenRow; // 648px
	private final int oneSecondInNano = 1000000000;
	private final int FPS = 30;

	// WORLD SETTINGS
	public final int maxWorldCol = 160;
	public final int maxWorldRow = 120;

	// SYSTEM
	public KeyHandler keyHandler = new KeyHandler();
	public UIManager uiManager = new UIManager(this);
	Sound sound = new Sound();
	Thread gameThread;

	// TILES
	TileManager tileManager = new TileManager(this);

	// ENTITY
	public CollisionChecker colChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyHandler);
	public ItemsManager itemsManager = new ItemsManager(this);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}

	public void setGameState() {

		this.itemsManager.createMapitems();
		this.playMusic("underclocked", .1f);
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = oneSecondInNano / FPS; // drawInterval ≃ 0.033...
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timePassed;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			timePassed = currentTime - lastTime;
			delta += timePassed / drawInterval; // Delta gets bigger/equal to 1 whenever time passed is bigger/equal to
												// the draw interval.
			lastTime = currentTime; // Update last time

			if (delta >= 1) {

				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		player.update();
		uiManager.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tileManager.draw(g2); 	// Tiles
		itemsManager.draw(g2); 	// Items
		player.draw(g2); 		// Player
		uiManager.draw(g2); 	// UI

		g2.dispose();
	}

	public void playMusic(String name, float volume) {

		sound.setFile(name);
		sound.setVolume(volume);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {

		sound.stop();
	}

	public void playSoundEffect(String efName, float volume) {

		sound.setFile(efName);
		sound.setVolume(volume);
		sound.play();
	}
}
