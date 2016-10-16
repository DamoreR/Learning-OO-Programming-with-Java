public class FlipTwoCoins 
{

// Driver program to instantiate two coins, flip both
// coins a large number of times and show the results
// in a 2 x 2 array

	public static void main(String[] args)
	{
		final int NUM_ROLLS = 100000;   // # of rolls
		Coin coin1 = new Coin(4);
		Coin coin2 = new Coin();
		int result1, result2;		// result of rolls
		final int NUM_FACES = 2;
		int[][] rollTable = new int[NUM_FACES][NUM_FACES];
		
		// initialize table
		for (int i = 0; i < NUM_FACES; i++)  
			for(int j = 0; j < NUM_FACES; j++)
				rollTable[i][j] = 0;
		// flip coins		
		
		for (int i = 0; i < NUM_ROLLS; i++)
		{															 
			result1 = coin1.flip();
			result2 = coin2.flip();
			rollTable[result1-1][result2-1]++;
		}  // end for 
		
		// print results of all rolls
		System.out.println("\nResults Array (Coin1-Rows;" + 
													" Coin2-Columns):\n");
		for (int i = 0;i < NUM_FACES; i++)
		{
			for (int j = 0; j < NUM_FACES; j++)
							System.out.print(rollTable[i][j] + "   ");
			System.out.println();
		} // end for
		
	} // end method main()          
}  // end class FlipTwoCoins 
