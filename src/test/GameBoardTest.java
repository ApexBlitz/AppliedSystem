package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.GameBoard;

public class GameBoardTest {
    
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