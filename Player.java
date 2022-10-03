/*
 * Name: Ali Sartaz Khan
 * Class: CSc 335
 * Description: Makes the player object for the game
 */
public class Player {

	private int points;
	private String name;
	public Player(String name) {
		/*
		 * Initializes points and name attributes. 
		 * name: String name of player
		 */
		points = 0;
		this.name = name;
	}
	public void match(){
		/*
		 * Increments points attribute when player scores a point
		 */
		points++;
	}
	public String getName() {
		/*
		 * Returns name of player
		 */
		return name;
	}
	
	public int getPoints() {
		/*
		 * Returns player's points
		 */
		return points;
	}
}
