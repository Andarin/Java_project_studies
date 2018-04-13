


/*************************************************************************
 *  Compilation:  javac StdRandom.java
 *  Execution:    java StdRandom
 *  
 *************************************************************************/

public class StdRandom {

    // return a real number uniformly between 0 and 1
    public static double uniform() {
        return Math.random();
    } 

    // return a real number uniformly between a and b
    public static double uniform(double a, double b) {
        return a + Math.random() * (b-a);
    } 

    // return an integer uniformly between 0 and N-1
    public static int uniform(int N) {
        return (int) (Math.random() * N);
    } 

    // return a boolean, which is true with prob p and false otherwise
    public static boolean bernoulli(double p) {
        return Math.random() < p;
    } 

    // return a real number with a standard Gausian distribution
    public static double gaussian() {
        double r, x, y;
        do {
            x = uniform(-1.0, 1.0);
            y = uniform(-1.0, 1.0);
            r = x*x + y*y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);

        // Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
        // is an independent random gaussian
    } 

    // return a real number from a gaussian distribution with given mean and stddev
    public static double gaussian(double mean, double stddev) {
        return mean + stddev * gaussian();
    } 

    // return i with probability a[i]
    // precondition: sum of array entries equals 1
    public static int discrete(double[] a)  { 
        double r = Math.random();
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];
            if (sum >= r) return i;
        }
        assert(false);
        return -1;
    } 

    // exponential random variable with rate lambda
    public static double exp(double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }


    // swaps array elements i and j
    public static void exch(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // take as input an array of objects and rearrange them in random order
    public static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + uniform(N-i);     // between i and N-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    // take as input an array of doubles and rearrange them in random order
    public static void shuffle(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + uniform(N-i);     // between i and N-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }


    /* Unit Test
     * with output on StdOut
     */
    public static void main2(String[] args) { 
        int N = Integer.parseInt(args[0]);

        double[] t = { .5, .3, .1, .1 };

        for (int i = 0; i < N; i++) { 
            StdOut.printf(" %2d " , uniform(100));
            StdOut.printf("%8.5f ", uniform(10.0, 99.0));
            StdOut.printf("%5b "  , bernoulli(.5));
            StdOut.printf("%7.5f ", gaussian(9.0, .2));
            StdOut.printf("%2d "  , discrete(t));
            StdOut.println();
        }
    }
 

    
    /* Unit Test
     * with graphical visualization
     */
    public static void main(String[] args) { 
    	
    	StdDraw.clear();
    	StdDraw.square(0.5,0.5,0.5);
    	StdDraw.text(0.5, 0.95, "Test 1: uniform()");
    	for (int i=0; i<2000; i++)  {
    		double x = uniform();
    		double y = uniform();
    		StdDraw.point(x,y);
    	}
    	while (!StdDraw.mousePressed());


    	StdDraw.clear();
    	StdDraw.square(0.5,0.5,0.5);
    	StdDraw.text(0.5, 0.95, "Test 2: x= uniform(0.2,0.8), y=uniform(0.4,0.6)");
    	for (int i=0; i<2000; i++)  {
    		double x = uniform(0.2,0.8);
    		double y = uniform(0.4,0.6);
    		StdDraw.point(x,y);
    	}
    	while (!StdDraw.mousePressed());
    
    	
    	StdDraw.clear();
    	StdDraw.square(0.5,0.5,0.5);
    	StdDraw.text(0.5, 0.95, "Test 3: bernoulli(0.5)");
    	for (int i=0; i<1000; i++)  {
    		double x = i*0.001;
    		double y;
    		if (bernoulli(0.5)) y = 0.2;
    		else				y = 0.3;;
    		StdDraw.point(x,y);
    	}
    	while (!StdDraw.mousePressed());
    
    	
    	StdDraw.clear();
    	StdDraw.square(0.5,0.5,0.5);
    	StdDraw.text(0.5, 0.95, "Test 4: gaussian(0.5,0.17)");
    	for (int i=0; i<5000; i++)  {
    		double x = gaussian(0.5, 0.17);
       		double y = gaussian(0.5, 0.17);
    		StdDraw.point(x,y);
    	}
    	while (!StdDraw.mousePressed());

     }
} 
