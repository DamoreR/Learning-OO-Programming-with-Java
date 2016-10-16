import java.util.*;

// class representing a 6 sided die
// only has one method to roll the die and
// report what face is up

public class Die 
{
	static Random gen = new Random();  

// no constructor needed - no initialization required

	public int roll() 
	{
		int randNum = gen.nextInt(6) + 1;
		
		return randNum;  // value representing face
	} // end method roll()
}  // end class Die
