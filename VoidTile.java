package indieQuest.level.tile;

import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

public class VoidTile extends Tile {
	int x, y;
	Sprite sprite;
	int SIZE;

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}


	public boolean topLevel() {
		return false;
	}

	public boolean solid() {
		return true;
	}
}
