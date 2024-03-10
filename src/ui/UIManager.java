package ui;

import java.awt.Graphics2D;

import main.GamePanel;

public class UIManager {

	public UI_Hotbar hotbar;

	public UIManager(GamePanel gp) {

		this.hotbar = new UI_Hotbar(gp);
	}

	public void draw(Graphics2D g2) {

		hotbar.draw(g2);
	}

	public void update() {

		hotbar.update();
	}
}
