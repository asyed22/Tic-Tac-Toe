package org.example;

import java.util.Scanner;

public class App {

    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private Scanner scanner = new Scanner(System.in);

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.getGreeting()); // Print the greeting message
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
                    gameOver = true;
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                    gameOver = true;
                } else {
                    game.switchPlayer();
                }
            }

            playAgain = game.askToPlayAgain();
        }
        System.out.println("Goodbye!");
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
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
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
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
