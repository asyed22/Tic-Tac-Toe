package org.example;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    private char[][] board = new char[3][3];
    private char currentPlayer;
    private Scanner scanner = new Scanner(System.in);
    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;
    private char lastLoser = 'O'; // Default to 'O' so X starts first initially

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        App game = new App();
        boolean playAgain = true;
        while (playAgain) {
            game.initializeBoard();
            System.out.println("Welcome to Tic-Tac-Toe!");
            game.printBoard();

            boolean gameOver = false;
            while (!gameOver) {
                int move = game.getPlayerMove();
                game.makeMove(move);
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("Player " + game.getCurrentPlayer() + " wins!");
                    game.updateStats(game.getCurrentPlayer());
                    game.printStats();
                    // Set lastLoser to the winning player
                    game.lastLoser = game.getCurrentPlayer();
                    gameOver = true;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                    game.updateStats('T');
                    game.printStats();
                    // Don't change lastLoser for a tie
                    gameOver = true;
                } else {
                    game.switchPlayer();
                }
            }

            playAgain = game.askToPlayAgain();
        }
        game.saveGameLog();
        System.out.println("Goodbye!");
    }

    private void updateStats(char winner) {
        if (winner == 'X') {
            xWins++;
        } else if (winner == 'O') {
            oWins++;
        } else {
            ties++;
        }
    }

    private void printStats() {
        System.out.println("\nThe current log is:\n");
        System.out.printf("Player X Wins   %d\n", xWins);
        System.out.printf("Player O Wins   %d\n", oWins);
        System.out.printf("Ties            %d\n\n", ties);
    }

    private void saveGameLog() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write("Final Game Statistics:\n\n");
            writer.write(String.format("Player X Wins   %d\n", xWins));
            writer.write(String.format("Player O Wins   %d\n", oWins));
            writer.write(String.format("Ties            %d\n", ties));
            System.out.println("Writing the game log to disk. Please see game.txt for the final statistics!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game log.");
        }
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        // Set current player to opposite of last loser
        currentPlayer = (lastLoser == 'X') ? 'O' : 'X';
        if (currentPlayer == 'X') {
            System.out.println("\nPlayer X goes first!");
        } else {
            System.out.println("\nPlayer O goes first!");
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cellValue = board[i][j];
                if (cellValue == ' ') {
                    System.out.print(" " + (i * 3 + j + 1) + " ");
                } else {
                    System.out.print(" " + cellValue + " ");
                }
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    public int getPlayerMove() {
        while (true) {
            System.out.print("What is your move? ");
            String input = scanner.nextLine().trim();

            try {
                int move = Integer.parseInt(input);
                if (move >= 1 && move <= 9 && isCellEmpty(move)) {
                    return move;
                } else {
                    System.out.println("That is not a valid move! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid move! Try again.");
            }
        }
    }

    public boolean isCellEmpty(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] == ' ';
    }

    public void makeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean askToPlayAgain() {
        while (true) {
            System.out.print("Would you like to play again (yes/no)? ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("That is not a valid entry!");
            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}