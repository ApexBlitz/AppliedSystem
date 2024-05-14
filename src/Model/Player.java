package Model;

public class Player {
	private String name;
	private int color;

	public Player(String name, int color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

  /**
   * Play a move on the board in parameter.
   * method implemented by under-class.
   *
   * @param gameboard the board where we will play .
   */
	public void play(GameBoard gameboard) {}
}
