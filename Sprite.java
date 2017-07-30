package indieQuest.graphics;

public class Sprite {
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite dirt_light = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite dirt_dark = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass_light = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite grass_dark = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 0, 1, SpriteSheet.tiles);
	
	public static Sprite voidSprite = new Sprite(16, 0);
	
	// Player
	public static Sprite a_f1 = new Sprite(16, 0, 4, SpriteSheet.archer);
	public static Sprite a_f2 = new Sprite(16, 1, 4, SpriteSheet.archer);
	public static Sprite a_f3 = new Sprite(16, 2, 4, SpriteSheet.archer);
	
	public static Sprite a_fl1 = new Sprite(16, 0, 2, SpriteSheet.archer);
	public static Sprite a_fl2 = new Sprite(16, 1, 2, SpriteSheet.archer);
	public static Sprite a_fl3 = new Sprite(16, 2, 2, SpriteSheet.archer);
	
	public static Sprite a_b1 = new Sprite(16, 0, 3, SpriteSheet.archer);
	public static Sprite a_b2 = new Sprite(16, 1, 3, SpriteSheet.archer);
	public static Sprite a_b3 = new Sprite(16, 2, 3, SpriteSheet.archer);
	
	public static Sprite a_bl1 = new Sprite(16, 3, 0, SpriteSheet.archer);
	public static Sprite a_bl2 = new Sprite(16, 3, 1, SpriteSheet.archer);
	public static Sprite a_bl3 = new Sprite(16, 3, 2, SpriteSheet.archer);
	
	public static Sprite a_l1 = new Sprite(16, 0, 1, SpriteSheet.archer);
	public static Sprite a_l2 = new Sprite(16, 1, 1, SpriteSheet.archer);
	public static Sprite a_l3 = new Sprite(16, 2, 1, SpriteSheet.archer);
	
	public static Sprite a_r1 = new Sprite(16, 0, 0, SpriteSheet.archer);
	public static Sprite a_r2 = new Sprite(16, 1, 0, SpriteSheet.archer);
	public static Sprite a_r3 = new Sprite(16, 2, 0, SpriteSheet.archer);
	
	
	// Satan
	public static Sprite s_f1 = new Sprite(16, 0, 2, SpriteSheet.wizard);
	public static Sprite s_f2 = new Sprite(16, 1, 2, SpriteSheet.wizard);
	public static Sprite s_f3 = new Sprite(16, 2, 2, SpriteSheet.wizard);
	
	public static Sprite s_b1 = new Sprite(16, 0, 3, SpriteSheet.wizard);
	public static Sprite s_b2 = new Sprite(16, 1, 3, SpriteSheet.wizard);
	public static Sprite s_b3 = new Sprite(16, 2, 3, SpriteSheet.wizard);
	
	public static Sprite s_l1 = new Sprite(16, 0, 1, SpriteSheet.wizard);
	public static Sprite s_l2 = new Sprite(16, 1, 1, SpriteSheet.wizard);
	public static Sprite s_l3 = new Sprite(16, 2, 1, SpriteSheet.wizard);
	
	public static Sprite s_r1 = new Sprite(16, 0, 0, SpriteSheet.wizard);
	public static Sprite s_r2 = new Sprite(16, 1, 0, SpriteSheet.wizard);
	public static Sprite s_r3 = new Sprite(16, 2, 0, SpriteSheet.wizard);
	
	public static Sprite arrow = new Sprite(8, 0, 0, SpriteSheet.projectiles);
	public static Sprite wiz_ball = new Sprite(8, 1, 0, SpriteSheet.projectiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size; 
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite (int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite (int[] pixels, int size) {
		this.SIZE = size;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}
	
	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(angle, sprite.pixels, sprite.SIZE, sprite.SIZE), sprite.SIZE);
	}
	
	public static int[] rotate(double angle, int[] sprite, int width, int height) { // angle of rotation, pixel array of sprite, width and height of sprite
		int [] nPixels = new int[width * height];
		
		double cos = Math.cos(-angle);
		double sin = Math.sin(-angle);
		double nx, ny;
		double x0, y0;
		double xx, yy;
		
		x0 = width /2;
		y0 = height /2;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				yy = -(y - y0);
				xx = x - x0;
				
				nx = (cos * xx - sin * yy);
				ny = (sin * xx + cos * yy);
				
				double properX = nx + x0;
				double properY = -(ny - y0);
				
				int gx = (int) properX;
				int gy = (int) properY;

				int col = 0;
				
				if (gx < 0) {
					gx = 0; col = 0xFFFFFFFF;
				}
				else if (gx >= width) {
					gx = width -1; col = 0xFFFFFFFF;
				}
				else if (gy < 0) {
					gy = 0; col = 0xFFFFFFFF;
				}
				else if (gy >= height) {
					gy = height -1; col = 0xFFFFFFFF;
				}
				else col = sprite[x + y * width];
				
				if (gx + gy * width > width * height) col = 0xFFFFFFFF;
				else nPixels[gx + gy * width] = col;
			}
		}
		return nPixels;
	}
	
	public void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x ++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}