package edu.hw1.task8;

public final class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private static final int ONE_SQUARE = 1;
    private static final int TWO_SQUARES = 2;

    private ChessBoard() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (isInvalidBoard(board)) {
            throw new IllegalArgumentException("Board must be 8x8 and contain only 0/1 values");
        }
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == 1 && canKnightCapture(board, row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInvalidBoard(int[][] board) {
        if (board == null || board.length != BOARD_SIZE) {
            return true;
        }
        for (int[] row : board) {
            if (row == null || row.length != BOARD_SIZE) {
                return true;
            }
            for (int value : row) {
                if (value != 0 && value != 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean canKnightCapture(int[][] board, int row, int column) {
        int[][] possibleMoves = {
            {-TWO_SQUARES, ONE_SQUARE},
            {-ONE_SQUARE, TWO_SQUARES},
            {ONE_SQUARE, TWO_SQUARES},
            {TWO_SQUARES, ONE_SQUARE}
        };
        for (int[] possibleMove : possibleMoves) {
            int currentColumn = column + possibleMove[0];
            int currentRow = row + possibleMove[1];

            if (currentColumn >= 0 && currentColumn < BOARD_SIZE
                && currentRow < BOARD_SIZE
                && board[currentRow][currentColumn] == 1) {
                return true;
            }
        }
        return false;
    }
}
