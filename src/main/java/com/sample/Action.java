package com.sample;

public class Action {
	
	private Player player;
	private boolean isFinished;

	public Action(Player player) {
		this.player = player;
		this.isFinished = false;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public boolean isFinished() {
		return isFinished;
	}
	
	public void endAction() {
		this.isFinished = true;
	}
	
}
