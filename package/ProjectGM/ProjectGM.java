package ProjectGM;

import javax.swing.JFrame;

public class ProjectGM extends JFrame {
	public ProjectGM() {
		add(new board());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("TwinStick Shooter");
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ProjectGM();
	}
}