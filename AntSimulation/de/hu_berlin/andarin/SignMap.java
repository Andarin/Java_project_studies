package de.hu_berlin.andarin;

public class SignMap implements Map {

	Type[][] signMap = new Type[100][100];
	
	public SignMap () {	}
	
	public void placeSign(int x, int y, Type type) {
		signMap[y][x] = type;
	}
	
	
	/**
	 *@param Parameters are the coordinates of the asking object 
	 *	returns null if there is no sign close by
	 **/
	public int[] isSomethingInRange (double x, double y, byte watchRange, Type type) {
		
		for (int row = (int)(y - watchRange); row < (int)(y + watchRange); row++)
			for (int col = (int)(x - watchRange); row < (int)(x + watchRange); row++)
				if (signMap[row][col] == type) {
					int[] coord = {col, row};
					return coord;
				}
		
		return null;
	}

}
