package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class alien {
	private String craftImage = "res/imgs/enemy.png";

	private int x;
	private int y;
	private int health;
	private int distance;
	private int craftX;
	private int craftY;
	private int width;
	private int height;
	private boolean visible;
	private Image image;

	public alien(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craftImage));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
		this.craftX = craftX;
		this.craftY = craftY;
		this.health = 5;
	}
	public void move() {
	}
	public int getX(int craft_X, int craft_Y) {
		int tempX;
		int tempY;
		tempX = craftX-x;
		tempY = craftY-y;
		craftX = craft_X;

		distance = formulas.getDistance(x, y, craftX, craftY);
		if (distance<100) {
			if (craftX>x) {
				x += 1;
			}
			if (craftX<x) {
				x -= 1;
			}
		}

		return x;
	}
	public int getY(int craft_Y, int craft_X) {
		int tempX;
		int tempY;
		tempX = craftX-x;
		tempY = craftY-y;
		craftY = craft_Y;

		distance = formulas.getDistance(x, y, craftX, craftY);
		if (distance<100) {
			if (craftY>y) {
				y += 1;
			}
			if (craftY<y) {
				y -= 1;
			}	
		}

		return y;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setHealth(int damage) {
		this.health += damage;
	}
	public int getHealth() {
		return health;
	}
	public Image getImage() {
		return image;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}