package ProjectGM;


import java.awt.Insets;

import javax.swing.JFrame;


public class ProjectGM extends JFrame {
	public ProjectGM() {
		add(new board());

		int WIDTH = 400;
		int HEIGHT = 300;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("TwinStick Shooter");
		setResizable(false);
		setVisible(true);
		Insets inset = getInsets();
		int INSET_WIDTH = inset.left+inset.right;
		int INSET_HEIGHT = inset.top+inset.bottom;
		setSize(WIDTH+INSET_WIDTH, HEIGHT+INSET_HEIGHT);
	}
	public static void main(String[] args) {
		new ProjectGM();
	}
}