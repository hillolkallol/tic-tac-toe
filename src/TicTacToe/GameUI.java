package TicTacToe;


import TicTacToe.TicTacToe;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KD
 */
public class GameUI {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        int player = 1;
        System.out.println("Let's begin..");
        while (true) {
            System.out.println("Player " + player + "'s turn! Put the index of your next move! Example: 0 1 or 2 2");
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String[] arr = s.split(" ");
            int row = Integer.parseInt(arr[0]);
            int col = Integer.parseInt(arr[1]);
            
            // checking if the move is valid, if not, giving the player another chance to make a correct move
            if (!game.validMove(row, col)) { 
                System.out.println("Not a valid move, try again!");
                continue;
            }
            
            if (game.move(row, col, player)) break;
            player = (player == 1) ? 2: 1;
        }
    }
}
