package com.sample;

public class Player implements Entity {
	private String name;
	private int vision;
	
	public Player(String name) {
		this.name = name;
		this.vision = 4;
	}
	
	public int getVision() {
		return this.vision;
	}

	public String toString() {
		return this.name;
	}
}
