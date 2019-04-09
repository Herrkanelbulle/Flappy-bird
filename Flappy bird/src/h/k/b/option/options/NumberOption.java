package h.k.b.option.options;

import h.k.b.option.Option;

public class NumberOption<T extends Number> extends Option<T> {

	private T min;
	private T max;
	private T inc;

	public NumberOption(String name, T value, T min, T max, T inc) {
		super(value, name);
		setValue(value);
		this.min = min;
		this.max = max;
		this.inc = inc;
	}

	public T getMinimum() {
		return this.min;
	}

	public T getMaximum() {
		return this.max;
	}

	public void setIncrement(T inc) {
		this.inc = inc;
	}

	public T getIncrement() {
		return this.inc;
	}
}
