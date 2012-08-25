package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class missile {
	private String direction;
	private int x, y;
	private Image image;
	boolean visible;
	private int width, height;

	private final int BOARD_WIDTH = 390;
	private final int MISSILE_SPEED = 5;

	public missile(int x, int y, String direction) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("res/imgs/missile.png"));
		image = ii.getImage();
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	public Image getImage() {
		return image;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public void move() {
		if (this.direction=="UP") {
			y += -MISSILE_SPEED;
		}
		else if (this.direction=="DOWN") {
			y += MISSILE_SPEED;
		}
		else if (this.direction=="LEFT") {
			x += -MISSILE_SPEED;
		}
		else if (this.direction=="RIGHT") {
			x += MISSILE_SPEED;
		}
		if ((x>BOARD_WIDTH)||(x<0)||(y>290)||(y<0)) {
			visible = false;
		}
	}
}