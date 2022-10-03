/*
 * Name: Ali Sartaz Khan
 * Class: CSc 335
 * Description: Makes the game class that sets up all the objects and has
 * them ready for the UI class to use
 */

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;



public class Game {
	
	public static void main(String args[]) 
	{
		
		
		int playerNumber;
		playerNumber = 2; // set player number
		ArrayList<Player> playerList = new ArrayList<>();
		Board board = new Board(3,4);	
		Card[][] boardArr = board.getBoard();
		createplayers(playerList, playerNumber); // creating list of players
		Display d = new Display();
		Shell s = new Shell(d);
		s.setSize(300,300);
		s.open();
		Button quitButton = new Button(s, SWT.PUSH);
		quitButton.setText("Play");
		quitButton.setFont(new Font(d,"Arial", 9, SWT.BOLD ));
		quitButton.setSize(100, 100);
		quitButton.addSelectionListener(new ButtonSelectionListener(boardArr, playerList, d));
		quitButton.setBounds(95,200, 100, 30);
		
		writeText(50, 50, 190,30, "Rules of the game: ", new Font(d,"Arial", 9, SWT.BOLD ), s, d);
		writeText(50, 80, 190,30, "(1) Select two cards", new Font(d,"Arial", 9, SWT.BOLD ), s, d);
		writeText(50, 110,190,30, "(2) Click a third time to continue", new Font(d,"Arial", 9, SWT.BOLD ), s, d);
		while (!s.isDisposed()) 
        {
        	
            if (!d.readAndDispatch())
                d.sleep();
            
        }
		
		
	}

	private static Text writeText(int x, int y, int w, int h, String txt, Font font, Shell s, Display d) {
		/*
		 * Write text on the GUI display using given coordinates and parameters
		 * x: x num
		 * y: y num
		 * w: width
		 * h: height
		 * txt: String text to display
		 * font: Font object to display text using
		 */
		Text text = new Text(s, SWT.LEFT);
        text.setText(txt);
        text.setFont(font);
        
        text.setBounds(x, y, w, h);
        text.setSize(w,h);
        text.setBackground(d.getSystemColor(SWT.COLOR_WHITE));
        text.setForeground(d.getSystemColor(SWT.COLOR_BLACK));
        
        return text;
	}
  
	public static  void createplayers(ArrayList<Player> list, int playerNumber)
	{ /*
		Creates new players and adds them to the list. 
		list: List of all the players
		playerNumber: Number of total players
	*/
		if (playerNumber == 2) // regular format
		{
		list.add(new Player("Sartaz"));
		list.add(new Player("Hannah"));
		}
		
		else {
			for (int i=1; i<= list.size(); i++)
			{
				list.add(new Player("Player "+i));
			}
		}
	}
	
}

/*
 * Class for the play button on initial display screen
 */
class ButtonSelectionListener implements SelectionListener 
{
	Card[][] board;
	ArrayList<Player> playerList;
	Display d;
	public ButtonSelectionListener(Card[][] boardArr, ArrayList<Player> playerList, Display d) {
		/*
		 * Constructor initializes attributes and take in 3 parameters.
		 */
		this.board = boardArr;
		this.playerList = playerList;
		this.d = d;
	}
	public void widgetSelected(SelectionEvent event) {
		/*
		 * When button is clicked, button disposes current display and creates a new UI object
		 * to run the actual game on
		 */
		d.dispose();
		UI ui = new UI(board, playerList);}
	public void widgetDefaultSelected(SelectionEvent event){}    
}
