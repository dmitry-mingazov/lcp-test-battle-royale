package com.sample;

public class MapCell {
	private Entity content;

	public MapCell() {
		this.content = null;
	}
	
	public Entity getContent() {
		return this.content;
	}
	
	public void insertContent(Entity entity) {
		this.content = entity;
	}
	
	public void removeContent() {
		this.content = null;
	}
	
	@Override
	public String toString() {
		if(this.content != null) {
			return this.content.toString();
		} else {
			return "    ";
		}
	}
}
