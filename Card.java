/*
 * Name: Ali Sartaz Khan
 * Class: CSc 335
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
	public Card(String name, String imgPath) {
		/*
		 * Contructor initilizes card image and image path
		 * name: Card name
		 * imgPath: image path from current directory
		 */
		this.name = name;
		this.imgPath = imgPath;
	}
	
	public String getImage() {
		/*
		 * Returns image path
		 */
		return imgPath;
	}
	public String getName() {
		/*
		 * Returns card name
		 */
		return name;
	}

	public String toString() {
		/*
		 * Card representation in string format
		 */
		return name;
	}

	public void setButton(Button button) {
		/*
		 * Setter for button attribute
		 * button: Button object that displays this card
		 */
		this.button = button;
	}
	public Button getButton() {
		/*
		 * Getter for button attribute
		 */
		return button;
	}
	public boolean isMatching(Card c){
		/*
		 * Checks to see if this card object and the one in the parameter matches
		 * c: Card object
		 */
		return this.name.equals(c.getName());
	}
	
}
