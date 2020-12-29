package TicTacToe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * TicTacToe
 * TicTacToe is a simple board game: https://www.youtube.com/watch?v=5SdW0_wTX5c
 *
 * Caution: Just for fun!!
 * Haven't captured a few edge cases (i.e. wrong user input, index out of bound)
 * Will be fixed in the next release! :p :p
 *
 * Wiki:
 * Tic-tac-toe (American English), noughts and crosses (Commonwealth English), 
 * or Xs and Os, is a paper-and-pencil game for two players, X and O, who take turns marking the spaces in a 3×3 grid. 
 * The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. 
 * It is a solved game with a forced draw assuming best play from both players. 
 * Reference: Wikipedia- https://en.wikipedia.org/wiki/Tic-tac-toe
 * 
 * **********************************************************
 * This game has two main characteristics- Move and Winner Check.
 * CheckWinner method will be called after each move.
 * 
 * Before executing move, validMove method will be called from the client side just to check if the move is valid.
 * If the move is valid, the move method will be called.
 * The sign will be planted in the board (based on given row and col)
 * Then the checkWinner method will be called.
 * Last thing we need to check is if the match is a draw!
 * 
 * Next thing is to check if the player is the winner after the last move.
 * Check-
 * * if all the rows contain same sign OR
 * * if all the columns contain same sign OR
 * * if all diagonal or anti-diagonal boxes contain same sign
 * ************************************************************
 * 
 * @author KD
 */
public class TicTacToe {
    
    // n size tictactoe board
    char[][] board;
    int n;
    // Storing all the moves in a list so that we can implement undo operation in the future.
    List<int[]> moves;
    char[] players;
    
    public TicTacToe (int size) {
        board = new char[size][size];
        moves = new ArrayList<>();
        n = size;
        players = new char[2];
        players[0] = 'X';
        players[1] = 'O';
    }
    
    /**
     * Before executing move, validMove method will be called from the client side just to check if the move is valid.
     * If the move is valid, the move method will be called.
     * The sign will be planted in the board (based on given row and col)
     * Then the checkWinner method will be called.
     * Last thing we need to check is if the match is a draw!
     * 
     * @param r desire row where the player want to execute his next move
     * @param c desire column where the player want to execute his next move
     * @param player it's a two player game, player id (1 or 2) will be passed through this parameter
     * @return true if there is a winner or the match is draw. False otherwise.
     */
    public boolean move (int r, int c, int player) {
        char sign = players[player-1];
        board[r][c] = sign;
        moves.add(new int[]{r, c});
        displayBoard();
        if (checkWinner (r, c, player)) {
            System.out.println("Player " + player + " wins!");
            return true;
        }
        
        if (moves.size() == n * n) {
            System.out.println("Draw!");
            return true;
        }
        
        System.out.println("Next move please!");
        return false;
    }
    
    /**
     * Check if the player is the winner after the last move.
     * Check-
     * * if all the rows contain same sign OR
     * * if all the columns contain same sign OR
     * * if all diagonal or anti-diagonal boxes contain same sign
     * 
     * @param r row where the player just executed their last move
     * @param c column where the player just executed their last move
     * @param player playerID (1 or 2) who has executed the last move
     * @return true if the player is the winner, false otherwise
     */
    public boolean checkWinner (int r, int c, int player) {
        return checkRow (r, player) || checkCol (c, player) || checkDiagonals (player);
    }
    
    /**
     * Check if all the rows contain same sign
     * 
     * @param r row where the player just executed their last move
     * @param player playerID (1 or 2) who has executed the last move
     * @return true if all the rows contain same sign, false otherwise
     */
    private boolean checkRow (int r, int player) {
        for (int i = 0; i < n; i++) {
            if (board[r][i] != players[player-1]) return false;
        }
        System.out.println("Row true");
        return true;
    }
    
    /**
     * Check if all the columns contain same sign
     * 
     * @param c column where the player just executed their last move
     * @param player playerID (1 or 2) who has executed the last move
     * @return true if all the columns contain same sign, false otherwise
     */
    private boolean checkCol (int c, int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][c] != players[player-1]) return false;
        }
        System.out.println("Col true");
        return true;
    }
    
    /**
     * Check if all diagonal or anti-diagonal boxes contain same sign
     * 
     * @param player playerID (1 or 2) who has executed the last move
     * @return true if all diagonal or anti-diagonal boxes contain same sign, false otherwise
     */
    private boolean checkDiagonals (int player) {
        boolean status1 = true;
        boolean status2 = true;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != players[player-1]) status1 = false; // checking diagonal
        }
        
        for (int r = 0, c = n-1; r < n && c >= 0; r++, c--) {
            if (board[r][c] != players[player-1]) status2 = false; // checking anti-diagonal
        }
        
        return status1 || status2;
    }
    
    /**
     * Display the TicTacToe Board (array)
     */
    public void displayBoard () {
        for (char[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * Check if the move is valid.
     * In other word, check if the given position (row, col) in the board is empty
     * 
     * @param r desire row where the player want to execute his next move
     * @param c desire column where the player want to execute his next move
     * @return true if the move is valid, false otherwise.
     */
    public boolean validMove (int r, int c) {
        return !(board[r][c] == players[0] || board[r][c] == players[1]);
    }
}
