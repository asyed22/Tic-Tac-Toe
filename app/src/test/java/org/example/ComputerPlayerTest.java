package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {
    private ComputerPlayer computer;
    private Board board;

    @BeforeEach
    void setUp() {
        computer = new ComputerPlayer('O');
        board = new Board();
    }

    @Test
    void testFirstMoveIsCorner() {
        int move = computer.makeMove(board);
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9);
    }

    @Test
    void testSecondMoveTakesCenter() {
        // Simulate first move
        board.setCell(1, 'X');
        int move = computer.makeMove(board);
        assertEquals(5, move);
    }

    @Test
    void testWinningMove() {
        board.setCell(1, 'O');
        board.setCell(2, 'O');
        int move = computer.makeMove(board);
        assertEquals(3, move);
    }

    @Test
    void testBlocksOpponentWin() {
        board.setCell(1, 'X');
        board.setCell(2, 'X');
        int move = computer.makeMove(board);
        assertEquals(3, move);
    }

    @Test
    void testRandomMoveWhenNoStrategy() {
        board.setCell(1, 'X');
        board.setCell(5, 'O');
        board.setCell(9, 'X');
        int move = computer.makeMove(board);
        assertTrue(move >= 1 && move <= 9);
        assertTrue(board.isCellEmpty(move));
    }
}