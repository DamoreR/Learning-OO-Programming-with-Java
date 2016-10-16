import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris.  Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author Robert Damore and CSC 143
 */

public class Game
{

	private Grid theGrid;       // the grid that makes up the Tetris board
	private Tetris theDisplay;  // the visual for the Tetris game
	private Shape piece;        // the current piece that is dropping
	private boolean isOver;     // has the game finished?
	private int pieceType;		// each specific shape is numbered for switch
	private Random ran = new Random();


	// possible move directions
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int UP = 3;
	
	/**
	 * Create a Tetris game
	 * @param Tetris the display
	 */
	public Game(Tetris display)
	{
		theGrid = new Grid();
		theDisplay = display;
		pieceType = ran.nextInt(7) + 1;
		updatePiece();
		isOver = false;

	}


	/** 
	 * Draw the current state of the game
	 * @param g the Graphics context on which to draw
	 */
	public void draw(Graphics g)
	{
		theGrid.draw(g);
		if (piece != null)
			piece.draw(g);
	}

	/**
	 * Move the piece in the given direction
	 * @param the direction to move
	 * @throws IllegalArgumentException if direction is not 
	 * one of the class directions
	 */
	public void movePiece(int direction){
		if (piece != null)
			piece.move(direction);
		updatePiece();
		theDisplay.update();
		theGrid.checkRows();    
	}

	/**
	 * Rotate the piece to the next clockwise position
	 */
	public void rotatePiece(){
		if (piece != null)
			piece.rotate(piece.getSquare());
		updatePiece();
		theDisplay.update();
	}

	/**
	 * @return if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid.  Usually happens when a new piece is made
		if (piece == null)
			return false;

		// check if game is already over
		if (isOver)
			return true;

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i <p.length; i++)
			if (theGrid.isSet((int) p[i].getX(), (int) p[i].getY()) ) {
				isOver = true;
				return true;
			}
		return false;
	}

	// Update the piece
	private void updatePiece() {

		// create new piece if last one is done moving
		if (piece == null) {

			//use randomly generated int for random new piece.
			switch(pieceType){
			case 1: 
				piece = new LShapeReversal(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 2:
				piece = new LShape(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 3:
				piece = new ZShape(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 4:
				piece = new ZShapeReversal(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 5:
				piece = new TShape(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 6:
				piece = new LineShape(1, Grid.WIDTH/2 -1, theGrid);
				break;
			case 7:
				piece = new BoxShape(1, Grid.WIDTH/2 -1, theGrid);
				break;

			}

			//change pieceType for next new piece
			pieceType = ran.nextInt(7) + 1;

		} 

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Game.DOWN)) {
			Point [] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i =0; i < p.length; i++){
				theGrid.set((int)p[i].getX(), (int)p[i].getY(), c);
			}
			piece = null;
		}

	}

}
