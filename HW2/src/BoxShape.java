import java.awt.Color;

/**
 * A Box-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration
 *              Sq Sq<br>
 *              Sq Sq<br>
 * 
 * @author Robert Damore
 */

public class BoxShape extends Shape {

	/**
	 * Create a Box-Shape piece.  See Shape class description for actual location
	 * of r and c
	 * @param r row location for this piece
	 * @param c column location for this piece
	 * @param g the grid for this game piece
	 * 
	 */
	public BoxShape(int r, int c, Grid g) {
		super();
		setShape(r, c, g, this.getSquare());
	}

	/**
	 * Initial setup for arranging the squares that make up this piece
	 * @param r row location for this piece
	 * @param c column location for this piece
	 * @param g the grid for this piece
	 * @param s the array of squares that make up the piece
	 */
	public void setShape(int r, int c, Grid g, Square[] s) {
		// Create the squares in the Line shape
		s[0] = new Square(g, r - 1, c, Color.black, true);
		s[1] = new Square(g, r - 1, c + 1, Color.black, true);
		s[2] = new Square(g, r, c, Color.black, true);
		s[3] = new Square(g, r, c + 1, Color.black, true);
	}

	/**
	 * Rotation of a square doesn't change anything
	 */
	@Override
	public void rotate(Square[] s) {
		
	}

}
