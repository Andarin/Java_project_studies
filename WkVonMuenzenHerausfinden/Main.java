import java.math.BigInteger;

/*
 * Modell: Es gibt 5 Messreihen, die von 2 gezinkten Muenzen stammen. Man weiß nicht, welche Messreihen
 * von welcher Muenze stammen, und will nun die wahrscheinlichsten Wk der Muenzen herausfinden.
 */

public class Main {

public static int iteration = 100;
public static int[][] messreihe = 	{{1,0,0,0,1,1,0,1,0,1},
									 {1,1,1,1,0,1,1,1,1,1},
									 {1,0,1,1,1,1,1,0,1,1},
									 {1,0,1,0,0,0,1,1,0,0},
									 {0,1,1,1,0,1,1,1,0,1}};

public static double wk1 = 0.9, wk2 = 0.8;

	public static void main(String[] args) {
		int[] sumarray = new int[5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				sumarray[i] += messreihe[i][j];
			}
		}
		
		double a1sum = 0, a2sum = 0, b1sum = 0, b2sum = 0;
		
		for (int i = 0; i < iteration; i++) {
			for (int row = 0; row < 5; row++) {
				double a = binCoeff(messreihe[row].length,sumarray[row]).longValue()*Math.pow(wk1,sumarray[row])*Math.pow(1-wk1,messreihe[row].length-sumarray[row]);
				double b = binCoeff(messreihe[row].length,sumarray[row]).longValue()*Math.pow(wk2,sumarray[row])*Math.pow(1-wk2,messreihe[row].length-sumarray[row]);
				double c = a+b;
				a = a/c;
				b = b/c;
				double a1 = a*sumarray[row];
				double a2 = a*(messreihe[row].length-sumarray[row]);
				double b1 = b*sumarray[row];
				double b2 = b*(messreihe[row].length-sumarray[row]);
				a1sum += a1;
				a2sum += a2;
				b1sum += b1;
				b2sum += b2;
			}
			wk1 = a1sum / (a1sum + a2sum);
			wk2 = b1sum / (b1sum + b2sum);
			a1sum = 0; 
			a2sum = 0;
			b1sum = 0;
			b2sum = 0;
			
			System.out.print(i+" Wk1 = "+wk1+"  ");
			System.out.println("Wk2 = "+wk2);
		}
	}
	
	public static BigInteger binCoeff(int n, int k)
	{
	    if ((n < 0) || (k < 0) || (k > n))
	        throw new IllegalArgumentException(n + ", " + k);
	    if (k > n/2) k = n - k;
	 
	    BigInteger result = BigInteger.ONE;
	 
	    for (int i = n - k + 1; i <= n; i++)
	        result = result.multiply(new BigInteger("" + i));
	    for (int i = 2; i <= k; i++)
	        result = result.divide(new BigInteger("" + i));
	 
	    return result;
	}

}
