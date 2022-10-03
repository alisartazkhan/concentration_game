/*
 * Name: Ali Sartaz Khan
 * Class: CSc 335
 * Description: UI class that displays the game using data from the Game class
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;


public class UI {
	static Display display = new Display();
	static Shell shell = new Shell(display);
	static ArrayList<Card> cards; // cards that have been clicked on
	static ArrayList<Card> matched;
	Button button;
	Image image;
	Image blank = new Image(display, "blank.png");
    Image white = new Image(display, "white.png");
    ArrayList<Text> points;
	public UI(Card[][] board, ArrayList<Player> playerList){
		/*
		 * Constructor sets up the display and creates the card deck to be displayed on screen. 
		 * It also makes sure that the data is being updated based on changes in the display made
		 * by the user/
		 * 
		 * board: 2D array of all the cards
		 * playerList; ArrayList of all the players
		 */

        shell.setSize(600, 600);
        cards = new ArrayList<>();
        matched = new ArrayList<>();
        points = new ArrayList<>();
        
        makeDeck(board);
        
        int rows = board.length;
        int columns = board[0].length;
        int cardsNum = rows*columns;
		
        shell.open();
        int i=0;
        Player currPlayer = playerList.get(i);
        displayAllPlayers(playerList);
        boolean gameOver = false;
        Text turn = writeText(260, 400, 110,20,currPlayer.getName()+"'s Turn", new Font(display,"Arial", 9, SWT.BOLD ));
        while (!shell.isDisposed()) 
        {
        	
            if (!display.readAndDispatch())
                display.sleep();
                     	
            	
            if (cards.size()==3)
            {	 
            	Card c1 = cards.get(0);
	            if (c1 == null) {
	            	cards.remove(null);
	            	continue;
	            }
            	Card c2 = cards.get(1);
            	if (c2 == null) {
                	cards.remove(null);
                	continue;
                }
            	Card c3 = cards.get(2); // c3 three is just a dummy card to let the program move onto the next turn
            	if (c1.isMatching(c2)){
            		cardsMatch(c1,c2);
            		currPlayer.match();
	            	cardsNum -= 2;}
            	else{cardsNotMatch(c1,c2);}
            	if (c3 != null)
            		c3.getButton().setImage(blank);
            	cards.removeAll(cards);
            
            	i++;
            	currPlayer = playerList.get(i%playerList.size());
            	updatePoints(playerList);
            	
            	turn.setText(currPlayer.getName()+"'s Turn");
     
            	} 
            
            if (isGameOver(cardsNum) && !gameOver) {
            	winningPlayer(playerList, turn);	
            	gameOver = true;}
        }
        display.dispose();
}    	
	private void makeDeck(Card[][] board) {
		/*
		 * Makes the deck of cards using buttons for each element of board
		 * Board: 2d Array containing cards
		 */
		int y = 50;
        for(int i=0;i<board.length; i++)
        {
            int x = 90;
        	for (int j=0; j<board[i].length;j++)
        	{   Card card = board[i][j];
        		button = new Button(shell, SWT.PUSH);
        		button.addMouseListener(new btnListener(card, button, display, board, cards, matched));
        		button.setImage(blank);        		
                button.setBounds(x,  y,  100,  100);
        		card.setButton(button);
        		x+=105;
        		
        	}
  
        	y+=105;
        }
	}
	private void updatePoints(ArrayList<Player> playerList) {
		/*
		 * Update points on the display
		 * playerList: ArrayList of all players
		 */
		for (int i=0; i<points.size();i++)
		{
			Player p = playerList.get(i);
			Text t = points.get(i);
			t.setText(p.getName()+": " +p.getPoints());
			
		}
	}
	private void displayAllPlayers(ArrayList<Player> playerList) {
		/*
		 * Display all players and their points on the display 
		 * playerList: list of all the players
		 */
		int x = 210;
		for (Player p: playerList)
		{
			points.add(writeText(x,20,75,20,p.getName()+": " +p.getPoints(), new Font(display,"Arial", 9, SWT.BOLD )));
			x+=100;
		}
	}
	private static Text writeText(int x, int y, int w, int h, String txt, Font font) {
		/*
		 * Write text on the GUI display using given coordinates and parameters
		 * x: x num
		 * y: y num
		 * w: width
		 * h: height
		 * txt: String text to display
		 * font: Font object to display text using
		 */

		Text text = new Text(shell, SWT.CENTER);
        text.setText(txt);
        text.setFont(font);
        
        text.setBounds(x, y, w, h);
        text.setSize(w,h);
        text.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        text.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        
        return text;
	}
  
        
	private void winningPlayer(ArrayList<Player> playerList, Text turn) {
		/*
		 * Displays the player who won
		 * playerList: list of all the players
		 * turn: Text object that displays the winner
		 */
		Player maxPlayer = null;
		// The for loop at the bottom is when there are more than 2 players
		if (playerList.size() != 2) {
		int max = 0;
		for (Player p: playerList)
		{
			int points = p.getPoints();
			if (points>=max)
			{
				max = points;
				maxPlayer = p;
			}
			
		}
		}
		// When there are two players
		if (playerList.get(0).getPoints() >playerList.get(1).getPoints()) {
			maxPlayer = playerList.get(0); 
			turn.setText(maxPlayer.getName()+" Wins!!");}
		else if (playerList.get(1).getPoints() >playerList.get(0).getPoints()) {
			maxPlayer = playerList.get(1);
			turn.setText(maxPlayer.getName()+" Wins!!");
		}
		else
			turn.setText("It's a draw!");
		;
		turn.setSize(100, 20);
		
		
		Button quitButton = new Button(shell, SWT.PUSH);
		quitButton.setText("Play Again?");
		quitButton.setFont(new Font(display,"Arial", 9, SWT.BOLD ));
		quitButton.setSize(100, 20);
		quitButton.addSelectionListener(new ButtonSelectionListener());
		quitButton.setBounds(260, 440, 100, 30);
		
	}


	private void cardsNotMatch(Card c1, Card c2) {
		/*
		 * Sets card images to blank box if they dont match
		 * c1: Card object
		 * c2: Card object
		 */
		c1.getButton().setImage(blank);
		c2.getButton().setImage(blank);
	}


	private void cardsMatch(Card c1, Card c2) {
		/*
		 * Sets card images to white if they match and add them to matched list 
		 * and get rid of them from cards list.
		 * c1: Card object
		 * c2: Card object
		 */
		c1.getButton().setImage(white);
    	c2.getButton().setImage(white);
    	cards.remove(c1);
    	cards.remove(c2);
    	matched.add(c1);
    	matched.add(c2);
	}


	public boolean isGameOver(int cardsNum){
		/*
		 * Checks if game is over
		 */
		return cardsNum == 0;
		
	}
        
       
	
	class btnListener implements MouseListener{
		/*
		 * Event Listener class for card button which reacts to the mouse clicks
		 */
		Card card;
		Button button;
		Display display;
		Card[][] board;
		ArrayList<Card> cards;
		int count;
		Image blank = new Image(display, "blank.png");
        Image white = new Image(display, "white.png");
        ArrayList<Card> matched;
		public btnListener(Card card, Button button, Display display, Card[][] board, 
				ArrayList<Card> cards, ArrayList<Card> matched) {
			/*
			 * Constructor initializes all the attributes
			 */
			this.card = card;
			this.button = button;
			this.display = display;
			this.board = board;
			this.cards = cards;
			this.matched = matched;
			this.count = 0;
			
		}

		
		@Override
		public void mouseDoubleClick(MouseEvent e) {}

		@Override
		public void mouseDown(MouseEvent e) {
			/*
			 * After mouse click, method changes the button image and also adds null or the card obj
			 * into the card list.
			 */
				if (!matched.contains(card)) // so matched cards wont do anything when clicked
					button.setImage(new Image(display,card.getImage()));
				
				// so that one card can't be clicked twice and form a match
				if (!cards.contains(card) && !matched.contains(card))  
					cards.add(card);
					
					
				else
					cards.add(null);
				
		}

		@Override
		public void mouseUp(MouseEvent e) {}
		
	}
	
	/**
	 * Class for the quit button listener that ends the program when the button is clicked.
	 * 
	 */
	class ButtonSelectionListener implements SelectionListener 
	{
		public void widgetSelected(SelectionEvent event) {System.exit(0);}
		public void widgetDefaultSelected(SelectionEvent event){}    
	}
	}
	
	

