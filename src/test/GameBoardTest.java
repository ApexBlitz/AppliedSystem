package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.*;

public class GameBoardTest {
    
// TEST 1
    @Test
    public void testPlayMove() {
    
        GameBoard gameBoard = new GameBoard(6, 7); // Create a 6x7 board
        
        int columnToDrop = 3; // Choose the column to drop the disc
        int playerColor = GameBoard.YELLOW; // Player color is yellow
        boolean moveSuccessful = gameBoard.playMove(columnToDrop, playerColor); // Make a move
        
        assertTrue(moveSuccessful); // Check if the move was successful
        assertEquals(playerColor, gameBoard.getGrid()[0][columnToDrop]); // Check if the disc is in the correct column and position
    }

// TEST 2
    @Test
    public void testTurnAnnouncement() {

        GameBoard gameBoard = new GameBoard(6, 7);
        Player player1 = new HumanPlayer("Player 1", GameBoard.RED);
        Player player2 = new HumanPlayer("Player 2", GameBoard.YELLOW);

        gameBoard.playMove(3, player1.getColor());
        gameBoard.playMove(3, player2.getColor());

        assertEquals(player1.getColor(), gameBoard.getGrid()[0][3]);
        assertEquals(player2.getColor(), gameBoard.getGrid()[1][3]);
    }

// TEST 3
    @Test
    public void testPlaceOnFullColumn() {
 
        GameBoard gameBoard = new GameBoard(6, 7);
        
        for (int row = 0; row < 6; row++) {
            gameBoard.playMove(3, GameBoard.RED);
        }
        
        assertFalse(gameBoard.playMove(3, GameBoard.YELLOW));
    }
    
 // TEST 4
    @Test
    public void testIsFull() {
       
        GameBoard gameBoard = new GameBoard(6, 7); // Create a 6x7 board        
       
        // Place disc still the board are full
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                gameBoard.playMove(col, GameBoard.YELLOW); // Put each columns token
            }
        }
        
        assertTrue(gameBoard.isFull()); // Control if board is full
    }


 // TEST 5
    @Test
    public void testEndGame() {
        GameBoard gameBoard = new GameBoard(6, 7);
        for (int i = 0; i < 4; i++) {
            gameBoard.playMove(i, GameBoard.RED);
        }
        assertTrue(gameBoard.find4());
    }
 }
