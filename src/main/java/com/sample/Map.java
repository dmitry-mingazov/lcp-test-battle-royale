package com.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Map {
	private int width;
	private int height;
	private MapCell[][] grid;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new MapCell[width][height];
		this.fillMap();
	}
	
	private void fillMap() {
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				this.grid[y][x] = new MapCell();
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void insertEntityAt(int x, int y, Entity entity) {
		this.grid[y][x].insertContent(entity);
	}
	
	public void insertEntityAtRandom(Entity entity) {
		int x = ThreadLocalRandom.current().nextInt(0, width);
		int y = ThreadLocalRandom.current().nextInt(0, height);
		
		this.grid[y][x].insertContent(entity);
	}
	
	private int[] getPlayerCoordinates(Player player) {
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				if (player == this.grid[y][x].getContent()) {
					int[] coordinates = {y, x};
					return coordinates;
				}
			}
		}
		return null;
	}
	
	public Entity[] getPlayerPOIs(Player player) {
		int[] coordinates = this.getPlayerCoordinates(player);
		int _y = coordinates[1];
		int _x = coordinates[0];
		int playerVision = player.getVision();
		int startY = _y - playerVision > 0 ? _y - playerVision : 0; 
		int endY = _y + playerVision < this.height ? _y + playerVision : this.height; 
		int startX = _x - playerVision > 0 ? _x - playerVision : 0; 
		int endX = _x + playerVision < this.width ? _x + playerVision : this.width; 
		
		List<Entity> POIs = new ArrayList<Entity>();
		for(int y = startY; y < endY; y++) {
			for(int x = startX; x < endX; x++) {
				if (x == _x && y == _y) {
					continue;
				}
				Entity entity = this.grid[y][x].getContent();
				if (entity != null) {
					POIs.add(entity);
				}
			}
		}
		
		return POIs.toArray(new Entity[0]);
	}

	@Override
	public String toString() {
		String res = "";
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				res = res.concat("[" + grid[y][x] + "]\t");
			}
			res = res.concat("\n\n");
		}
		return res;
	}
	

}
