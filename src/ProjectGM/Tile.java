package ProjectGM;

import java.awt.Rectangle;


public class Tile {
	private Tile() {
		throw new AssertionError();
	}
	public static int[] get(int row, int column) {
		int[] points = new int[2];
		points[0] = column * 50;
		points[1] = row * 50;

		return points;
	}
}