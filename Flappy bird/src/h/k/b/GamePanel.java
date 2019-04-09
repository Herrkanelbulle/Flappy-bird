package h.k.b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import h.k.b.entity.Bird;
import h.k.b.entity.Pipe;
import h.k.b.entity.Pipe.PIPETYPE;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	// The current score
	private int score;

	// Random generator
	private final Random random;

	// Bird entity
	private final Bird bird;

	// All active pipes in one list
	private final ArrayList<Pipe> pipes;

	// Returns true if the application is running
	private boolean running;

	// Returns true if the game has started
	private boolean started;

	// Returns true if the bird is currently jumping
	private boolean jumping;

	// Returns true if the bird can jump under current circumstances.
	private boolean canJump;

	// Y-coordinate of bird when jump started
	private int jumpStart;

	// Window width
	public final int WIDTH = 500;

	// Window height
	public final int HEIGHT = 500;

	// Ticks per second
	private final long fps = 60;

	// Jump height
	private final int hop = 90;

	// horizontal speed
	private final int speed = 2;

	// Vertical speed
	private final int fallSpeed = 4;

	// Gap-size in-between two pipes
	private final int pipeGap = 150;

	// Gap between two pipes
	private final int gap = 200;

	// Maximum amount of active pipes
	private final int maxPipes = 9;

	// Width of each and every pipe
	private final int pipeWidth = 40;

	public GamePanel() {
		// Initialize variables
		score = 0;
		canJump = true;
		bird = new Bird(50, 0);
		bird.setY(HEIGHT / 2 - bird.getHeight());
		random = new Random();
		pipes = new ArrayList<Pipe>();

		// Request keyboard focus and add the key listener
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		// Start the application thread.
		new Thread(this).start();
	}

	@Override
	public void run() {
		if (running) {
			return;
		}
		running = true;

		// Add the "default pipes"
		addPipes();
		while (running) {
			// Update game loop on every tick
			try {
				Thread.sleep(1000 / fps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (started) {
				update();
			}
		}
	}

	private void render(final Graphics2D g) {
		if (bird == null) {
			return;
		}
		// Render the bird
		bird.draw(g);

		// Render all pipes
		pipes.stream().filter(p -> p.getType() != PIPETYPE.MIDDLE).forEach(p -> p.draw(g));

		// Write out the score
		String text = "Score: " + score;
		g.setColor(Color.WHITE);
		g.drawString(text, HEIGHT / 2 - g.getFontMetrics().stringWidth(text) / 2, 30);
	}

	private void update() {
		// Kill the bird if it hits the ground
		if (bird.getY() + bird.getHeight() > HEIGHT - bird.getHeight()) {
			bird.setAlive(false);
		}

		// Remove all pipes that are out of render
		pipes.removeIf(p -> p.getX() < -p.getWidth());

		for (Pipe p : pipes) {
			// Kill the bird if it hits an obscure pipe
			if (bird.getBound().intersects(p.getBound()) && !p.getType().equals(PIPETYPE.MIDDLE)) {
				bird.setAlive(false);
			}

			// Update score
			if (bird.getX() == p.getX() + p.getWidth() && p.getType().equals(PIPETYPE.MIDDLE)) {
				score++;
			}

			// Handle horizontal movement
			if (bird.isAlive()) {
				p.setX(p.getX() - speed);
			}
		}

		if (bird.isAlive()) {
			// Handle vertical movements
			if (jumping) {
				if (bird.getY() > jumpStart - hop) {
					bird.setY(bird.getY() - 10);
				} else {
					jumping = false;
				}
			} else {
				bird.setY(bird.getY() + fallSpeed);
			}
		}

		// Add new pipes
		if (pipes.size() < maxPipes * 3) {
			addPipe((maxPipes * gap) - 40);
		}
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Draw background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		// Render the in-game content
		render(g2d);

		// Dispose graphics
		g2d.dispose();
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!started) {
				// Start the game
				started = true;
			}
			if (!jumping || !bird.isAlive()) {
				// Jump
				jump();
				jumpStart = bird.getY();
			}
			// Prevent the bird from jumping multiple times at once
			canJump = false;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			// Exit on escape
			System.exit(0);
		}
	}

	private void jump() {
		if (!bird.isAlive()) {
			// Reset map and re-spawn the bird
			pipes.clear();
			addPipes();
			bird.setY(HEIGHT / 2 - bird.getHeight() / 2);
			bird.setAlive(true);
			score = 0;
		}
		if (bird.getY() > hop && canJump) {
			// Set jumping status to true
			jumping = true;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// Give the bird the ability to jump again.
			canJump = true;
		}
	}

	@Override
	public void keyTyped(final KeyEvent e) {
	}

	private void addPipe(final int x) {
		final int minHeight = 50;
		final int rHeight = minHeight + random.nextInt(gap);

		// Pipes
		final Pipe up = new Pipe(x, 0, pipeWidth, rHeight, PIPETYPE.UPPER);
		final Pipe middle = new Pipe(x, rHeight, pipeWidth, pipeGap, PIPETYPE.MIDDLE);
		final Pipe down = new Pipe(x, rHeight + pipeGap, pipeWidth, (400 - minHeight) - rHeight, PIPETYPE.LOWER);
		final Color randomColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

		// Give the pipes their color
		middle.setColor(Color.BLACK);
		down.setColor(randomColor);
		up.setColor(randomColor);

		// Add the pipes
		pipes.add(up);
		pipes.add(middle);
		pipes.add(down);
	}

	private void addPipes() {
		// Add the starting pipes.
		int x = 0;
		for (int i = 0; i < maxPipes; ++i) {
			addPipe(200 + x * gap);
			++x;
		}
	}
}
