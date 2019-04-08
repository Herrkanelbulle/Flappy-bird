package h.k.b;

import javax.swing.*;
import java.awt.*;

public class Main {
	public Main() {
		final JFrame frame = new JFrame("Flappy bird");
		final GamePanel panel = new GamePanel();
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(panel.WIDTH, panel.HEIGHT));
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	public static void main(final String[] args) {
		new Main();
	}
}
