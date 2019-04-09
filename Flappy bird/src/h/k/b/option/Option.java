package h.k.b.option;

public abstract class Option<T> {

	// Value
	private T value;

	// Name
	private String name;

	// Superclass constructor
	public Option(T value, String name) {
		this.name = name;
		setValue(value);
	}

	// Returns the current value
	public T getValue() {
		return value;
	}

	// Set value
	public void setValue(T value) {
		this.value = value;
	}

	// Returns the name of the individual value
	public String getName() {
		return name;
	}

}
