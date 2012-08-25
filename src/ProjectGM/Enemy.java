package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	private String PlayerImage = "res/imgs/Enemy.png";

	private int x;
	private int y;
	private int health;
	private int distance;
	private int PlayerX;
	private int PlayerY;
	private int width;
	private int height;
	private boolean visible;
	private Image image;

	public Enemy(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(PlayerImage));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
		this.PlayerX = PlayerX;
		this.PlayerY = PlayerY;
		this.health = 5;
	}
	public void move() {
	}
	public int getX(int Player_X, int Player_Y) {
		int tempX;
		int tempY;
		tempX = PlayerX-x;
		tempY = PlayerY-y;
		PlayerX = Player_X;

		distance = GM_Utilities.getDistance(x, y, PlayerX, PlayerY);
		if (distance<100) {
			if (PlayerX>x) {
				x += 1;
			}
			if (PlayerX<x) {
				x -= 1;
			}
		}

		return x;
	}
	public int getY(int Player_Y, int Player_X) {
		int tempX;
		int tempY;
		tempX = PlayerX-x;
		tempY = PlayerY-y;
		PlayerY = Player_Y;

		distance = GM_Utilities.getDistance(x, y, PlayerX, PlayerY);
		if (distance<100) {
			if (PlayerY>y) {
				y += 1;
			}
			if (PlayerY<y) {
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