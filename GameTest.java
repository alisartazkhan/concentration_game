import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTest {

	@Test
	public void test() {
		Game game = new Game();
		ArrayList arr = new ArrayList<Player>();
		game.createplayers(arr, 2);
		assertEquals(2, arr.size());
	}
	
	@Test
	public void test2() {
		Game game = new Game();
		ArrayList arr = new ArrayList<Player>();
		game.createplayers(arr, 3);
		assertEquals(3, arr.size());
	}@Test
	public void test3() {
		Board board = new Board(3,4);
		Card[][] x = board.getBoard();
		assertEquals(3, x.length);
		assertEquals(4, x[0].length);
	}
	@Test
	public void test5() {
		Player player = new Player("X");
		player.match();
		assertEquals(player.getPoints(), 1);
		player.match();
		assertEquals(player.getPoints(), 2);
		player.match();
		assertEquals(player.getPoints(), 3);
	}@Test
	public void test6() {
		Player player = new Player("X");
		player.getName();
		assertEquals(player.getName(), "X");

	}@Test
	public void test7() {
		Card c = new Card("apple", "apple.jpg");
		assertEquals(c.getName(), "apple");
		assertEquals(c.getImage(), "apple.jpg");
	}@Test
	public void test8() {
		Card c = new Card("apple", "apple.jpg");
		Card d = new Card("apple", "apple.jpg");
		assertEquals(c.isMatching(d), true);
		
	}

}
