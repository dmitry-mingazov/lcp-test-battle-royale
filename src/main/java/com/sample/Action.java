package com.sample;

import java.util.concurrent.ThreadLocalRandom;

public class Action {
	
	private Player player;
	private String type;

	public Action(Player player, String type) {
		this.player = player;
		this.type = type;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int[] getMovement() {
		if (type.equals("movement")) {
			int x = ThreadLocalRandom.current().nextInt(0, player.getVision());
			int y = ThreadLocalRandom.current().nextInt(0, player.getVision());
			int[] movement = {y, x};
			return movement;
		} else {
			return null;
		}
	}

}
