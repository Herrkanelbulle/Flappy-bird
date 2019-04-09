package h.k.b.entity;

import java.awt.*;

public abstract class Entity {

	// Bounding-box
	private Rectangle bound;

	// Entity constructor
	public Entity(final int x, final int y, final int width, final int height) {
		this.setBound(x, y, width, height);
	}

	// Returns the x-coordinate
	public int getX() {
		return this.bound.x;
	}

	// Sets the x-coordinate
	public void setX(final int x) {
		this.bound.x = x;
	}

	// Returns the y-coordinate
	public int getY() {
		return this.bound.y;
	}

	// Sets the y-coordinate
	public void setY(final int y) {
		this.bound.y = y;
	}

	// Returns the bounding-box width
	public int getWidth() {
		return this.bound.width;
	}

	// Returns the bounding-box height
	public int getHeight() {
		return this.bound.height;
	}

	// Sets the bounding-box for the entity
	private void setBound(final int x, final int y, final int width, final int height) {
		this.bound = new Rectangle(x, y, width, height);
	}

	// Returns the bounding-box
	public Rectangle getBound() {
		return this.bound;
	}

	// Draw the entity
	public abstract void draw(final Graphics2D p0);
}
