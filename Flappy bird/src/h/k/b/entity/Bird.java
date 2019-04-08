package h.k.b.entity;

import java.awt.*;

public class Bird extends Entity {
	private boolean alive;

	public Bird(final int x, final int y) {
		super(x, y, 25, 25);
		this.alive = true;
	}

	public void setAlive(final boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return this.alive;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setColor(this.alive ? Color.GREEN : Color.RED);
		g.fill(this.getBound());
	}
}
