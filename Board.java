/*
 * Name: Ali Sartaz Khan
 * Class: CSc 335
 * Description: Makes the Board object than creates a 2D list
 * of all the cards that are randomized for every game.
 */

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	private Card[][] board;
	ArrayList<Card> cards;
	private int rows;
	private int columns;
	
	
	public Board(int rows, int columns) {
		/*
		 * Constructor initializes the variables and accepts 2 paramters.
		 * rows: number of rows
		 * columns: number of columns
		 */
		this.rows = rows;
		this.columns = columns;
		board = new Card[rows][columns];
		cards= new ArrayList<>();
		addCards(cards);
		populateBoard(board, cards);
		
		
	}
	
	
	private void addCards(ArrayList<Card> cards2) {
		/*
		 * Cards all the cards to the arraylist using a set number of names and 
		 * images.
		 * cards2: Arraylist of cards
		 */
		
		String[] names = {"apple", "pear", "peach", "pineapple", "greenapple", "avocado",
				"apple", "pear", "peach", "pineapple", "greenapple", "avocado"};
		String[] paths = {"apple.jpg", "pear.jpg", "peach.jpg", "pineapple.jpg", "greenapple.jpg","avocado.jpg",
				"apple.jpg", "pear.jpg", "peach.jpg", "pineapple.jpg", "greenapple.jpg","avocado.jpg"};


		for(int i=0; i<(rows*columns); i++)
		{
			cards2.add(new Card(names[i], paths[i]));
		}
		
	}

	public void populateBoard(Card[][] board, ArrayList<Card> cards) {
		/*
		 * Fills up the 2D board array using random cards by calling another function.
		 * board: 2D boards array
		 * cards: arrayList containing all cards
		 */
		for(int i=0; i<board.length; i++)
		{
			for (int j=0; j<board[i].length;j++)
			{
				board[i][j] = getRandomCard(cards);
			}
		}
	}

	private Card getRandomCard(ArrayList<Card> cards) {
		/*
		 * Returns a random card from the cards list
		 * cards: Arraylist containing all cards
		 */
		Random rand = new Random();
		int i = rand.nextInt(cards.size());
		Card card = cards.get(i);
		cards.remove(i);
		return card;
		
	}
	
	public Card[][] getBoard(){
		/*
		 * Returns the 2D array for board
		 */
		return board;
	}
}
