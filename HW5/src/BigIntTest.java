import junit.framework.TestCase;

/** Class to test BigInt.java
*
* SOME NOTES:
* You need to have toString() implemented with the constructors in order to test thime with this file
* you need compareTo() implemented early in order to test the arithmetic operations
* You need to implement BigIntFormatException (a RuntimeException) for testing the constructors
*/


public class BigIntTest extends TestCase {

  /**
   * Testing String constructor and toString
   */
  public void testStringConstructorsAndToString() {
    String[] s = new String[] {
      "-1424",
        "  +14324",
        "    142432",
        "\n\t2402\n\t\r",
		"   0   ",
        "   28402437802743027340273402734027340273402734027340732407204702740274027430732073027430730" };
    String[] trim = {
      "-1424",
      "14324",
      "142432",
      "2402",
      "0",
      "28402437802743027340273402734027340273402734027340732407204702740274027430732073027430730" };
    
    for (int i = 0; i < s.length; i++) {
      BigInt b;
	try {
		b = new BigInt(s[i]);      
		assertTrue("Problem with the string: "+ s[i], b.toString().equals(trim[i]));
	} catch (BigIntFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
  }

/** 
   * Test copy constructor
   */
  public void testCopyConstructor(){
    BigInt b;
	try {
		b = new BigInt("34972374932");
		BigInt c = new BigInt(b);
	    assertTrue(c != b);   // they're not the same object
	    assertTrue("Copy constructor error", b.toString().equals(c.toString()));   // but contain the same value
	    
	  //another test
	    b = new BigInt("-123456789");
	    c = new BigInt(b);
	    assertTrue(c != b);   // they're not the same object
	    assertTrue("2nd Copy constructor test error", b.toString().equals(c.toString()));   // but contain the same value
	} catch (BigIntFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  /** 
   * Test BigInt(long)
   */
  public void testLongConstructor(){
    BigInt b = new BigInt(-142423424l);
    assertTrue("Long constructor test 1 error", b.toString().equals("-142423424"));
    b = new BigInt(+402480848404382l);
    assertTrue("Long constructor test 2 error", b.toString().equals("402480848404382"));
    
    b = new BigInt(0l);
    assertTrue("Long contructor test 3 error", b.toString().equals("0"));
  }
  
  
  /** 
   * Testing that constructors will catch precondition errors and throw the correct exception
   *  BigIntFormatException must be defined in order for this to work (extend RuntimeException)
   */
  public void testConstructorFail(){
    // all these strings are illegal and should throw a BigIntFormatException when used to initialize a BigInt object
 
    String[] s = { " - - 1424", "+ + 14324", "a142432", "1432 3413",
      "+242134.32421", "", "    \n", "\t ++" };
    for (int i = 0; i < s.length; i++) {
      try {
        BigInt b = new BigInt(s[i]);
        
        // if we got to this point, that is an error. The constructor SHOULD have thrown an exception
        assertFalse("Error with the string: " + s[i], true);
      } catch (BigIntFormatException e) {
        // got here correctly, keep going
        assertTrue(true);
      }
      catch (Exception e){
        // an exception was thrown, but the wrong type
        assertFalse("Wrong exception type thrown by String Constructor", true);
      }
    }
  }
  
  /**
   * Testing compareTo
   * This is needed for testing all the arithmetic methods
   */
  public void testCompareTo() {
    BigInt b1;
	try {
		b1 = new BigInt(
		                       "840832400834083240283408234027590723407835035073250278340782340782");
		assertTrue(b1.compareTo(b1) == 0);
	    BigInt copy = new BigInt(b1);
	    assertTrue(copy.compareTo(b1) == 0);
	    BigInt b2 = new BigInt(
	                           "840832400834083240283408234027590723407835035073250278340782340783");
	    assertTrue(b1.compareTo(b2) < 0);
	    assertTrue(b2.compareTo(b1) > 0);
	    BigInt b3 = new BigInt("-37240782304320488303");
	    BigInt b4 = new BigInt("-37240782304320488304");
	    assertTrue(b3.compareTo(b4) > 0);
	    assertTrue(b3.compareTo(b1) < 0);
	} catch (BigIntFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
  
  /**
   * Testing the Add method
   * Does it add properly
   * Does it NOT change this and the parameter
   */
  public void testAdd() {
    
    // test 
    BigInt b1;
	try {
		b1 = new BigInt("374073204787074307340730730730730739023679628265");
		BigInt b2 = new BigInt("-374073204787074307340730730730730739023679628265");
	    
	    BigInt sum = b1.add(b2);
	    assertTrue("Adding oppositese should equals 0", 
	               sum.compareTo(new BigInt("0"))== 0);
	    
	    sum = b1.add(new BigInt(0));
	    assertTrue("adding 0 shouldn't change the original value", 
	               sum.compareTo(b1) == 0);
	    
	    // adding two positive BigInts  (15 9's)
	    b1 = new BigInt("999999999999999");
	    b2 = new BigInt(1);
	    
	    sum = b2.add(b1);
	    assertTrue("problem adding 2 positive numbers",
	               sum.compareTo(new BigInt("1000000000000000")) == 0);
	    
	    //test that adding didn't change parameter
	    assertTrue("adding changed parameter",
	               b1.compareTo(new BigInt("999999999999999")) == 0);
	    
	    //adding negative and positive BigInts
	    b1 = new BigInt("-10000000000");   // 10 zeros
	    b2 = new BigInt(1);

	    // negative + positive
	    sum = b1.add(b2);
	    assertTrue("problem adding 1 to a negative",
	               sum.compareTo(new BigInt("-9999999999")) == 0);
	    
	    // did adding change this?
	    assertTrue("adding changed this",
	               b1.compareTo(new BigInt("-10000000000")) == 0);
	    
	    // positive + negative
	    sum = b2.add(b1);
	    assertTrue("problem adding negative to 1", 
	               sum.compareTo(new BigInt("-9999999999")) == 0);
	    
	    // adding two negative  BigInts
	    b1 = new BigInt("-999999999999");  // 12 9's
	    b2 = new BigInt(-9);
	    sum = b2.add(b1);
	    assertTrue("Problem adding 2 negatives",
	               sum.compareTo(new BigInt("-1000000000008")) == 0);
	} catch (BigIntFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
  
  /**
   * testing negate
   * did it NOT change this?
   */
  public void testNegate(){
    BigInt b1 = new BigInt(-1111);
    BigInt b2 = new BigInt(4444);
    
    BigInt n1 = b1.negate();
    BigInt n2 = b2.negate();
    
    assertTrue("didn't work negative to positive", n1.compareTo( new BigInt(1111)) == 0);
    assertTrue("didn't work positive to negative", n2.compareTo( new BigInt(-4444)) == 0);
      
    // ensure didn't change originals
   assertTrue("negate changed original", b1.compareTo(new BigInt(-1111)) == 0);
  }
}