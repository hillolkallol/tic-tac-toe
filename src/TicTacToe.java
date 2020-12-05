
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KD
 */
public class TicTacToe {
    
    char[][] board;
    int n;
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
    
    public boolean checkWinner (int r, int c, int player) {
        return checkRow (r, player) || checkCol (c, player) || checkDiagonals (player);
    }
    
    private boolean checkRow (int r, int player) {
        for (int i = 0; i < n; i++) {
            if (board[r][i] != players[player-1]) return false;
        }
        System.out.println("Row true");
        return true;
    }
    
    private boolean checkCol (int c, int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][c] != players[player-1]) return false;
        }
        System.out.println("Col true");
        return true;
    }
    
    private boolean checkDiagonals (int player) {
        boolean status1 = true;
        boolean status2 = true;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != players[player-1]) status1 = false;
        }
        
        for (int r = 0, c = n-1; r < n && c >= 0; r++, c--) {
            if (board[r][c] != players[player-1]) status2 = false;
        }
        
        return status1 || status2;
    }
    
    public void displayBoard () {
        for (char[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    public boolean validMove (int r, int c) {
        if (board[r][c] == players[0] || board[r][c] == players[1]) return false;
        return true;
    }
}
