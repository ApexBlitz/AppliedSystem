package Vue;

import Model.*;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Connect4 {
	protected static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] options = {"Player VS Bot", "Player VS Player"};
		int n = JOptionPane.showOptionDialog(new JFrame(), "Choose game mode.", "Welcome in connect4", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		Game p = null;
		if(n == 0){
			String nom = (String)JOptionPane.showInputDialog(new JFrame(),"Your PlayerName.", "Inscription", JOptionPane.PLAIN_MESSAGE);
			if(nom != null){
				p = new Game(new BotPlayer(GameBoard.YELLOW), new HumanPlayer(nom, GameBoard.RED));
			}
		}
		else if(n==1){
			String nom =  (String)JOptionPane.showInputDialog(new JFrame(),"Name of player 1.", "Inscription 1", JOptionPane.PLAIN_MESSAGE);
			if(nom != null){
				String nom2 =  (String)JOptionPane.showInputDialog(new JFrame(),"Name of player 2.", "Inscription 2", JOptionPane.PLAIN_MESSAGE);
				if(nom2 != null){
					p = new Game(new HumanPlayer(nom, GameBoard.YELLOW), new HumanPlayer(nom2, GameBoard.RED));
				}
			}
		}
		if(p != null){
			p.play();
		}
	}

}
