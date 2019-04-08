package h.k.b.entity;

import java.awt.*;

public class Pipe extends Entity {
	private Color color;
	private PIPETYPE type;

	public Pipe(final int x, final int y, final int width, final int height, final PIPETYPE type) {
		super(x, y, width, height);
		this.type = type;
		this.color = Color.BLUE;
	}

	public PIPETYPE getType() {
		return this.type;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.setColor(this.getColor());
		g.fill(this.getBound());
	}

	public enum PIPETYPE {
		UPPER("UPPER", 0), MIDDLE("MIDDLE", 1), LOWER("LOWER", 2);

		private PIPETYPE(final String s, final int n) {
		}
	}
}
