package indieQuest.level.tile;

import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

public class CollisionTile extends Tile{
	public int x, y;
	public Sprite sprite;
	public int SIZE;
	
	public CollisionTile (Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
