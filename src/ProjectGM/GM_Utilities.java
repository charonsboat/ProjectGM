package ProjectGM;

import java.awt.Rectangle;


public class GM_Utilities {
	private GM_Utilities() {
		throw new AssertionError();
	}
	public static int getDistance(int x1, int y1, int x2, int y2) {
		int distance = (int) Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));

		return distance;
	}
	public static boolean checkCollision(Rectangle Entity1, Rectangle Entity2) {
		if (Entity1.intersects(Entity2)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static String getCollisionSide(Rectangle Entity1, Rectangle Entity2) {
		String side = "EMPTY";
		if (((Entity1.getX()+Entity1.getWidth())-(1+Player.speed()))<Entity2.getX()) {
			side = "LEFT";
		}
		if ((Entity1.getX()+(1+Player.speed()))>(Entity2.getX()+Entity2.getWidth())) {
			side = "RIGHT";
		}
		if (((Entity1.getY()+Entity1.getHeight())-(1+Player.speed()))<Entity2.getY()) {
			side = "TOP";
		}
		if ((Entity1.getY()+(1+Player.speed()))>(Entity2.getY()+Entity2.getHeight())) {
			side = "BOTTOM";
		}
		if ((((Entity1.getX()+Entity1.getWidth())-(1+Player.speed()))>Entity2.getX())&&((Entity1.getX()+(1+Player.speed()))<(Entity2.getX()+Entity2.getWidth()))) {
			if ((((Entity1.getY()+Entity1.getHeight())-(1+Player.speed()))>Entity2.getY())&&((Entity1.getY()+(1+Player.speed()))<(Entity2.getY()+Entity2.getHeight()))) {
				side = "INSIDE";
			}
		}
		System.err.println(side);

		return side;
	}
}