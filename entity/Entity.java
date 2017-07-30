package indieQuest.entity;

import indieQuest.graphics.Screen;
import indieQuest.level.Level;

import java.util.Random;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render (Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	// Initializes the level object for use in mob for collision
	public void init(Level level) {
		this.level = level;
	}
}
