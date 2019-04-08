package h.k.b;

import javax.swing.*;
import java.awt.*;

public class Main {
	
	public Main() {
		//Initialize frame and GamePanel
		final JFrame frame = new JFrame("Flappy bird");
		final GamePanel panel = new GamePanel();
		
		//Setup the frame
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(panel.WIDTH, panel.HEIGHT));
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(final String[] args) {
		//Run the main constructor
		new Main();
	}
}
