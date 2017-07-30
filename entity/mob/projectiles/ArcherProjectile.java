package indieQuest.entity.mob.projectiles;

import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

public class ArcherProjectile extends Projectile {

	public ArcherProjectile(int sx, int sy, double dir) {
		super(sx - 5, sy, dir);
		speed = 4; // 4 feels good
		angle = dir;
		fireRate = 7; // 7 feels good
		damage = 5;
		range = 200;

		sprite = Sprite.rotate(Sprite.arrow, angle);

		targetX = speed * Math.cos(angle);
		targetY = speed * Math.sin(angle);
	}

	public void update() {
		move();
	}

	public void move() {
		x += targetX;
		y += targetY;
		if (distance() > range) remove();
	}

	protected double distance() {
		double dist = 0;
		dist = Math.sqrt((startX - x) * (startX - x) + (startY - y) * (startY - y));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, sprite);
	}
}
