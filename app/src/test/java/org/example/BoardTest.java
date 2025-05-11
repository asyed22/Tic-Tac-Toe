package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testInitialBoardIsEmpty() {
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isCellEmpty(i));
            assertEquals(' ', board.getCell(i));
        }
    }

    @Test
    void testSetAndGetCell() {
        board.setCell(5, 'X');
        assertFalse(board.isCellEmpty(5));
        assertEquals('X', board.getCell(5));
    }

    @Test
    void testInvalidPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> board.setCell(0, 'X'));
        assertThrows(IllegalArgumentException.class, () -> board.setCell(10, 'O'));
        assertThrows(IllegalArgumentException.class, () -> board.getCell(0));
        assertThrows(IllegalArgumentException.class, () -> board.getCell(10));
    }

    @Test
    void testWinConditions() {
        // Test row win
        board.setCell(1, 'X');
        board.setCell(2, 'X');
        board.setCell(3, 'X');
        assertTrue(board.checkWin('X'));

        // Test column win
        board = new Board();
        board.setCell(1, 'O');
        board.setCell(4, 'O');
        board.setCell(7, 'O');
        assertTrue(board.checkWin('O'));

        // Test diagonal win
        board = new Board();
        board.setCell(1, 'X');
        board.setCell(5, 'X');
        board.setCell(9, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void testDrawDetection() {
        // Fill board without winner
        char[] moves = {'X','O','X','X','O','O','O','X','X'};
        for (int i = 1; i <= 9; i++) {
            board.setCell(i, moves[i-1]);
        }
        assertTrue(board.isFull());
        assertFalse(board.checkWin('X'));
        assertFalse(board.checkWin('O'));
    }
}