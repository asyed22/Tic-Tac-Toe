package org.example;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer implements Player {
    private char symbol;
    private Random random;

    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
        this.random = new Random();
    }

    @Override
    public int makeMove(Board board) {
        // Rule 1: First move takes a corner
        if (isFirstMove(board)) {
            return getRandomCorner(board);
        }

        // Rule 2: Second move takes center if available
        if (isSecondMove(board) && board.isCellEmpty(5)) {
            return 5;
        }

        // Rule 3: Win if possible
        int move = findWinningMove(board, symbol);
        if (move != -1) return move;

        // Rule 4: Block opponent's win
        char opponentSymbol = (symbol == 'X') ? 'O' : 'X';
        move = findWinningMove(board, opponentSymbol);
        if (move != -1) return move;

        // Rule 5: Random valid move
        return getRandomValidMove(board);
    }

    private boolean isFirstMove(Board board) {
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (!board.isCellEmpty(i)) count++;
        }
        return count == 0;
    }

    private boolean isSecondMove(Board board) {
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (!board.isCellEmpty(i)) count++;
        }
        return count == 1;
    }

    private int getRandomCorner(Board board) {
        int[] corners = {1, 3, 7, 9};
        List<Integer> availableCorners = new ArrayList<>();
        for (int corner : corners) {
            if (board.isCellEmpty(corner)) {
                availableCorners.add(corner);
            }
        }
        return availableCorners.get(random.nextInt(availableCorners.size()));
    }

    private int findWinningMove(Board board, char symbolToCheck) {
        for (int pos = 1; pos <= 9; pos++) {
            if (board.isCellEmpty(pos)) {
                board.setCell(pos, symbolToCheck);
                boolean wins = board.checkWin(symbolToCheck);
                board.setCell(pos, ' '); // Undo the test move
                if (wins) {
                    return pos;
                }
            }
        }
        return -1;
    }

    private int getRandomValidMove(Board board) {
        List<Integer> availableMoves = new ArrayList<>();
        for (int pos = 1; pos <= 9; pos++) {
            if (board.isCellEmpty(pos)) {
                availableMoves.add(pos);
            }
        }
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}