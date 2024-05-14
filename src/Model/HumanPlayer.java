package Model;

import Vue.*;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name,int color) {
		super(name, color);
	}
	
	public static int colSelected;

	public void play(GameBoard game) {
	    boolean correct;
		synchronized (Vue.clic) {

			try {
				Vue.clic.wait();
			    correct = game.playMove(colSelected, this.getColor());
			    if (correct == false) {
			    	play(game);
			    }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
