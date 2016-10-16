import java.awt.Color;

/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration
 *              Sq<br>
 *              Sq<br>
 *              Sq Sq<br>
 * 
 * @author Robert Damore
 */

public class LShape extends Shape {
	
	private int position;	//rotation of the piece
	private boolean[][] localGrid;	//boolean map of local grid surrounding the piece (true for set Square)

	/**
	 * Create an L-Shape piece.  See Shape class description for actual location
	 * of r and c
	 * @param r row location for this piece
	 * @param c column location for this piece
	 * @param g the grid for this game piece
	 * 
	 */
	public LShape(int r, int c, Grid g) {
		super();
		setShape(r, c, g, this.getSquare());
		this.position = 1;
		this.localGrid = new boolean[3][3];
	}

	/**
	 * Initial setup for arranging the squares that make up this piece
	 * @param r row location for this piece
	 * @param c column location for this piece
	 * @param g the grid for this piece
	 * @param s the array of squares that make up the piece
	 */
	public void setShape(int r, int c, Grid g, Square[] s) {
		// Create the squares in the L shape
		s[0] = new Square(g, r - 1, c, Color.magenta, true);
		s[1] = new Square(g, r, c , Color.magenta, true);
		s[2] = new Square(g, r + 1, c, Color.magenta, true);
		s[3] = new Square(g, r + 1, c + 1, Color.magenta, true);
	}

	/**
	 * Rotate the piece into the next clockwise position
	 * and change the position number
	 */
	@Override
	public void rotate(Square[] s) {

		//Map out the squares surrounding this piece
		//using the location of a central square in the piece
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				try{
					localGrid[i][j] = s[1].getGrid().isSet(s[1].getRow() - 1 + i, s[1].getCol() - 1 + j);
				}catch(ArrayIndexOutOfBoundsException e){
					localGrid[i][j] = true;
				}
			}
		}
		
		//Check local Grid to see if anything is in the way
		//and then rotate this piece based on the current position
		switch(position){
		case 1:
			if (!localGrid[0][2] && 
				!localGrid[1][0] && !localGrid[1][2] &&
				!localGrid[2][0]){

				s[0].move(Game.RIGHT);
				s[2].move(Game.LEFT);
				s[3].move(Game.LEFT);

				s[0].move(Game.DOWN);
				s[2].move(Game.UP);
				s[3].move(Game.LEFT);

				position = 2;
			}	
			break;

		case 2:
			if (!localGrid[0][0] && !localGrid[0][1] && 
				!localGrid[2][1] && !localGrid[2][2]){

				s[0].move(Game.DOWN);
				s[2].move(Game.UP);
				s[3].move(Game.UP);

				s[0].move(Game.LEFT);
				s[2].move(Game.RIGHT);
				s[3].move(Game.UP);

				position = 3;
			}	
			break;

		case 3:
			if (!localGrid[0][2] &&
				!localGrid[1][0] && !localGrid[1][2] &&
				!localGrid[2][0]){

				s[0].move(Game.LEFT);
				s[2].move(Game.RIGHT);
				s[3].move(Game.RIGHT);

				s[0].move(Game.UP);
				s[2].move(Game.DOWN);
				s[3].move(Game.RIGHT);

				position = 4;
			}	
			break;

		case 4:
			if (!localGrid[0][0] && !localGrid[0][1] && 
				!localGrid[2][1] && !localGrid[2][2]){

				s[0].move(Game.UP);
				s[2].move(Game.DOWN);
				s[3].move(Game.DOWN);

				s[0].move(Game.RIGHT);
				s[2].move(Game.LEFT);
				s[3].move(Game.DOWN);

				position = 1;
			}	
			break;
		}//end of switch

	}//end of rotate
}
