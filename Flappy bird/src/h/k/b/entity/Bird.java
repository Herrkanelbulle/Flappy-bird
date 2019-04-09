package h.k.b.entity;

import java.awt.*;

public class Bird extends Entity {

	// Returns true if the bird is alive
	private boolean alive;

	// Entity constructor for the bird entity
	public Bird(final int x, final int y) {
		super(x, y, 25, 25);
		this.alive = true;
	}

	// Set alive status
	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	// Returns whether the bird is alive or not
	public boolean isAlive() {
		return this.alive;
	}

	// Render the bird.
	@Override
	public void draw(final Graphics2D g) {
		g.setColor(this.alive ? Color.GREEN : Color.RED);
		g.fill(this.getBound());
	}
}
