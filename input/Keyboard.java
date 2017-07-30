package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	// Create an array of booleans to store whether keys are down or up (t/f)
	// 120 is an arbitrary number, provided its higher than # of keys needed to keep track of, it doesn't matter
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right;
	
	// Updates and checks whether the key is pressed or not
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	// Method executed when key is pressed, setting the boolean to true
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	// Method executed when key is released, setting the boolean to false
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	// Not used
	public void keyTyped(KeyEvent e) {
		
	}
}
