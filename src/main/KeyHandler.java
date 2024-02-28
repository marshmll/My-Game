package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {

	public String pressedKey, releasedKey;
	Map<Integer, String> validKeys = new HashMap<Integer, String>();

	public KeyHandler() {
		super();
		// Accepted keys and their respective name.
		validKeys.put(KeyEvent.VK_UP, "up");
		validKeys.put(KeyEvent.VK_DOWN, "down");
		validKeys.put(KeyEvent.VK_LEFT, "left");
		validKeys.put(KeyEvent.VK_RIGHT, "right");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		String key = validKeys.get(keyCode);
		if (key == null)
			return; // If the key pressed is not valid, just return.

		if (key == releasedKey) // Reset released key if the presses key is the same.
			releasedKey = null;

		pressedKey = key;

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		String key = validKeys.get(keyCode);
		if (key == null)
			return; // If the key pressed is not valid, just return.

		if (key == pressedKey) // Reset pressed key if the released key is the same.
			pressedKey = null;

		releasedKey = key;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
