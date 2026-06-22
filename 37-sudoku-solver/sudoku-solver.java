public class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    // Backtracking helper function
    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') { // empty cell found
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c; // place digit
                            if (backtrack(board)) {
                                return true; // solved
                            } else {
                                board[row][col] = '.'; // backtrack
                            }
                        }
                    }
                    return false; // no valid digit found, trigger backtracking
                }
            }
        }
        return true; // all cells filled successfully
    }

    // Check if placing c at board[row][col] is valid
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // Check row
            if (board[row][i] == c) return false;
            // Check column
            if (board[i][col] == c) return false;
            // Check 3x3 sub-box
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }
}