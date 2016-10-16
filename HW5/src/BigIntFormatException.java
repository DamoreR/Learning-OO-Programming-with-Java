
/**
 * BigIntFormatException is an Exception that is thrown for invalid
 * BigInt constructor input
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

@SuppressWarnings("serial")
public class BigIntFormatException extends Throwable{
	public BigIntFormatException(){
		super ("Not Big Int");
	}
}