package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private Board board;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;

    @BeforeEach
    void setUp() {
        board = new Board();
        humanPlayer = new HumanPlayer('X');
        computerPlayer = new ComputerPlayer('O');
    }

    @Test
    void testHumanPlayerInitialization() {
        assertEquals('X', humanPlayer.getSymbol());
    }

    @Test
    void testComputerPlayerInitialization() {
        assertEquals('O', computerPlayer.getSymbol());
    }

    @Test
    void testEmptyBoardInitialization() {
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isCellEmpty(i));
        }
    }

    @Test
    void testHumanPlayerMove() {
        // Simulate human move (in real test would mock input)
        int move = humanPlayer.makeMove(board);
        assertTrue(move >= 1 && move <= 9);
        assertTrue(board.isCellEmpty(move)); // Move not actually made yet
    }

    @Test
    void testComputerFirstMoveIsCorner() {
        int move = computerPlayer.makeMove(board);
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9);
    }

    @Test
    void testComputerBlocksHumanWin() {
        // Setup human almost winning
        board.setCell(1, 'X');
        board.setCell(2, 'X');
        int move = computerPlayer.makeMove(board);
        assertEquals(3, move);
    }

    @Test
    void testComputerTakesWinWhenAvailable() {
        // Setup computer almost winning
        board.setCell(1, 'O');
        board.setCell(2, 'O');
        int move = computerPlayer.makeMove(board);
        assertEquals(3, move);
    }

    @Test
    void testGameWinDetection() {
        board.setCell(1, 'X');
        board.setCell(2, 'X');
        board.setCell(3, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    void testGameDrawDetection() {
        board.setCell(1, 'X');
        board.setCell(2, 'O');
        board.setCell(3, 'X');
        board.setCell(4, 'X');
        board.setCell(5, 'O');
        board.setCell(6, 'O');
        board.setCell(7, 'O');
        board.setCell(8, 'X');
        board.setCell(9, 'X');
        assertTrue(board.isFull());
        assertFalse(board.checkWin('X'));
        assertFalse(board.checkWin('O'));
    }
}