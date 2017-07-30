package level;

import indieQuest.entity.Entity;
import indieQuest.entity.mob.Player;
import indieQuest.entity.mob.WizardEnemy;
import indieQuest.entity.mob.projectiles.Projectile;
import indieQuest.graphics.Screen;
import indieQuest.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
	protected int width;
	protected int height;
	protected int[] tiles;
	protected int[] mapArray; // pixel array of map
	protected int[] mapArray2;
	private boolean alreadyRun = false;
	private Random rand = new Random();
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
		
		if (alreadyRun == false) {
			alreadyRun = true;
			int numOfEnemies = rand.nextInt(51);
			for (int i = 0; i < 1; i++) {
				//WizardEnemy e = new WizardEnemy(rand.nextInt(width) * 16, rand.nextInt(height) * 16);
				WizardEnemy e = new WizardEnemy(17 * 16, 17 * 16);
				add(e);
			}
		}
	}
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}
	
	public void render(int xPos, int yPos, Screen screen) {
		// Sets the offset equal to the xPos and yPos of the player which we want
		screen.setOffset(xPos, yPos);
		int x0 = xPos >> 4;
		int x1 = (xPos + screen.width + 16) >> 4;  // (x0, y0) is top left corner pin
		int y0 = yPos >> 4;							// (x1, y1) is top right corner pin
		int y1 = (yPos + screen.height + 16) >> 4;

		// Cycles through the tiles that are within the corner pins (visible on screen) and renders only those
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	public Player getPlayer() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				return (Player) entities.get(i);
			}
		}
		return null;
	}
	
	// Used to get tiles off the RGB map, default returns void 
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xFF0000FF) return Tile.dirt_light;
		if (tiles[x + y * width] == 0xFF00000F) return Tile.stone;
		if (tiles[x + y * width] == 0xFF00FFFF) return Tile.grass_dark;
		if (tiles[x + y * width] == 0xFF00AAAA) return Tile.grass_light;
		if (tiles[x + y * width] == 0xFFAAAAAA) return Tile.dirt_dark;
		
		return Tile.voidTile;
	}
}
