import java.awt.Graphics;

/**
 * This interface is to be implemented any time you want to 
 * create a KochCurve and draw it on a specific Swing Component
 * in a GUI.
 * 
 * @author Robert Damore
 *
 */

public interface UseKochCurve {

	/**
	 * Use this method to create a new KochCurve
	 * in the given graphics.
	 * @param g graphics where curve will be drawn
	 */
	public void drawNewCurve(Graphics g);
	
	/**
	 * Use this method to call updateCurve method of a KochCurve object
	 * with the given graphics and the level you want to update it to.
	 * @param g graphics containing drawing of curve
	 */
	public void updateCurve(Graphics g);
	
}
