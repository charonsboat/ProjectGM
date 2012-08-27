package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	private String Player = "res/imgs/Player.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private static final int PLAYER_SPEED = 2;
	private boolean visible;
	private boolean stats = false;
	private Image image;
	private ArrayList missiles;
	private String direction;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(Player));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		missiles = new ArrayList();
		visible = true;
		x = 40;
		y = 60;
	}
	public static int speed() {
		int speed = PLAYER_SPEED;

		return speed;
	}
	private void toggleStats() {
		if (stats) {
			stats = false;
		}
		else {
			stats = true;
		}
	}
	public void move(int B_WIDTH, int B_HEIGHT) {
		x += dx;
		y += dy;

		if (x<0) {
			x = 0;
		}
		if (x>B_WIDTH-width) {
			x = B_WIDTH-width;
		}
		if (y<0) {
			y = 0;
		}
		if (y>B_HEIGHT-height) {
			y = B_HEIGHT-height;
		}
	}
	public void setMove(String move_direction) {
		switch (move_direction) {
			case "UP":
				y -= PLAYER_SPEED;
				break;
			case "RIGHT":
				x += PLAYER_SPEED;
				break;
			case "DOWN":
				y += PLAYER_SPEED;
				break;
			case "LEFT":
				x -= PLAYER_SPEED;
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
	public boolean stats() {
		return stats;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key==KeyEvent.VK_F3) {
			toggleStats();
		}
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
			dx = -PLAYER_SPEED;
		}
		if (key==KeyEvent.VK_D) {
			dx = PLAYER_SPEED;
		}
		if (key==KeyEvent.VK_W) {
			dy = -PLAYER_SPEED;
		}
		if (key==KeyEvent.VK_S) {
			dy = PLAYER_SPEED;
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