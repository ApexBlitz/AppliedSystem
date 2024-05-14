package Controller;

import Model.*;
import Vue.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClicEvent implements ActionListener {

    public void actionPerformed(ActionEvent e)
    {       
    	synchronized (Vue.clic) {
    		HumanPlayer.colSelected = Integer.parseInt(e.getActionCommand());
    		Vue.clic.notify(); 
    	}
    }

}
