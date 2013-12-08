package joaolourenco.net.pascal.display;

import javax.swing.JFrame;

public class Window {

	public static void create() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("PascalLike");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		Display.setFrame(frame);
	}

	public static void create(boolean useDefaults) {
		if (useDefaults) create();
		else {
			JFrame frame = new JFrame();
			Display.setFrame(frame);
		}
	}
}
