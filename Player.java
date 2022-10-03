/*
 * Name: Ali Sartaz Khan
 * Description: Makes the player object for the game
 */
public class Player {

	private int points;
	private String name;
	
	/*
	 * Initializes points and name attributes. 
	 * name: String name of player
	 */
	public Player(String name) {
		points = 0;
		this.name = name;
	}
	
	/*
	 * Increments points attribute when player scores a point
	 */
	public void match(){
		points++;
	}
	
	/*
	 * Returns name of player
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Returns player's points
	 */
	public int getPoints() {
		return points;
	}
}
