import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The Shape of a piece in the Tetris game. 
 * The game piece "floats above" the Grid.  
 * The piece must be a specific Shape,
 * so this is an abstract class for all Shapes.
 * The (row, col) coordinates used to create
 * each shape is the middle Square in the 
 * second row of the Grid.
 * 
 * @author Robert Damore and CSC 143 
 */

public abstract class Shape
{
	private boolean ableToMove;    // can this piece move
	private Square[] square;       // the Squares that make up this piece
	// Made up of PIECE_COUNT squares

	// number of squares in 1 Tetris game piece
	private static final int PIECE_COUNT = 4;

	/**
	 * Create a Shape through a specific shape
	 */
	public Shape()
	{
		square = new Square[PIECE_COUNT];
		ableToMove = true;
	}

	/**
	 * Draw the piece on the given Graphics context
	 */
	public void draw(Graphics g){
		for (int i = 0; i < PIECE_COUNT; i++)
			square[i].draw(g);
	}

	/**
	 * Move the piece if possible.
	 * Freeze the piece if it cannot move down anymore.
	 * @param direction the direction to move
	 * @throws IllegalArgumentException if direction is not Game.DOWN,
	 * Game.UP, Game.LEFT, or Game.RIGHT
	 */
	public void move(int direction){
		if (canMove(direction)){
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}       
		// if we couldn't move, see if its because we're at the bottom
		else if (direction == Game.DOWN){
			ableToMove = false;
		}
	}

	/** Return the (row,col) grid coordinates occupied by this Piece
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations(){
		Point[] points = new Point[PIECE_COUNT];
		for(int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/** 
	 * @return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	/**
	 * @return whether this piece can move in the given direction
	 * @throws IllegalArgumentException if direction is not Game.DOWN,
	 * Game.UP, Game.LEFT, or Game.RIGHT
	 */
	public boolean canMove(int direction) {
		if (!ableToMove)
			return false;

		//Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}

	/**
	 * @return the Squares that make up this piece
	 */
	public Square[] getSquare(){
		return square;
	}

	/**
	 * Rotate the piece
	 * @param s array of Squares that make up piece
	 */
	public abstract void rotate(Square[] s);

}
