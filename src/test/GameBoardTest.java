package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.GameBoard;

public class GameBoardTest {

    // TEST 4
    @Test
    public void testIsFull() {
        // Arrange
        GameBoard gameBoard = new GameBoard(6, 7); // Create a 6x7 board
        
        // Act
        // Place disc still the board are full
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                gameBoard.playMove(col, GameBoard.YELLOW); // Put each columns token
            }
        }
        
        // Assert
        assertTrue(gameBoard.isFull()); // Control if board is full
    }



    
    // TEST 1
    @Test
    public void testPlayMove() {
        // Arrange
        GameBoard gameBoard = new GameBoard(6, 7); // Create a 6x7 board
        
        // Act
        int columnToDrop = 3; // Choose the column to drop the disc
        int playerColor = GameBoard.YELLOW; // Player color is yellow
        boolean moveSuccessful = gameBoard.playMove(columnToDrop, playerColor); // Make a move
        
        // Assert
        assertTrue(moveSuccessful); // Check if the move was successful
        assertEquals(playerColor, gameBoard.getGrid()[0][columnToDrop]); // Check if the disc is in the correct column and position
    }

}




// TEST 2
    @Test
    public void testTurnAnnouncement() {
        // Arrange
        GameBoard gameBoard = new GameBoard(6, 7);
        Player player1 = new HumanPlayer("Player 1", GameBoard.RED);
        Player player2 = new HumanPlayer("Player 2", GameBoard.YELLOW);

        // Act
        gameBoard.playMove(3, player1.getDiscColor());
        
        // Assert
        assertEquals(GameBoard.YELLOW, gameBoard.getNextPlayerColor());

        // Act
        gameBoard.playMove(3, player2.getDiscColor());
        
        // Assert
        assertEquals(GameBoard.RED, gameBoard.getNextPlayerColor());
    }




// TEST 3
    @Test
    public void testPlaceOnFullColumn() {
        // Arrange
        GameBoard gameBoard = new GameBoard(6, 7);
        
        // Act
        for (int row = 0; row < 6; row++) {
            gameBoard.playMove(3, GameBoard.RED);
        }
        
        // Assert
        assertFalse(gameBoard.playMove(3, GameBoard.YELLOW));
    }




 // TEST 5
    @Test
    public void testEndGame() {
        // Arrange
        GameBoard gameBoard = new GameBoard(6, 7);
        
        // Act
        for (int i = 0; i < 4; i++) {
            gameBoard.playMove(i, GameBoard.RED);
        }
        
        // Assert
        assertTrue(gameBoard.checkWin(GameBoard.RED));
    }
