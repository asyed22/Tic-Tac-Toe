package org.example;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    public Game() {
        board = new Board();
    }

    public void start(int gameChoice) {
        switch (gameChoice) {
            case 1:
                player1 = new HumanPlayer('X');
                player2 = new HumanPlayer('O');
                System.out.println("\nHuman vs Human game started!");
                break;
            case 2:
                player1 = new HumanPlayer('X');
                player2 = new ComputerPlayer('O');
                System.out.println("\nHuman vs Computer (Human first)!");
                break;
            case 3:
                player1 = new ComputerPlayer('X');
                player2 = new HumanPlayer('O');
                System.out.println("\nComputer vs Human (Computer first)!");
                break;
        }

        boolean gameWon = false;
        Player currentPlayer = player1;

        while (!gameWon && !board.isFull()) {
            System.out.println("\n--- " + 
                (currentPlayer == player1 ? "Player 1 (X)" : "Player 2 (O)") + 
                " turn ---");

            if (currentPlayer instanceof HumanPlayer) {
                board.printBoardWithNumbers();
            }

            int move = currentPlayer.makeMove(board);
            board.setCell(move, currentPlayer.getSymbol());
            board.printBoardWithNumbers();

            if (board.checkWin(currentPlayer.getSymbol())) {
                System.out.println(currentPlayer == player1 ? 
                    "Player 1 (X) wins!" : "Player 2 (O) wins!");
                gameWon = true;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        if (!gameWon) {
            System.out.println("It's a draw!");
        }
    }
}