package level.tile;

import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	public int SIZE;
	
	public static Tile dirt_light = new BckgTile(Sprite.dirt_light);
	public static Tile dirt_dark = new BckgTile(Sprite.dirt_dark);
	public static Tile grass_light = new BckgTile(Sprite.grass_light);
	public static Tile grass_dark = new BckgTile(Sprite.grass_dark);
	public static Tile stone = new BckgTile(Sprite.stone);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		this.SIZE = sprite.SIZE;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean topLevel() {
		return false;
	}
	
	public boolean solid() {
		return false;
	}
}
