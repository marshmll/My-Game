package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
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

	// WORLD SETTINGS
	public final int maxWorldCol = 160;
	public final int maxWorldRow = 120;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	final int oneSecondInNano = 1000000000;
	final int FPS = 30;

	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	TileManager tileManager = new TileManager(this);
	public Player player = new Player(this, keyHandler);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
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
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		tileManager.draw(g2d);
		player.draw(g2d);

		g2d.dispose();
	}
}
