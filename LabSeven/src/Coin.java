import java.util.*;

// class representing a 2 sided Coin
// only has one method to flip the Coin and
// report what face is up

public class Coin 
{
	Random gen;  

	public Coin(){
		gen = new Random();
	}
	
	public Coin(long seed){
		gen = new Random(seed);
	}

	public int flip() 
	{
		int result = 0;
		boolean randNum = gen.nextBoolean();
		// each face has probability of 1/2
		// randNum >= 0.0 and < 1.0 
		if (randNum == false)
			result = 1;
		if (randNum == true)
			result = 2;
		return result;  // value representing face
	} // end method flip()
}  // end class Coin
