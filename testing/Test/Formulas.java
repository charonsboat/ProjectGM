package Test;


public class Formulas {
	private Formulas() {
		throw new AssertionError();
	}
	public static int getDistance(int x1, int y1, int x2, int y2) {
		int distance = (int) Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));

		return distance;
	}
}