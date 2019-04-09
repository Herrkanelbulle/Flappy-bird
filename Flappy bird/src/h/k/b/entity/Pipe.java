package h.k.b.entity;

import java.awt.*;

public class Pipe extends Entity {

	// Pipes color
	private Color color;

	// What type of pipe.
	private PIPETYPE type;

	// Entity constructor for the pipe
	public Pipe(final int x, final int y, final int width, final int height, final PIPETYPE type) {
		super(x, y, width, height);
		this.type = type;
		this.color = Color.BLUE;
	}

	// Returns the pipe-type
	public PIPETYPE getType() {
		return this.type;
	}

	// Returns the color of the pipe
	public Color getColor() {
		return this.color;
	}

	// Sets the color of the pipe
	public void setColor(final Color color) {
		this.color = color;
	}

	// Renders the pipe
	@Override
	public void draw(final Graphics2D g) {
		g.setColor(this.getColor());
		g.fill(this.getBound());
	}

	// All pipe types.
	public enum PIPETYPE {
		UPPER, MIDDLE, LOWER;
	}
}
