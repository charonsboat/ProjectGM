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
	private double timeOver;
	private Thread thread;
	public Player Player;
	private ArrayList Enemies;
	private boolean ingame;
	private int B_WIDTH;
	private int B_HEIGHT;
	private double FPS;
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

		Player = new Player();

		initEnemies();
	}
	private void displayFPS() {
		FPS = Math.round(1000/ ((int) DELAY + timeOver));
	}
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
		thread = new Thread(this);
		thread.start();
	}
	public void initEnemies() {
		Enemies = new ArrayList();

		for (int i=0; i<pos.length; ++i) {
			Enemies.add(new Enemy(pos[i][0], pos[i][1]));
		}
	}
	public void paint(Graphics g) {
		super.paint(g);

		if (ingame) {
			Graphics2D g2d = (Graphics2D) g;

			if (Player.isVisible()) {
				g2d.drawImage(Player.getImage(), Player.getX(), Player.getY(), this);
			}

			ArrayList ms = Player.getMissiles();

			for (int i=0;i<ms.size();++i) {
				missile m = (missile) ms.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}
			for (int i=0;i<Enemies.size();++i) {
				Enemy a = (Enemy) Enemies.get(i);
				if (a.isVisible()) {
					int a_xx = a.getX(Player.getX(), Player.getY());
					int a_yy = a.getY(Player.getY(), Player.getX());
					g2d.drawImage(a.getImage(), a_xx, a_yy, this);
					g2d.setColor(Color.WHITE);
					g2d.drawString(String.valueOf(a.getHealth()), a_xx, a_yy-2);
				}
			}

			g2d.setColor(Color.WHITE);
			g2d.drawString("Enemies Left: "+Enemies.size(), 5, 15);
			displayFPS();
			g2d.drawString("FPS: "+String.valueOf(FPS), 200, 15);
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
			Player.move();
			checkCollisions();
			repaint();

			if (Enemies.size()==0) {
				ingame = false;
			}

			ArrayList ms = Player.getMissiles();

			for (int i=0; i<ms.size(); ++i) {
				missile m = (missile) ms.get(i);
				if (m.isVisible()) {
					m.move();
				}
				else {
					ms.remove(i);
				}
			}
			for (int i=0; i<Enemies.size(); ++i) {
				Enemy a = (Enemy) Enemies.get(i);
				if (a.isVisible()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("interrupted");
					}
					a.move();
				}
				else {
					Enemies.remove(i);
				}
			}

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			this.timeOver = (-1) * sleep;

			if (sleep<0) {
				sleep = 0;
			}

			try {
				Thread.sleep(sleep);
			}
			catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}
	}
	public void checkCollisions() {
		Rectangle r3 = Player.getBounds();

		for (int j=0; j<Enemies.size(); ++j) {
			Enemy a = (Enemy) Enemies.get(j);
			Rectangle r2 = a.getBounds();

			if (r3.intersects(r2)) {
				Player.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}

		ArrayList ms = Player.getMissiles();

		for (int i=0; i<ms.size(); ++i) {
			missile m = (missile) ms.get(i);

			Rectangle r1 = m.getBounds();

			for (int j=0; j<Enemies.size(); ++j) {
				Enemy a = (Enemy) Enemies.get(j);
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
			Player.keyReleased(e);
		}
		public void keyPressed(KeyEvent e) {
			Player.keyPressed(e);
		}
	}
}