package indieQuest.entity.mob;

import indieQuest.Quest;
import indieQuest.graphics.Screen;
import indieQuest.graphics.Sprite;
import indieQuest.input.Keyboard;
import indieQuest.input.Mouse;

public class Player extends Mob {
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private int health = 100;
	private boolean walking = false;
	private final int ANIMSPEED = 10;
	private int currentIn = 0;
	private final int MOVESPEED = 2;
	private int lastDir;

	public Player(Keyboard input) {
		this.input = input;
	}
	
	// X & Y are relative to the world
	public Player(int x, int y, Keyboard input) {
		this.x = x * 16;
		this.y = y * 16;
		this.input = input;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		
		if (anim < 9000) {
			anim ++;
		} else anim = 0;
		
		if (input.up) {
			currentIn = 1;
		}
		if (input.down) {
			currentIn = 3;
		}  
		if (input.left) {
			lastDir = 1;
			currentIn = 4; 
		}  
		if (input.right) {
			lastDir = 2;
			currentIn = 2;
		} 
		if (!input.up && !input.down && !input.left && !input.right) currentIn = 0;
		
		if (currentIn == 1) ya-= MOVESPEED;
		if (currentIn == 2) xa+= MOVESPEED;
		if (currentIn == 3) ya+= MOVESPEED;
		if (currentIn == 4) xa-= MOVESPEED;

		// If we are moving call the move method, if not moving walking = false	
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		clear();
		updateShoot();
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
		double dx = Mouse.getX() - (Quest.width * Quest.scale) /2;
		double dy = Mouse.getY() - (Quest.height * Quest.scale) /2;
		double dir = Math.atan2(dy, dx);
		
		if (Mouse.getButton() == 1) {
			shoot(x, y, dir);
		}
	}
	
	// Render method which has if statements for animation
	@SuppressWarnings("static-access")
	public void render(Screen screen) {	
		if (dir == 0) {
			if (lastDir == 1) {
				sprite = sprite.a_b1; 
			} else {
				sprite = sprite.a_b1;
			}
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					if (lastDir == 1) {
						sprite = sprite.a_b1; 
					} else {
						sprite = sprite.a_b1;
					}
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					if (lastDir == 1) {
						sprite = sprite.a_b2; 
					} else {
						sprite = sprite.a_b2;
					}
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					if (lastDir == 1) {
						sprite = sprite.a_b3; 
					} else {
						sprite = sprite.a_b3;
					}
					anim = 0;
				} else anim = 0;
			}
		}
		if (dir == 2) {
			if (lastDir == 1) {
				sprite = sprite.a_f1; 
			} else {
				sprite = sprite.a_f1;
			}
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					if (lastDir == 1) {
						sprite = sprite.a_f1; 
					} else {
						sprite = sprite.a_f1;
					}
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					if (lastDir == 1) {
						sprite = sprite.a_f2; 
					} else {
						sprite = sprite.a_f2;
					}
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					if (lastDir == 1) {
						sprite = sprite.a_f3; 
					} else {
						sprite = sprite.a_f3;
					}
					anim = 0;
				} else anim = 0;
			}
		}
		if (dir == 3) {
			sprite = sprite.a_l1;
				if (walking) {
					if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
						sprite = sprite.a_l1;
					} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
						sprite = sprite.a_l2;
					} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
						sprite = sprite.a_l3;
						anim = 0;
					} else anim = 0;
				}
		}
		if (dir == 1) { 
			sprite = Sprite.a_r1; 
			if (walking) {
				if (anim >= ANIMSPEED * 0 && anim < ANIMSPEED * 1) {
					sprite = sprite.a_r1;
				} else if (anim >= ANIMSPEED * 1 && anim < ANIMSPEED * 2) {
					sprite = sprite.a_r2;
				} else if (anim >= ANIMSPEED * 2 && anim < ANIMSPEED * 3) {
					sprite = sprite.a_r3;
					anim = 0;
				} else anim = 0;
			}
		}
		screen.renderPlayer(x, y, sprite);
	}
}
