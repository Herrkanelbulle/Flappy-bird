package h.k.b.management;

import h.k.b.option.options.NumberOption;

public class Settings {

	// Ticks per second
	public static NumberOption<Long> fps = new NumberOption<Long>("Ticks per second", 60L, 10L, 120L, 10L);;

	// Jump height
	public static NumberOption<Integer> hop = new NumberOption<Integer>("Jump height", 90, 30, 160, 10);;

	// horizontal speed
	public static NumberOption<Integer> speed = new NumberOption<Integer>("Horizontal speed", 2, 1, 5, 1);;

	// Vertical speed
	public static NumberOption<Integer> fallSpeed = new NumberOption<Integer>("Vertical speed", 4, 1, 10, 1);

	// Gap-size in-between two pipes
	public static NumberOption<Integer> pipeGap = new NumberOption<Integer>("Pipe gap size", 150, 100, 300, 10);

	// Gap between two pipes
	public static NumberOption<Integer> gap = new NumberOption<Integer>("Gap inbetween pipes", 200, 100, 500, 10);;;

	// Maximum amount of active pipes
	public static NumberOption<Integer> maxPipes = new NumberOption<Integer>("Maximum amount of active pipes", 9, 1, 20,
			1);;;

	// Width of each and every pipe
	public static NumberOption<Integer> pipeWidth = new NumberOption<Integer>("Pipe width", 40, 10, 60, 5);

}
