package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class craft {
	private String craft = "craft.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private Image image;
	private ArrayList missiles;
	private String direction;

	public craft() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		missiles = new ArrayList();
		visible = true;
		x = 40;
		y = 60;
	}
	public void move() {
		x += dx;
		y += dy;

		if (x<1) {
			x = 1;
		}
		if (y<1) {
			y = 1;
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return image;
	}
	public ArrayList getMissiles() {
		return missiles;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isVisible() {
		return visible;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key==KeyEvent.VK_UP) {
			fire("UP");
		}
		if (key==KeyEvent.VK_DOWN) {
			fire("DOWN");
		}
		if (key==KeyEvent.VK_LEFT) {
			fire("LEFT");
		}
		if (key==KeyEvent.VK_RIGHT) {
			fire("RIGHT");
		}
		if (key==KeyEvent.VK_A) {
			dx = -3;
		}
		if (key==KeyEvent.VK_D) {
			dx = 3;
		}
		if (key==KeyEvent.VK_W) {
			dy = -3;
		}
		if (key==KeyEvent.VK_S) {
			dy = 3;
		}
	}
	public void fire(String direction) {
		missiles.add(new missile(x+width, y+height/2, direction));
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key==KeyEvent.VK_A) {
			dx = 0;
		}
		if (key==KeyEvent.VK_D) {
			dx = 0;
		}
		if (key==KeyEvent.VK_W) {
			dy = 0;
		}
		if (key==KeyEvent.VK_S) {
			dy = 0;
		}
	}
}