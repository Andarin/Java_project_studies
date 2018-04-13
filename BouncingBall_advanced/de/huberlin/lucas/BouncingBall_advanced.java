package de.huberlin.lucas;

import gdp.stdlib.StdDraw;

import java.awt.Color;
import java.util.*;

/**
 * @author Frank und Luc
 * 
 */
public class BouncingBall_advanced {

	final static double ay = 0.005; 	// Erdanziehungskraft
	final static double R = 0.0005; 	// Rollreibung
	final static double fehler = 0.002; // Wenn welche v unterschritten wird,
										// hört er auf sich zu bewegen
	final static double V2kinE = 0.1; 	// Wieviel kin. E verliert er beim
										// Bodenaufprall (in %)
	final static int refreshRate = 25;

	static Vector<Ball> baelle; 		// Ballnetz

	public static void main(String[] args) {
		double 	x1 = -0.9, y1 = -0.9, 	// Startpunkt Ball 1
				v1x = 0.04, v1y = 0.03, // Beschleunigung Ball 1
				rad1 = 0.1; 			// Radius Ball 1

		int maxBaelle = 5;

		baelle = new Vector<Ball>(maxBaelle);
		for (int i = 0; i < maxBaelle; i++) {
			
			int r, g, b;			// Farbe erzeugen;
			if 		(i == 0) {r = 255; g = 0; b = 0;}
			else if (i == 1) {r = 0; g = 0; b = 255;}
			else 			 {r = (int) (245*Math.random()); g = (int) (255*Math.random()); b = (int) (245*Math.random());}
			
			baelle.add(new Ball(x1 + 1.5 / maxBaelle * i, y1 + 1.5 / maxBaelle * i, v1x, v1y, rad1,
					new Color(r, g, b)));
		}

		StdDraw.setXscale(-1.0, +1.0);
		StdDraw.setYscale(-1.0, +1.0);

		while (true) {
			StdDraw.clear(StdDraw.WHITE);
			for (int i = 0; i < baelle.size(); i++) {
				animation(i);
			}
			StdDraw.show(refreshRate);
		}

	}

	/**
	 * @param ballIndex
	 *            Index des Balls, der animiert werden soll.
	 */
	private static void animation(int ballIndex) {

		Ball b = baelle.get(ballIndex);

		if (StdDraw.mousePressed()
				&& Math.abs(b.getX() - StdDraw.mouseX()) < b.getRad()
				&& Math.abs(b.getY() - StdDraw.mouseY()) < b.getRad()) {
			b.push(StdDraw.mouseX(), StdDraw.mouseY());
		}

		b.applyGravity(ay);
		b.applyAirFriction(R);

		if (b.isTooSlowToBounce(fehler)) {
			b.rollOnGround(R, fehler);
		}
		
		boolean moveNormal = true;				// checks if the movement of b is interrupted or normal
		
		if (b.touchesLeftOrRightBorder()) { 
			b.bounceBackX(V2kinE); 
			moveNormal = false;}
		
		if (b.touchesBottomOrTopBorder()) {
			b.bounceBackY(V2kinE);
			moveNormal = false;}
		
		Ball c = b.givesBallBCollidesWith(baelle, ballIndex);
		if (c != null) {
			b.collision(c);
			b.moveNormally();
			moveNormal = false;
		}
		
		if (moveNormal) {
			b.moveNormally();	
		}

		// Ball zeichnen

		StdDraw.setPenColor(b.getColour());
		StdDraw.filledCircle(b.getX(), b.getY(), b.getRad());
	}

}
