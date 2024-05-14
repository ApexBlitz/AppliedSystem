package Vue;

import Controller.*;
import Model.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Vue extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JPanel layout = new JPanel();
	private int rows;
	private int columns;
	private CirclePanel[][] grid;
	public final static ActionListener clic = new ClicEvent();;

    public Vue(int rows, int columns)
    {
        super("Connect4");
        this.rows = rows;
        this.columns = columns;
        layout.setLayout(new GridLayout(this.rows + 1,this.columns));

	    grid = new CirclePanel[rows][columns];
 
	    for (int row = rows - 1; row >= 0; --row) {
	        for (int col = 0; col < columns; col++) {
	    		CirclePanel circle = new CirclePanel(Color.white);
	    		grid[row][col] = circle;
	    		layout.add(grid[row][col]);
	    	}
	    }
        
        for (int col = 0; col < columns; col++){
        	JButton button = new JButton(String.valueOf(col));
            button.addActionListener(clic);
    		layout.add(button);
        }
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(layout);
        pack();
        setVisible(true);
    }
    
    public void changeColorGrid(int row, int col, int color){
    	switch (color) {
	        case GameBoard.RED:
	    	  grid[row][col].changeColor(Color.red);
	          break;
	        case GameBoard.YELLOW:
	      	  grid[row][col].changeColor(Color.yellow);
	          break;
        }
    	refresh();
    }
    
    public void refresh(){
	    layout.repaint();
    }
    
    public void endOfGame(String message){
    	JOptionPane.showMessageDialog(null, message, "La partie est finie.", JOptionPane.INFORMATION_MESSAGE);
    	setVisible(false);
    	dispose();
    }
    
    public class CirclePanel extends JPanel {
    	private static final long serialVersionUID = 1L;
		private Color color;
    	
    	public CirclePanel(Color color){
    		this.color = color;
    	}
    	
    	public void changeColor(Color color){
    		this.color = color;
    	}

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(this.color);
            g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        }
    }

}