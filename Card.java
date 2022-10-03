/*
 * Name: Ali Sartaz Khan
 * Description: Makes the card object which is used to create the 2D card array
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import java.util.List;
import java.util.*;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.*;


public class Card {
	private String name;
	private String imgPath;
	private Button button;
	
	/*
	* Contructor initilizes card image and image path
	* name: Card name
	* imgPath: image path from current directory
	*/
	public Card(String name, String imgPath) {
		this.name = name;
		this.imgPath = imgPath;
	}
	
	/*
	 * Returns image path
	 */
	public String getImage() {
		
		return imgPath;
	}
	
	/*
	* Returns card name
	*/
	public String getName() {
		
		return name;
	}

	/*
	* Card representation in string format
	*/
	public String toString() {
		
		return name;
	}

	/*
	 * Setter for button attribute
	 * button: Button object that displays this card
	*/
	public void setButton(Button button) {
		this.button = button;
	}
	
	/*
	 * Getter for button attribute
	 */
	public Button getButton() {
	
		return button;
	}
	
	/*
	 * Checks to see if this card object and the one in the parameter matches
	 * c: Card object
	 */
	public boolean isMatching(Card c){
		
		return this.name.equals(c.getName());
	}
	
}
