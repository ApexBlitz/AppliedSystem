package Model;

public class BotPlayer extends Player {
	 public BotPlayer(int couleur) {
	    super("The Bot", couleur);
	  }

	  public void play(GameBoard game) {
		  if(game.iaPlay(this.getColor())){
			  return;
		  }
	  }
}
