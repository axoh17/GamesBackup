package mygames.Maker.level;

import java.util.ArrayList;
import java.util.List;

import mygames.Maker.entity.Entity;
import mygames.Maker.entity.particle.Particle;
import mygames.Maker.entity.projectile.Projectile;
import mygames.Maker.graphics.Screen;
import mygames.Maker.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	public static Level spawn = new SpawnLevel("/levels/spawnlevel.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level (String path) {
		loadLevel(path);
		generateLevel();
	} //Use this to load a pre-made level map
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++){
		entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++){
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++){ 
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	private void time() {
		
	}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4; 
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if(getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) { //Use this to render levels
		screen.setOffset(xScroll,  yScroll);
		int x0 = xScroll >> 4; //same as divided by 16 Left side of screen
		int x1 = (xScroll + screen.width + 16) >>  4;//Right side of the screen
		int y0 = yScroll >> 4; //Top edge of screen
		int y1 = (yScroll + screen.height + 16) >> 4; //Bottom edge of screen
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x,y).render(x , y, screen); //For Random Generating
			} //end for x loop
		} //end for y loop
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).render(screen);
		}
	} //end render
	
	public void add(Entity e) {
		e.init(this);
		if(e instanceof Particle) {
			particles.add((Particle)e);
		} else if( e instanceof Projectile) {
			projectiles.add((Projectile)e);
		} else {
			entities.add(e);
		}
	}
	
	//Grass = 0xff00ff00
	//Flower = 0xffffff00
	//Rock = 0xff7f7f00
	//Brick Wall = 0xff7f0000
	public Tile getTile(int x, int y) { //Run this under render above which takes in these tiles
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile; //The boundaries
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass; 
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower; 
		if (tiles[x + y * width] == Tile.col_rock) return Tile.rock; 
		if (tiles[x + y * width] == Tile.col_wallBrick) return Tile.wallBrick; 
		return Tile.voidTile; //A black tile
	}
}
