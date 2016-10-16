/**
 * WrongTreeStateException is an exception thrown when QueryTree methods are being called in the wrong order. 
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

@SuppressWarnings("serial")
public class WrongTreeStateException extends Throwable {
	public WrongTreeStateException(String s){
		super(s);
	}
}