package ProjectGM;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Block {
	private String Block = "res/imgs/Block.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private Image image;

	public Block() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(Block));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		x = 50;
		y = 100;
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
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isVisible() {
		return visible;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}