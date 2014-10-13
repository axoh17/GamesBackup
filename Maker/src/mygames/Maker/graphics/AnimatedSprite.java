package mygames.Maker.graphics;

public class AnimatedSprite extends Sprite{

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int length = -1;
	private int time = 0;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		sprite = sheet.getSprites()[0];
		this.length = length;
	}

	public void update() {
		time++;
		if(time % rate == 0) {
			if(frame >= length - 1) frame = 0;
			else frame ++;
			sprite = sheet.getSprites()[frame];
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int frames) {
		rate = frames;
	}
	
	public void setFrame (int index) {
		if(index > sheet.getSprites().length - 1) {
			System.err.println("Index Out of Bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}
}
