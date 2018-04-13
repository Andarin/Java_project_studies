package de.hu_berlin.andarin;

public class FoodMap implements Map {
	
	int[][] foodMap = new int[100][100];
	
	public FoodMap () {	}
	
	public void placeFood(int x, int y, int amount) {
		foodMap[y][x] = amount;
	}
	
	/**
	 *@param Parameters are the coordinates of the asking object 
	 *	returns null if there is no food close by
	 **/
	public int[] isSomethingInRange (double x, double y, byte watchRange, Type type) {
		
		for (int row = (int)(y - watchRange); row < (int)(y + watchRange); row++)
			for (int col = (int)(x - watchRange); row < (int)(x + watchRange); row++)
				if (foodMap[row][col] > 0) {
					int[] coord = {col, row};
					return coord;
				}
		
		return null;
		
	}

	public byte earnFood(int[] earnCoord) {
		if (foodMap[earnCoord[1]][earnCoord[0]] > 0) {  // solange noch food da ist
			foodMap[earnCoord[1]][earnCoord[0]]--;		// baue ab
			return 1;
		} else {
			return 0;									// ansonsten gibt es nichts
		}
	}
	
}
