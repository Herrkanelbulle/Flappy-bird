package h.k.b.entity;

import java.awt.*;

public abstract class Entity {
	private Rectangle bound;

	public Entity(final int x, final int y, final int width, final int height) {
		this.setBound(x, y, width, height);
	}

	public int getX() {
		return this.bound.x;
	}

	public void setX(final int x) {
		this.bound.x = x;
	}

	public int getY() {
		return this.bound.y;
	}

	public void setY(final int y) {
		this.bound.y = y;
	}

	public int getWidth() {
		return this.bound.width;
	}

	public int getHeight() {
		return this.bound.height;
	}

	private void setBound(final int x, final int y, final int width, final int height) {
		this.bound = new Rectangle(x, y, width, height);
	}

	public Rectangle getBound() {
		return this.bound;
	}

	public abstract void draw(final Graphics2D p0);
}
