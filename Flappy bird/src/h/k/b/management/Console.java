package h.k.b.management;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JPanel {

	private static final long serialVersionUID = 1L;

	// Window width and height
	public final int WIDTH = 400;
	public final int HEIGHT = 400;

	// Text fields
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

	// Setup the text area
	private void initializeTextArea() {
		textOutput = new JTextArea();
		JScrollPane output;

		// Text area bounds
		int width = 300;
		int height = 300;
		int x = WIDTH / 2 - width / 2;
		int y = 20;

		textOutput.setBackground(Color.LIGHT_GRAY);
		textOutput.setEditable(false);

		// Add the output field
		output = new JScrollPane(textOutput);
		output.setBounds(x, y, width, height);
		output.setBackground(Color.LIGHT_GRAY);
		add(output);
	}

	private void initializeTextField() {
		textInput = new JTextField(20);

		// Text field bounds
		int width = 300;
		int height = 20;
		int x = WIDTH / 2 - width / 2;
		int y = 320;
		textInput.setBounds(x, y, width, height);

		// Sends message when enter is pressed
		textInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textInput.getText() != null) {
					executeCommand(textInput.getText());
					textInput.setText("");
				}

			}
		});

		// Add the input field
		add(textInput);
	}

	// Add the message to the text area, and add a new line.
	public void write(String s) {
		textOutput.append(s + "\n");
	}

	// Execute command
	private void executeCommand(String s) {
		write(s);

	}
}