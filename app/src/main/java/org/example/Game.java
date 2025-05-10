package org.example;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    public Game() {
        board = new Board();
    }

    public void start(int gameChoice) {  // Removed boolean parameter
        switch (gameChoice) {
            case 1:
                player1 = new HumanPlayer('X');
                player2 = new HumanPlayer('O');
                break;
            case 2:
                player1 = new HumanPlayer('X');
                player2 = new ComputerPlayer('O');
                break;
            case 3:
                player1 = new ComputerPlayer('X');
                player2 = new HumanPlayer('O');
                break;
        }

        boolean gameWon = false;
        
        // Only print empty board if human goes first
        if (gameChoice != 3) {
            board.printBoardWithNumbers();
        }

        while (!gameWon && !board.isFull()) {
            // Player 1 move
            if (player1 instanceof ComputerPlayer) {
                System.out.println("\nComputer player:");
            }
            int move1 = player1.makeMove(board);
            board.setCell(move1, player1.getSymbol());
            board.printBoardWithNumbers();
            if (board.checkWin(player1.getSymbol())) {
                System.out.println("Player 1 (" + player1.getSymbol() + ") wins!");
                gameWon = true;
                break;
            }

            if (board.isFull()) break;

            // Player 2 move
            if (player2 instanceof ComputerPlayer) {
                System.out.println("\nComputer player:");
            }
            int move2 = player2.makeMove(board);
            board.setCell(move2, player2.getSymbol());
            board.printBoardWithNumbers();
            if (board.checkWin(player2.getSymbol())) {
                System.out.println("Player 2 (" + player2.getSymbol() + ") wins!");
                gameWon = true;
            }
        }

        if (!gameWon) {
            System.out.println("It's a draw!");
        }
    }
}