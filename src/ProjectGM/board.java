package ProjectGM;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class board extends JPanel implements Runnable {
	private Thread thread;
	public craft craft;
	private ArrayList aliens;
	private boolean ingame;
	private int B_WIDTH;
	private int B_HEIGHT;
	private final int DELAY = 20;

	private int[][] pos = {
		{250, 200}, {300, 40}, {300, 200}
	};

	public board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		ingame = true;

		setSize(400, 300);

		craft = new craft();

		initAliens();
	}
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
		thread = new Thread(this);
		thread.start();
	}
	public void initAliens() {
		aliens = new ArrayList();

		for (int i=0; i<pos.length; ++i) {
			aliens.add(new alien(pos[i][0], pos[i][1]));
		}
	}
	public void paint(Graphics g) {
		super.paint(g);

		if (ingame) {
			Graphics2D g2d = (Graphics2D) g;

			if (craft.isVisible()) {
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
			}

			ArrayList ms = craft.getMissiles();

			for (int i=0;i<ms.size();++i) {
				missile m = (missile) ms.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
			for (int i=0;i<aliens.size();++i) {
				alien a = (alien) aliens.get(i);
				if (a.isVisible()) {
					g2d.drawImage(a.getImage(), a.getX(craft.getX(), craft.getY()), a.getY(craft.getY(), craft.getX()), this);
				}
			}

			g2d.setColor(Color.WHITE);
			g2d.drawString("Aliens Left: "+aliens.size(), 5, 15);
		}
		else {
			String msg = "Game Over";
			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics metr = this.getFontMetrics(small);

			g.setColor(Color.WHITE);
			g.setFont(small);
			g.drawString(msg, (B_WIDTH-metr.stringWidth(msg))/2, B_HEIGHT/2);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {
			craft.move();
			checkCollisions();
			repaint();

			if (aliens.size()==0) {
				ingame = false;
			}

			ArrayList ms = craft.getMissiles();

			for (int i=0; i<ms.size(); ++i) {
				missile m = (missile) ms.get(i);
				if (m.isVisible()) {
					m.move();
				}
				else {
					ms.remove(i);
				}
			}
			for (int i=0; i<aliens.size(); ++i) {
				alien a = (alien) aliens.get(i);
				if (a.isVisible()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("interrupted");
					}
					a.move();
				}
				else {
					aliens.remove(i);
				}
			}

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep<0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}
	}
	public void checkCollisions() {
		Rectangle r3 = craft.getBounds();

		for (int j=0; j<aliens.size(); ++j) {
			alien a = (alien) aliens.get(j);
			Rectangle r2 = a.getBounds();

			if (r3.intersects(r2)) {
				craft.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}

		ArrayList ms = craft.getMissiles();

		for (int i=0; i<ms.size(); ++i) {
			missile m = (missile) ms.get(i);

			Rectangle r1 = m.getBounds();

			for (int j=0; j<aliens.size(); ++j) {
				alien a = (alien) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					a.setHealth(-1);
					if (a.getHealth()<=0) {
						a.setVisible(false);
					}
				}
			}
		}
	}
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			craft.keyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
		}
	}
}