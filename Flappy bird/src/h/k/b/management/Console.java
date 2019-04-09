package h.k.b.management;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JPanel {

	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	private static final long serialVersionUID = 1L;
	private JTextField textInput;
	private JTextArea textOutput;

	public Console() {
		// Add the input field
		initializeTextField();

		// Add the output field
		initializeTextArea();

		// Setups keyboard management
		setFocusable(true);
		requestFocus();
	}

	private void initializeTextArea() {
		textOutput = new JTextArea();
		JScrollPane output;
		int width = 300;
		int height = 300;
		int x = WIDTH / 2 - width / 2;
		int y = 20;
		textOutput.setBackground(Color.LIGHT_GRAY);
		textOutput.setEditable(false);
		output = new JScrollPane(textOutput);
		output.setBounds(x, y, width, height);
		output.setBackground(Color.LIGHT_GRAY);
		add(output);
	}

	private void initializeTextField() {
		textInput = new JTextField(20);
		int width = 300;
		int height = 20;
		int x = WIDTH / 2 - width / 2;
		int y = 320;
		textInput.setBounds(x, y, width, height);
		textInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textInput.getText() != null) {
					executeCommand(textInput.getText());
					textInput.setText("");
				}

			}
		});
		add(textInput);
	}

	public void write(String s) {

	}

	private void executeCommand(String s) {
		textOutput.append(s + "\n");

	}
}