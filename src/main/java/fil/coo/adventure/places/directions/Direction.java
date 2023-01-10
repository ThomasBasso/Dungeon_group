package fil.coo.adventure.places.directions;

import java.util.Random;

public enum Direction {
	N("north"),
	S("south"),
	E("east"),
	W("west");
	
	private String name;
	
	private Direction(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public static Direction alea() {
		Random r = new Random();
		Direction[] directions = Direction.values();
		return directions[r.nextInt(directions.length)];
	}
	
	public Direction opposite() {
		// A compléter
	    return Direction.S;
	}
}
