package de.hu_berlin.andarin;

import java.util.Vector;

import gdp.stdlib.StdDraw;

public class AntSim {

	static Vector<Ant> colony;
	
	public static void main(String[] args) {
		
		int screenSectionMin = 0, screenSectionMax = 100, antCurrent = 0,
			timeBetweenCreationOfTwoAnts = 10;
		
		boolean finish = false;
		
		canvasInit(screenSectionMin, screenSectionMax);
		
		FoodMap myFoodMap = new FoodMap();
		SignMap mySignMap = new SignMap();
		
		myFoodMap.placeFood(90, 5, 100);
		
		while(!finish) {			// Programm solange fortsetzen, bis irgendwann Siegbedingung erfuellt
			colony.add(new Ant());  // fuege Kolonie neue Ameise hinzu
			
			Ant ant = colony.get(antCurrent);	// nimm diese Ameise
			
			for (int cnt = 0; cnt < timeBetweenCreationOfTwoAnts; cnt++) {
				for (int antID = 0; antID <= antCurrent; antID++) {
					ant.walk(mySignMap, myFoodMap);
					StdDraw.show(10);
				}
			}
			
			antCurrent++;						// in der naechsten Runde nimm naechste Ameise
			
		}
		
	}

	private static void canvasInit(int min, int max) {
		
		StdDraw.setCanvasSize(500, 400);
		
		StdDraw.setXscale(min, max);
		StdDraw.setYscale(min, max);
		
	}

}
