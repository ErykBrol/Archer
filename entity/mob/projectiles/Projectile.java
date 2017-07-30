package indieQuest.entity.mob.projectiles;

import indieQuest.entity.Entity;
import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

public class Projectile extends Entity {
	
	public final int startX, startY;
	public double angle;
	public double x, y;
	public double targetX, targetY;
	public double speed, damage, range, fireRate;
	public Sprite sprite;
	
	public Projectile(int sx, int sy, double dir) {
		startX = sx;
		startY = sy;
		angle = dir;
		this.x = sx;
		this.y = sy;
	}

	public void render(Screen screen) {

	}
	
	public void move() {
		
	}
	
	protected double distance() {
		return 0;
	}
}
