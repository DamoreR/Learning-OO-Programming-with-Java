import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * This is a Koch Curve, or snow flake shape. It will draw and update the levels 
 * of the curve on the given graphics, with the given start and end points. 
 * The level cannot go below 0 (straight line) or above 6. 
 * 
 * @author Robert Damore
 *
 */

public class KochCurve{
	
	private int level;						//level of the Koch Curve
	private Point2D.Double start, end;		//start and end points of the curve
	
	/**
	 * Creates a new Koch Curve at level 0. The curve is then drawn
	 * onto the given graphics using the drawCurve method.
	 * 
	 * @param start starting point
	 * @param end ending point
	 * @param g graphics for Koch Curve to be drawn on
	 */
	public KochCurve(Point2D.Double start, Point2D.Double end, Graphics g) {
		this.level = 0;
		this.start = start;
		this.end = end;
		drawCurve(start, end, level, g);
	}

	/**
	 * Updates the curve to the given level. The updated curve is 
	 * then drawn onto the given graphics using the drawCurve method.
	 * 
	 * @param level level to update the curve to
	 * @param g graphics for Koch Curve to be drawn on
	 */
	public void updateCurve(int level, Graphics g){
		this.level = level;
		drawCurve(this.start, this.end, this.level, g);
	}
	
	/*
	 * Recursive method for drawing the Koch Curve.
	 * Initial call with level 0 will draw a straight 
	 * line from start to end point on given graphics. 
	 * If the level, k, is over 0, the line is split into 
	 * thirds, a bump is created in the middle, and each
	 * new line segment is treat as another Koch Curve 
	 * with level k-1.
	 */
	private void drawCurve(Point2D.Double start, Point2D.Double end, int level, Graphics g){
		//base case
		if(level == 0){			
			g.drawLine((int)Math.round(start.x), (int)Math.round(start.y), (int)Math.round(end.x), (int)Math.round(end.y));
		}
		//recursive case
		else{
			//find three points of the bump
			Point2D.Double firstThird = new Point2D.Double(start.x + (end.x - start.x) / 3, start.y + (end.y - start.y) / 3);
			Point2D.Double secondThird = new Point2D.Double(start.x + (end.x - start.x) * 2 / 3, start.y + (end.y - start.y) * 2 / 3);
			Point2D.Double newPeak = new Point2D.Double(firstThird.x + (secondThird.x - firstThird.x) * Math.cos(Math.PI / 3) - (firstThird.y - secondThird.y) * Math.sin(Math.PI / 3), firstThird.y - (secondThird.x - firstThird.x) * Math.sin(Math.PI / 3) - (firstThird.y - secondThird.y) * Math.cos(Math.PI / 3));
			//recursively call this method on each new line segment
			drawCurve(start, firstThird, level - 1, g);
			drawCurve(firstThird, newPeak, level - 1, g);
			drawCurve(newPeak, secondThird, level - 1, g);
			drawCurve(secondThird, end, level - 1, g);
		}
	}
	
	/**
	 * @return level of this KochCurve
	 */
	public int getLevel(){
		return this.level;
	}
}
