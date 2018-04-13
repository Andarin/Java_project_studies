package de.huberlin.lucas;

import java.util.Vector;

import gdp.stdlib.StdDraw;

/**
 * @author Frank
 * 
 */
public class Ball {

	double x, y;
	double vx, vy;
	double rad;
	java.awt.Color colour;

	/**
	 * @param x
	 *            Startpunkt (X-Koordinate)
	 * @param y
	 *            Startpunkt (Y-Koordinate)
	 * @param vx
	 *            Beschleunigung (X-Richtung)
	 * @param vy
	 *            Beschleunigung (Y-Richtung)
	 * @param rad
	 *            Radius des Balls
	 * @param colour
	 *            Farbe des Balles
	 */
	public Ball(double x, double y, double vx, double vy, double rad,
			java.awt.Color colour) {
		super();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.rad = rad;
		this.colour = colour;
	}

	public java.awt.Color getColour() {
		return colour;
	}

	public void setColour(java.awt.Color colour) {
		this.colour = colour;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void setRad(double rad) {
		this.rad = rad;
	}

	public double getRad() {
		return rad;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	/**
	 * @return Cloned Ball-Object
	 * @override
	 */
	public Ball clone() {
		Ball resb = new Ball(x, y, vx, vy, rad, colour);
		return resb;
	}

	/**
	 * @param ay
	 */
	public void applyGravity(double ay) {
		vy -= ay;
	}

	/**
	 * @param r
	 */
	public void applyAirFriction(double r) {
		vy = vy * (1 - r / 100);
		vx = vx * (1 - r / 100);
	}

	/**
	 * @param fehler
	 * @return
	 */
	public boolean isTooSlowToBounce(double fehler) {
		return Math.abs(vy) <= fehler && y <= -1;
	}

	/**
	 * @param r
	 * @param fehler
	 */
	public void rollOnGround(double r, double fehler) {
		vy = 0.0;
		if (vx < -fehler)
			vx += r;
		else if (vx > fehler)
			vx -= r;
		else
			vx = 0.0;
	}

	/**
	 * @return
	 */
	public boolean touchesLeftOrRightBorder() {
		return Math.abs(x + vx) > 1.0;
	}

	/**
	 * @param v2kine
	 *            Prozentuales Ding
	 */
	public void bounceBackX(double v2kine) {
		if (vx < 0) { // wenn er an die linke Wand schlaegt
			y += (x + vx + 1) / vx * vy;
			x = -1.0;
		} else {
			y += (x + vx - 1) / vx * vy; // wenn er an die rechte Wand schlaegt
			x = 1.0;
		}
		vx = -vx * (1 - v2kine);
	}

	/**
	 * @return
	 */
	public boolean touchesBottomOrTopBorder() {
		return Math.abs(y + vy) > 1.0;
	}

	/**
	 * @param v2kine
	 */
	public void bounceBackY(double v2kine) {
		if (vy < 0) {
			x += (y + vy + 1) / vy * vx; // wenn er auf den Boden aufschlaegt
			y = -1.0;
		} else {
			x += (y + vy - 1) / vy * vx; // wenn er an die Decke schlaegt
			y = 1.0;
		}
		vy = -vy * (1 - v2kine);
	}

	/**
	 * 
	 */
	public void moveNormally() {
		x = x + vx;
		y = y + vy;
	}

	/**
	 * @param mouseX
	 * @param mouseY
	 */
	public void push(double mouseX, double mouseY) {
		vx = 1. * (x - StdDraw.mouseX());
		vy = 1. * (y - StdDraw.mouseY());
	}

	/**
	 * @param baelle
	 *            ist die Gesamtheit aller Baelle
	 * @param index
	 *            ist Index des aktuellen Balles
	 * @return Ball, mit dem Ball b kollidiert bzw. null wenn keine Kollision
	 */
	public Ball givesBallBCollidesWith(Vector<Ball> baelle, int index) {

		for (int i = 0; i < baelle.size(); i++) {
			if (i != index) {
				Ball c = baelle.get(i);
				if ((c.getRad() + rad + Math.abs(vx))*(c.getRad() + rad + Math.abs(vx)) >= 
						(c.getX() - x)*(c.getX() - x) + (c.getY() - y)*(c.getY() - y) ) return c;
			}
		}

		return null;
	}

	/**
	 * Kehrt die Bewegungen der Baelle, die kollidieren, im Zuge eines elastischen Stosses um
	 * @param c ist der Ball, mit dem kollidiert wird
	 */
	public void collision(Ball c) {
		
		double winkel;
		if (c.getX() != x) 	winkel = Math.atan((c.getY() - y)/(c.getX() - x));	// Winkel phi für Drehung es Koordinatensystems
		else				winkel = Math.PI / 2;								// falls der Nenner 0 wird
		
		double[][] M  = {{  Math.cos(winkel), Math.sin(winkel)},					// Drehmatrix
						 { -Math.sin(winkel), Math.cos(winkel)}};

		double[][] Mi = {{ Math.cos(winkel), -Math.sin(winkel)},				// Inverse zur Matrix M
						 { Math.sin(winkel),  Math.cos(winkel)}};
		
		double vcxn = M[0][0] * vx + M[0][1] * vy;								// berechnet neue Richtung der Geschwindigkeit
		double vbxn = M[0][0] * c.getVx() + M[0][1] * c.getVy();				// im gedrehten Koordinatensystem
		double vbyn = M[1][0] * vx + M[1][1] * vy;								// dabei vertauschen die beiden Baelle
		double vcyn = M[1][0] * c.getVx() + M[1][1] * c.getVy();				// ihre Zentralrichtung
		
		double vbx = Mi[0][0] * vbxn + Mi[0][1] * vbyn;							// dreht die Geschwindigkeiten
		double vby = Mi[1][0] * vbxn + Mi[1][1] * vbyn;							// wieder ins normale Koord.system zurueck
		double vcx = Mi[0][0] * vcxn + Mi[0][1] * vcyn;
		double vcy = Mi[1][0] * vcxn + Mi[1][1] * vcyn;
		
		vx   = vbx;
		vy   = vby;
		c.setVx(vcx);
		c.setVy(vcy);
		
		while ((c.getRad() + rad)*(c.getRad() + rad) >= 						// zieht die ineinander verkeilten Baelle
				(c.getX() - x)*(c.getX() - x) + (c.getY() - y)*(c.getY() - y) ) { // wieder auseinander
			x += Math.signum(vx)*rad;															  // !!! PRODUZIERT NOCH FEHLER
			y += Math.signum(vy)*rad;
		}
		
	}

}
