package de.hu_berlin.andarin;

public class Ant implements Thing {
	
	protected int posX = 10, posY = 90,
				health = 10;
	
	protected int[] aim; // holds the current aim-coords of the ant (e.g. food / home / etc.)
	
	protected byte load = 0, loadToUnload = 0;
	
	protected final byte capacity = 10, watchRange = 3;
	
	protected final String name = "Ant";
	
	boolean atHome = false, earning = false;
	
	public Ant () {	};
	
	/**
	 * Makes the ant move - depending on its loading to home or to food
	 * @param fieldPart is a small area of the global field around the ant
	 */
	public void walk(SignMap signMap, FoodMap foodMap) {
		if (load < capacity && earning) earnFood(foodMap, aim);
		else if (!(load / capacity > 0.5)) goHome(foodMap, signMap);
		else looksForFood(foodMap, signMap);
		}
	
	/**
	 * @param signMap is a small area of the global field around the ant,
	 * @param at minimum it is the watchRange of the ant
	 */
	protected void goHome(FoodMap foodMap, SignMap signMap) {
		
		boolean stepTaken = false;
		
		// Suche nach Wegzeichen
		

		// Was machen, wenn keine Wegzeichen gefunden wurden
		if (!stepTaken) {
			walkByMood(foodMap);
		}
	}

	private void walkByMood(Map map) {
		
	}

	/**
	 * returns the actions happening when the ant arrives at home
	 * like unloading food, restore health, disappearing from the field
	 */
	protected void isAtHome() {
		atHome = true; 		 // ant ist nun zuhause
		loadToUnload = load; // uebergibt Lieferung ins temp, per getInfo abrufbar
		load = 0;			 // ist nun wieder leer
	}
	
	/**
	 * Standardmethod to get all information about the ant
	 * @return int[] info = {posX, posY, health, loadToUnload};
	 */
	public int[] getInfo() {
		int[] info = {posX, posY, health, loadToUnload};
		loadToUnload = 0;
		return info;
	}

	protected void looksForFood(FoodMap foodMap, SignMap signMap) {
		
		// Frage nach, ob die Ant essen sieht
		aim = foodMap.isSomethingInRange(posX, posY, watchRange);
		
		// Wenn ja, dann
		if (!(aim == null)) {
			double difX = (aim[0] - posX);
			double difY = (aim[1] - posY);
			// Wenn pythagoraeischer Abstand kleiner gleich 1 -> kann mit dem Abbau beginnen
			if (difX*difX + difY*difY <= 1) {
				earning = true;
				earnFood(foodMap, aim);
			} else  { // Wenn essen gesehen, aber noch zu weit entfernt zum ernten -> Schritt darauf zu
				double newDifX = difX * (difX*difX + difY*difY);
				double newDifY = difY * (difX*difX + difY*difY);
				posX += newDifX;
				posY += newDifY;
			}
			
		} else { // Wenn Ameise noch kein essen sieht
				 // haelt Ant Ausschau nach foodSign
			aim = signMap.isSomethingInRange(posX, posY, watchRange);
			
			// Wenn sie foodSign sieht, dann
			if (!(aim == null)) {
				double difX = (aim[0] - posX);
				double difY = (aim[1] - posY);
				// Gehe auf foodSign zu
					double newDifX = difX * (difX*difX + difY*difY);
					double newDifY = difY * (difX*difX + difY*difY);
					posX += newDifX;
					posY += newDifY;
			} else {
				walkByMood(signMap); // Wenn ant noch kein EssenSign sieht
			}
		}
	}

	protected void makeTraceSign() { };
	
	/**
	 * @param foodMap is the overview over the foodressources
	 * @param aim is the actual target in the mind of the ant - here the foodCoords
	 * 	increases the load of the ant if there is still food
	 */
	protected void earnFood (FoodMap foodMap, int[] aim) {
		byte earned = foodMap.earnFood(aim);
		if (earned > 0) load += earned;
		else earning = false;
	};

	/**
	 * Returns true if the ant is on the field
	 */
	public boolean occupiesSpace() {
		return !atHome;
	}

	/**
	 * Returns true if the name given equals with the name of the object
	 */
	public boolean whatIsIt(String name) {
		return name.equals(this.name);
	}
}
