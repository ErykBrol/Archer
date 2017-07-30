package indieQuest.entity.mob;

import indieQuest.entity.Entity;
import indieQuest.entity.mob.projectiles.ArcherProjectile;
import indieQuest.entity.mob.projectiles.Projectile;
import indieQuest.entity.mob.projectiles.WizardProjectile;
import indieQuest.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {
	protected Sprite sprite;
	protected int dir = 1;
	protected boolean moving = false;
	private double fireRate = 0;
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void move(int xa, int ya ) {
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
		
	}
	
	public void shoot(int x, int y, double dir) {
			if (fireRate == 0) {
				Projectile p = new ArcherProjectile(x, y, dir);
				projectiles.add(p);
				level.addProjectile(p);
				fireRate = p.fireRate;
			} else {
				fireRate -= 1;
			}
	}
	
	public void enemy_shoot(int x, int y, double dir) {
		if (fireRate == 0) {
			Projectile p = new WizardProjectile(x, y, dir);
			projectiles.add(p);
			level.addProjectile(p);
			fireRate = p.fireRate;
		} else {
			fireRate -= 1;
		}
	}
	
	public void render() {
		
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		
		// Checks collision in all 4 corners of a tile
//		for (int c = 0; c < 4; c++) {
//			int xt = ((x + xa) + c % 2 * 24 - 20) /16; 
//			int yt = ((y + ya) + c / 2 * 26 - 20) /16; 
//			if(level.getTile(xt, yt).solid()) solid = true; 
//		}
		return solid;
	}
}