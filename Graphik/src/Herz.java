

/**
 * Erstellt am: 20.11.2010 / 12:04:44
 * Erstellt von: Luc
 * Projektname: Graphik
 * Warum das ganze? 42
 */

/**
 * @author Luc
 */


public class Herz {


	public static void main(String[] args) {
		
		double x = 0.5 , y = 0.5, xalt = 0.5, yalt = 0.5, xm=1, ym=1;
		
		StdDraw.setXscale(0, xm);
		StdDraw.setYscale(0, ym);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.03);
		
//		for (int i = 0; i < 8000; i++) {
//			double r = StdRandom.uniform(0.1,0.9);
//			double q = StdRandom.uniform(0.3,0.8);
//			StdDraw.point(r, q);
//			StdDraw.picture(xm/2, ym/2, "herz2.gif", xm*1.1, ym*1.1);
//		}
		
		//Zuf�llige Punkte
		 for (int i = 0; i < 3000; i++) {
			double r = Math.random();
			double q = Math.random();
			
			
			
			if 		(x < xm && r < 0.25) x += xm/5*q;
			else if (x > 0  && r < 0.50) x -= xm/5*q;
			else if (y < ym && r < 0.75) y += ym/5*q;
			else if (y > 0)				 y -= ym/5*q;
		
			StdDraw.filledCircle(x, y, q*xm/20);
			StdDraw.picture(xm/2, ym/2, "Bild27.png", xm*1.1, ym*1.1);
			}

		 
		/* In Linien aufbauen 
StdDraw.setPenColor(StdDraw.RED);
StdDraw.setPenRadius(0.01);
		for (int i = 0; i < 3000; i++) {
			double r = Math.random();
			double q = Math.random();
			
			xalt = x;
			yalt = y;
			
			if 		(x < 1 && r < 0.25) x += 0.1*q;
			else if (x > 0 && r < 0.50) x -= 0.1*q;
			else if (y < 1 && r < 0.75) y += 0.1*q;
			else if (y > 0)				y -= 0.1*q;
		
			StdDraw.line(xalt, yalt, x, y);
			StdDraw.picture(0.5, 0.5, "herz3.gif");
			}
	*/
		
		  System.out.println("Fertig!");
	}

}

