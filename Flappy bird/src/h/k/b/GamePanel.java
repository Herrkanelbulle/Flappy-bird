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

	private int score;
	private final Random random;

	private boolean running;
	private final Bird bird;
	private final ArrayList<Pipe> pipes;
	private boolean started;
	private boolean jumping;
	private boolean canJump;
	private int jumpStart;
	public final int WIDTH = 500;
	public final int HEIGHT = 500;
	private final long fps = 60L;
	private final int hop = 90;
	private final int speed = 2;
	private final int fallSpeed = 4;
	private final int pipeGap = 150;
	private final int gap = 200;
	private final int maxPipes = 9;
	private final int pipeWidth = 40;

	public GamePanel() {
		score = 0;
		canJump = true;
		bird = new Bird(50, 0);
		bird.setY(HEIGHT / 2 - bird.getHeight());
		random = new Random();
		pipes = new ArrayList<Pipe>();
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		new Thread(this).start();
	}

	@Override
	public void run() {
		if (running) {
			return;
		}
		running = true;
		addPipes();
		while (running) {
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
		bird.draw(g);
		pipes.stream().filter(p -> p.getType() != Pipe.PIPETYPE.MIDDLE).forEach(p -> p.draw(g));
		final String text = "Score: " + score;
		g.setColor(Color.WHITE);
		g.drawString(text, HEIGHT / 2 - g.getFontMetrics().stringWidth(text) / 2, 30);
	}

	private void update() {
		if (bird.getY() + bird.getHeight() > HEIGHT - bird.getHeight()) {
			bird.setAlive(false);
		}
		pipes.removeIf(p -> p.getX() < -p.getWidth());
		for (final Pipe p2 : pipes) {
			if (bird.getBound().intersects(p2.getBound()) && !p2.getType().equals(PIPETYPE.MIDDLE)) {
				bird.setAlive(false);
			}
			if (bird.getX() == p2.getX() + p2.getWidth() && p2.getType().equals(PIPETYPE.MIDDLE)) {
				score++;
			}
			if (bird.isAlive()) {
				p2.setX(p2.getX() - speed);
			}
		}
		if (bird.isAlive()) {
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
		if (pipes.size() < maxPipes * 3) {
			addPipe((maxPipes * gap) - 40);
		}
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		render(g2d);
		g2d.dispose();
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			if (!started) {
				started = true;
			}
			if (!jumping || !bird.isAlive()) {
				jump();
				jumpStart = bird.getY();
			}
			canJump = false;
		} else if (e.getKeyCode() == e.VK_ESCAPE) {
			System.exit(0);
		}
	}

	private void jump() {
		if (!bird.isAlive()) {
			pipes.clear();
			addPipes();
			bird.setY(HEIGHT / 2 - bird.getHeight() / 2);
			bird.setAlive(true);
			score = 0;
		}
		if (bird.getY() > hop && canJump) {
			jumping = true;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == e.VK_SPACE) {
			canJump = true;
		}
	}

	@Override
	public void keyTyped(final KeyEvent e) {
	}

	private void addPipe(final int x) {
		final int minHeight = 50;
		final int rHeight = 50 + random.nextInt(200);
		final Pipe up = new Pipe(x, 0, pipeWidth, rHeight, PIPETYPE.UPPER);
		final Pipe middle = new Pipe(x, rHeight, pipeWidth, 150, PIPETYPE.MIDDLE);
		final Pipe down = new Pipe(x, rHeight + pipeGap, pipeWidth, (400 - minHeight) - rHeight, PIPETYPE.LOWER);
		final Color randomColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		middle.setColor(Color.BLACK);
		down.setColor(randomColor);
		up.setColor(randomColor);
		pipes.add(up);
		pipes.add(middle);
		pipes.add(down);
	}

	private void addPipes() {
		int x = 0;
		for (int i = 0; i < maxPipes; ++i) {
			addPipe(200 + x * gap);
			++x;
		}
	}
}
