package entity.mob;

import indieQuest.Quest;
import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;

import java.util.Random;

public class WizardEnemy extends Mob {
	private Quest quest;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private final int ANIMSPEED = 10;
	private final int MOVESPEED = 2;
	private int health = 50;
	private int lastDir;
	private int xa = 0, ya = 0;
	
	public WizardEnemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	private void move() {
		xa = 0; ya = 0;
		xa++;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	public void update() {
		move();
	}
	
	private void clear() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
				level.projectiles.remove(i);
			}
		}
	}

	public void updateShoot() {		

	}
	
	// Render method which has if statements for animation
	@SuppressWarnings("static-access")
	public void render(Screen screen) {	
		if (dir == 0) {
			if (lastDir == 1) {
				sprite = sprite.s_b1; 
			} else {
				sprite = sprite.s_b1;
			}
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					if (lastDir == 1) {
						sprite = sprite.s_b1; 
					} else {
						sprite = sprite.s_b1;
					}
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					if (lastDir == 1) {
						sprite = sprite.s_b2; 
					} else {
						sprite = sprite.s_b2;
					}
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					if (lastDir == 1) {
						sprite = sprite.s_b3; 
					} else {
						sprite = sprite.s_b3;
					}
					anim = 0;
				} else anim = 0;
			}
		}
		if (dir == 2) {
			if (lastDir == 1) {
				sprite = sprite.s_f1; 
			} else {
				sprite = sprite.s_f1;
			}
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					if (lastDir == 1) {
						sprite = sprite.s_f1; 
					} else {
						sprite = sprite.s_f1;
					}
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					if (lastDir == 1) {
						sprite = sprite.s_f2; 
					} else {
						sprite = sprite.s_f2;
					}
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					if (lastDir == 1) {
						sprite = sprite.s_f3; 
					} else {
						sprite = sprite.s_f3;
					}
					anim = 0;
				} else anim = 0;
			}
		}
		if (dir == 3) {
			sprite = sprite.s_l1;
				if (walking) {
					if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
						sprite = sprite.s_l1;
					} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
						sprite = sprite.s_l2;
					} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
						sprite = sprite.s_l3;
						anim = 0;
					} else anim = 0;
				}
		}
		if (dir == 1) { 
			sprite = Sprite.s_r1; 
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					sprite = sprite.s_r1;
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					sprite = sprite.s_r2;
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					sprite = sprite.s_r3;
					anim = 0;
				} else anim = 0;
			}
		}
		screen.renderPlayer(x, y, sprite);
	}
}
