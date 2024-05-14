package Model;
import java.util.Random;
import Vue.*;

public class GameBoard {
    public final static int EMPTY = 0;
    public final static int YELLOW = 1;
    public final static int RED = 2;

    private int rows; // Rows
    private int columns; // Columns
    private int[][] grid;
    private Vue vue;
    private boolean beginning = true;

    public GameBoard(int rows, int columns) {
        startGame(rows, columns);
        vue = new Vue(rows, columns);
    }

    public GameBoard() {
        this(6, 7); // Default, 6 rows and 7 columns
    }
    
    public int[][] getGrid(){
    	return grid;
    }
    
    public void endGame(String message){
        this.vue.endOfGame(message);
    }

    private void startGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = EMPTY;
            }
        }
    }

    public boolean playMove(int col, int player) {
        if ((col < 0) || (col >= columns)) {
            return false;
        }

        // Find the first position EMPTY in the column
        for (int row = 0; row < rows; row++) {
            if (grid[row][col] == EMPTY) {
                grid[row][col] = player;
                this.vue.changeColorGrid(row, col, player); // Change the order of parameters
                return true;
            }
        }

        // column is full
        return false;
    }

    /**
     * Control all rows, columns, and diagonals to find a set of 4 tokens
     * of the same color. If found, return true.
     *
     * @return true if the game has 4 tokens aligned
     */
    public boolean find4() {
        // Control horizontal ( - )
        for (int row = 0; row < rows; row++) {
            if (find4Token(row, 0, 0, 1)) { // Change the order of parameters
                return true;
            }
        }

        // Control vertical ( | )
        for (int col = 0; col < columns; col++) {
            if (find4Token(0, col, 1, 0)) { // Change the order of parameters
                return true;
            }
        }

        // Diagonals (Control from bottom row)
        for (int col = 0; col < columns; col++) {
            // First diagonal ( / )
            if (find4Token(0, col, 1, 1)) { // Change the order of parameters
                return true;
            }
            // Second diagonal ( \ )
            if (find4Token(0, col, 1, -1)) { // Change the order of parameters
                return true;
            }
        }

        // Nothing found
        return false;
    }

    /**
     * This method finds 4 tokens aligned on a row. The row is defined by
     * the starting point, or origin of the coordinates (oRow, oCol), and by the shift
     * delta (dRow, dCol).
     *
     * @param oRow Origin row of the search
     * @param oCol Origin column of the search
     * @param dRow Delta of the shift over a row
     * @param dCol Delta of the shift over a column
     * @return true if we find an alignment
     */
    private boolean find4Token(int oRow, int oCol, int dRow, int dCol) { // Change the variable names
        int color = EMPTY;
        int counter = 0;

        int curRow = oRow;
        int curCol = oCol;

        while ((curRow >= 0) && (curRow < rows) && (curCol >= 0) && (curCol < columns)) { // Change the order of conditions
            if (grid[curRow][curCol] != color) {
                // If the color changes, reset the counter
                color = grid[curRow][curCol];
                counter = 1;
            } else {
                // Otherwise, increment it
                counter++;
            }

            // We go out when counter = 4
            if ((color != EMPTY) && (counter == 4)) {
                return true;
            }

            // Going to next iteration
            curRow += dRow;
            curCol += dCol;
        }

        // No alignment was found
        return false;
    }

    public boolean iaPlay(int player) {
        if (this.beginning) {
            this.beginning = false;
            Random rand = new Random();
            int randomNumber = rand.nextInt(columns);
            if (this.playMove(randomNumber, player)) {
                return true;
            }
        } else {
            for (int col = 0; col < columns; col++) {
                for (int row = 0; row < rows; row++) {
                    if (grid[row][col] == player) {
                        int nextCol = getNearCase(row, col);
                        if (nextCol != -1 && this.playMove(nextCol, player)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getNearCase(int colTarget, int rowTarget) {
        int[] elements = {0, -1, 1};  
        for (int col : elements) {
            for (int row : elements) {
                if (colTarget + col >= 0 && colTarget + col < columns && rowTarget + row >= 0 && rowTarget + row < rows) {
                    if (grid[rowTarget + row][colTarget + col] == EMPTY) {
                        return colTarget + col;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Look if it's still possible to put a token
     * @return true if the board is full
     */
    public boolean isFull() {
        // Looking for an empty case. If there isn't, the board is full
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (grid[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}