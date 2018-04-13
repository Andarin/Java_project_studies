import gdp.stdlib.StdDraw;	

public class Teilerdichte {

			public static void main(String[] args) {
				int n=10;
				
				StdDraw.setCanvasSize(1024, 256);
 				StdDraw.setXscale(0, n);
 				StdDraw.setYscale(0, 1);
				
 				int count, count2;
				double k,l;
				for (int i = 2; i<=n; i++) {
					k=l=i;
					count = 0;
					count2 = 0;
					for (int j = 2; j<=k; j++) {
						boolean test=true;
						// zaehlt alle potentiellen Teiler
						while (k/j>=1) {
								count2++;
								k=k/j;

						};
						// zaehlt alle richtigen Teiler
						while (test) {
							if (l%j == 0) {
								count++;
								l=l/j;
							}
							else test=false;
						};
						k=i;
						if (l==1) break;
					};
					if (count==1) count=0;
					
					System.out.print(i + " ");
 					System.out.println((count*1.0)/count2);
					
 					StdDraw.setPenRadius(1);
 					StdDraw.setPenColor(StdDraw.BLACK);
 					StdDraw.pixel(i, (count*1.0)/count2);
 					
				};
				
				
				
			}

	}
