package Model;

public class Game {
	private Player[] players = new Player[2];
	private GameBoard gameboard;

	public Game(Player player1, Player player2) {
		players[0] = player1;
		players[1] = player2;
		gameboard = new GameBoard();
	}

	public void play() {
		int winner = -1;
	    int cplayer = 0;

	    while (winner==-1 && !gameboard.isFull()) {
	      players[cplayer].play(gameboard);
	      if (gameboard.isFull()) {
	        winner = -1;
	      }

	      // if 4 token are aligned we have a winner
	      // (even if gameboard full!)

	      if (gameboard.find4()) {
	    	  winner = cplayer;
	      }

	      // Changing player by the following iteration
	      cplayer++;
	      cplayer %= 2;
	    }
	    
	    if (winner == -1) {
	      this.gameboard.endGame("Draw");
	    } else {
	      this.gameboard.endGame("The winner is " + players[winner].getName());
	    }
	}
}
