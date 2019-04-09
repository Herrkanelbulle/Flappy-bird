package h.k.b.option;

public abstract class Option<T> {

	private T value;
	private String name;

	public Option(T value, String name) {
		this.name = name;
		setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

}
