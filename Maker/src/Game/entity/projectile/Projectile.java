package Game.entity.projectile;

import java.util.Random;

import Game.entity.Entity;
import Game.graphics.Sprite;

public abstract class Projectile extends Entity{
	
	final protected double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny; //new x/y
	protected double speed, range, damage;
	protected double distance;
	
	protected final Random random = new Random();
	
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {
		
	}
}