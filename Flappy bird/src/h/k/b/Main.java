package h.k.b;

import javax.swing.*;
import java.awt.*;

public class Main {
	public Main() {
		final JFrame frame = new JFrame("Flappy bird");
		final GamePanel panel = new GamePanel();
		frame.setContentPane(panel);
		frame.setVisible(true);
		final JFrame frame2 = frame;
		panel.getClass();
		final int n = 500;
		panel.getClass();
		frame2.setPreferredSize(new Dimension(n, 500));
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
	}

	public static void main(final String[] args) {
		new Main();
	}
}
