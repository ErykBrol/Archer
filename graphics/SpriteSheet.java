package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/map/map_tilesheet_blur.png", 64); 
	public static SpriteSheet archer = new SpriteSheet("/textures/player/archer_char_sheet.png", 80);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/misc/projectile_sheet.png", 16);
	public static SpriteSheet wizard = new SpriteSheet("/textures/player/wizard_char_sheet.png", 64);
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
