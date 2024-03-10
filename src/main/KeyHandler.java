package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyHandler implements KeyListener {

	public String pressedKey, releasedKey;
	public boolean hasValidKeyPressed;
	Map<Integer, String> validKeys = new HashMap<Integer, String>();

	public KeyHandler() {
		super();
		// Accepted keys and their respective name.
		validKeys.put(KeyEvent.VK_UP, "up");
		validKeys.put(KeyEvent.VK_DOWN, "down");
		validKeys.put(KeyEvent.VK_LEFT, "left");
		validKeys.put(KeyEvent.VK_RIGHT, "right");
		validKeys.put(KeyEvent.VK_Q, "q");
		validKeys.put(KeyEvent.VK_1, "1");
		validKeys.put(KeyEvent.VK_2, "2");
		validKeys.put(KeyEvent.VK_3, "3");
		validKeys.put(KeyEvent.VK_4, "4");
		validKeys.put(KeyEvent.VK_5, "5");
		validKeys.put(KeyEvent.VK_6, "6");
		validKeys.put(KeyEvent.VK_7, "7");
		validKeys.put(KeyEvent.VK_8, "8");
		validKeys.put(KeyEvent.VK_9, "9");
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
		hasValidKeyPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		String key = validKeys.get(keyCode);
		if (key == null)
			return; // If the key pressed is not valid, just return.

		if (key == pressedKey) {
			// Reset pressed key if the released key is the same.
			pressedKey = null;
			hasValidKeyPressed = false;
		}

		releasedKey = key;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
