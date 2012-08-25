package Test; // In this package, I am trying to access a class full of static methods so that I can build a utility class.

public class Main {
	public Main() {
		System.out.println(Formulas.getDistance(1,3,1,132));
	}
	public static void main(String[] args) {
		new Main();
	}
}