package com.sample;

public class Turn {

	private Player player;
	private Entity[] POIs;
	
	public Turn(Player player, Entity[] POIs) {
		this.player = player;
		this.POIs = POIs;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Entity[] getPOIs() {
		return this.POIs;
	}
}
