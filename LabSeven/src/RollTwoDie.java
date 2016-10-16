public class RollTwoDie {

// Driver program to instantiate two die, roll both
// die a large number of times and show the results
// in a 6 x 6 array

	public static void main(String[] args) {
		
		final int NUM_ROLLS = 100000;   // # of rolls
		Die die1 = new Die();
		Die die2 = new Die();
		int result1, result2;		// result of rolls
		final int NUM_FACES = 6;
		int[][] rollTable = new int[NUM_FACES][NUM_FACES];
		
		// initialize table
		for (int i = 0; i < NUM_FACES; i++)  
			for(int j = 0; j < NUM_FACES; j++)
				rollTable[i][j] = 0;
				
		// roll dice		
		for (int i = 0; i < NUM_ROLLS; i++) {															 
			result1 = die1.roll();
			result2 = die2.roll();
			rollTable[result1-1][result2-1]++;
		}  // end for 
		
		// print results of all rolls
		System.out.println("\nResults Array (Die1-Rows;" + 
													" Die2-Columns):\n");
		for (int i = 0;i < NUM_FACES; i++) {
			for (int j = 0; j < NUM_FACES; j++)
				System.out.print(rollTable[i][j] + "   ");
			System.out.println();
		} // end for
		
	} // end method main()          
}  // end class RollTwoDie 
