/* Michele Pashby
 * 30335753
 * mpashby19@cmc.edu
 */

public class Quick {

    private static boolean debug = true;
    private static boolean debug2 = true;

    public static void qsort(int[] a) {
        quickSort(a, 0, a.length - 1, 0);
    }

    private static void quickSort(int a[], int from, int to, int lev) {
    	// Check the base case first
    	if (from < to) {   // low < high
	// Determine the pivot element
        int p = from;  // First element in Array
        
        int[] three = {0, 0, 0};  // to get values from partition

        partition(p, from, to, a, three);

        int nsp = three[0];  // # of elements smaller than p (see partition)
        int nep = three[1];  // # of elements equal to p including p
        int ngp = three[2];  // # of elements greater than p
	
	// Find new from and to for two recursive calls
        int newFrom1, newFrom2, newTo1, newTo2;
        int pind = from + nsp+ nep-1;  // new pivot index

        //  set the four variables before the recursive calls
        newFrom1 = from;      
        newFrom2 = pind+1;
        newTo1 = pind-1;
        newTo2 = to;
              
        quickSort(a, newFrom1, newTo1, lev + 1);
        quickSort(a, newFrom2, newTo2, lev + 1);
    	}
    }
    

    public static void partition(int p, int from, int to, int[] a, int[] three) {  //p = from    
    	int i = p;     // i starts at pivot
    	int j = to;    // ends at lest element 
    	int less = 0;  // values less than pivot
    	int eq = 0;    // values equal to pivot, including the pivot
      	int gre = 0;   // values greater than pivot
      	
      	// count less than, = to, and greater than for three array
      	for (int k = p; k<= to; k++) { // includes pivot
      		if (a[k] < a[p]) {         // less than p
      			less ++;
      		} else if (a[k] > a[p]) {  //greater than p
      			gre ++; 
      		} else {                   // equal to p
      			eq ++;
      		}
      	}
      	while (i< j) { 
      			//printArray(a, "\t");
      			while (a[i] <= a[p] && i <j ) {  //left side is smaller/ equal than pivot
      				i++; 
      			}
      			while (a[j] > a[p] && j> i) {  // right side greater than pivot   
      				j--;
      			}
      			if (i<j) {    // when two values are out of place
      				swap(a, i, j);
      			}
      		}   
      		
      		if (gre >0 && less > 0) { // pivot is not smallest or largest
      			swap(a, from + less + eq-1, p);
      		}  else if (gre <= 0 ) {  // pivot is greatest number
      			swap(a, to, p); //put as last element
      		} // else when pivot is smallest number: no change
      
      three[0] = less; // # of elements smaller than p
  	  three[1] = eq;  // # of elements equal to p
  	  three[2] = gre; // # greater than p
    }  
    
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    
    private static void printFromToPivot(int from, int to, int p, int lev) {
        for (int i = 0; i < lev; i++) {
            System.out.print("    ");
        }
        System.out.println(from + "--" + to + " p " + p);
    }

    private static void printArray(int[] a, String msg) {
        System.out.print("\n" + msg);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private static void printArrayIndent(int[] a, String msg, int lev) {
        for (int i = 0; i < lev; i++) {
            System.out.print("   ");
        }
        System.out.print(msg);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n\n");
    }

    // Returns an array of n random integers.  The integers are
    // chosen from the interval [0..n)
    public static int[] randomArray (int n) {
        int[] A;
        A = new int[n];
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < n; i++) {
            A[i] = r.nextInt(n * 10);
        }
        return A;
    }


    public static void benchmark(int size, int repts) {  // find how long quick sort takes
        long sumTime = 0;
        System.out.println();
        for (int i = 0; i < repts; i++) {
            int[] a = randomArray(size);
	    	    printArray(a, "Before sorted: ");
            System.out.println("Quick sort run number " + (i+1) + " started (size: " + size + ")");
            long start = System.currentTimeMillis();
            qsort(a);
            long timeTaken = (System.currentTimeMillis() - start);
            sumTime = sumTime + timeTaken;
	    	    printArray(a, "  After sorted: ");
        }
        System.out.println("Quick sort took " + (sumTime/(long)repts) + " milliseconds.");
    }


    public static void main (String[] args) {
        
        // Quick Sort test cases
    	
    	 int[] M1 = {5, 6, 3, 5, 8, 7, 6, 5, 9, 2};
    	 printArray(M1, "Before sorted");
    	 qsort(M1);
    	 printArray(M1, "after sorted");
        System.out.println();
       

	// Quick sort tests
    	
        int[] numbers1 = {9, 6, 2, 11, 10, 4, 8, 6, 6, 5, 9, 13, 12, 1};
        int[] three = {0, 0, 0};
        
        printArray(numbers1, "Before sorted: ");
        qsort(numbers1);
        printArray(numbers1, " After sorted: ");  

       
        numbers1 = randomArray(20);
        printArray(numbers1, "Before sorted: ");
        qsort(numbers1);
        printArray(numbers1, " After sorted: "); 
        
        
	    benchmark(99, 1);  
    }
}

