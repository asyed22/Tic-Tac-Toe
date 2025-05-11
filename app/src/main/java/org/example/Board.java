package org.example;

public class Board {
    private char[] board;

    public Board() {
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public void printBoardWithNumbers() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                System.out.print(" " + (i+1) + " ");
            } else {
                System.out.print(" " + board[i] + " ");
            }
            if (i == 2 || i == 5 || i == 8) {
                System.out.println();
                if (i != 8) System.out.println("---+---+---");
            } else {
                System.out.print("|");
            }
        }
    }

    public boolean isCellEmpty(int position) {
        return board[position - 1] == ' ';
    }

    public void setCell(int position, char symbol) {
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Position must be between 1 and 9");
        }
        board[position - 1] = symbol;
    }

    public char getCell(int position) {
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Position must be between 1 and 9");
        }
        return board[position - 1];
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public boolean checkWin(char symbol) {
        return (checkLine(0, 1, 2, symbol) || 
                checkLine(3, 4, 5, symbol) || 
                checkLine(6, 7, 8, symbol) ||
                checkLine(0, 3, 6, symbol) || 
                checkLine(1, 4, 7, symbol) || 
                checkLine(2, 5, 8, symbol) ||
                checkLine(0, 4, 8, symbol) || 
                checkLine(2, 4, 6, symbol));
    }

    private boolean checkLine(int a, int b, int c, char symbol) {
        return (board[a] == symbol && board[b] == symbol && board[c] == symbol);
    }
}