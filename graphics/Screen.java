package indieQuest.graphics;

import indieQuest.level.tile.Tile;

import java.util.Random;

public class Screen {
	/*
	 * The math behind pixels[x + y * width] or similar calculations is simple. Basically it takes x (which cycles through 0 - width) and adds it to y * width. (y also cycles through 0 - width)
	 * This gets us every pixel on the screen row by row as while y = 0, we're in first row and cycle through all pixels in it (x = 0 -> width) and so on.
	 * 
	 */

	public int width, height;

	public int[] pixels;
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	public int xOffset, yOffset;	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(0xFFFFFF);
		}
	}
	
	public void clear(){
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		// Offsets the tile rendering by the offset called in the Level class
		xp -= xOffset; 
		yp -= yOffset;

		// Cycles through each pixel in the tile's sprite
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			// Cycles through the pixels of the offset position of the tile
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				// Cycles through the pixels of the offset position of the tile
				int xa = x + xp;
				// If the tile is off the screen (not shown) stop rendering it
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				//Cycles through each pixel in screen array (row by row) and sets it to the corresponding pixel from the tile sprite
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Sprite sprite) {
		// Offsets the tile rendering by the offset called in the Level class
		xp -= xOffset; 
		yp -= yOffset;

		// Cycles through each pixel in the tile's sprite
		for (int y = 0; y < sprite.SIZE; y++) {
			// Cycles through the pixels of the offset position of the tile
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				// Cycles through the pixels of the offset position of the tile
				int xa = x + xp;
				// If the tile is off the screen (not shown) stop rendering it
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				//Cycles through each pixel in screen array (row by row) and sets it to the corresponding pixel from the tile sprite
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xFFFFFFFF && col != 0x00000000) pixels[xa + ya * width] = col;
			}
		}
	}
	
	
	// The player doesn't move on the screen, therefore we constantly render him in the center 
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset + 8;
		yp -= yOffset + 8;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xFFFFFFFF) pixels[xa + ya * width] = col;
			}
		}
	}

	// Sets this classes' offset values passed by Level class
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
