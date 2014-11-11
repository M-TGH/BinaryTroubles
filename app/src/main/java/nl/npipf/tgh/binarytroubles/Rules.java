package nl.npipf.tgh.binarytroubles;

/**
 * Created by Matthijs on 10-11-2014.
 */
public class Rules {

    private Rules() { } // No instantiation of this class

    /**
     * This method checks if there are just as many zeros and ones in every
     * row and every column.
     * @param board The binary puzzle board
     * @return A boolean value indicating if zero's and one's are equally distributed.
     */
    public static boolean equalDistribution(Board board) {
        int countOnes;
        int countZeros;
        int maxCountOfEachNumber;
        boolean result;

        result = true;

        // Count each row
        maxCountOfEachNumber = board.getColCount() / 2;
        for (int i=0; i<board.getRowCount(); i++) {
            countOnes = countZeros = 0;

            for (int j=0; j<board.getColCount(); j++) {
                if (board.getSquareAt(i, j) == 0)
                    countZeros++;
                if (board.getSquareAt(i, j) == 1)
                    countOnes++;
            }
            if (countZeros > maxCountOfEachNumber ||
                    countOnes > maxCountOfEachNumber) {
                result = false;
            }
        }

        // Count each column
        maxCountOfEachNumber = board.getRowCount() / 2;
        for (int i=0; i<board.getColCount(); i++) {
            countOnes = countZeros = 0;

            for (int j=0; j<board.getRowCount(); j++) {
                if (board.getSquareAt(j, i) == 0)
                    countZeros++;
                if (board.getSquareAt(j, i) == 1)
                    countOnes++;
            }
            if (countZeros > maxCountOfEachNumber ||
                    countOnes > maxCountOfEachNumber) {
                result = false;
            }
        }

        return result;
    }

    /**
     * This method checks if there are no more than two of the same number
     * next to or under each other.
     * @param board The binary puzzle board
     * @return A boolean indicating whether this board situation complies to the
     * described rule.
     */
    public static boolean noMoreThanTwoOfTheSameNumbers(Board board) {
        boolean result;
        boolean alreadyCounted;
        int currentlyCounting;

        result = true;
        for (int i=0; i<board.getRowCount(); i++) {
            alreadyCounted = false;
            currentlyCounting = -1;
            for (int j=0; j<board.getColCount(); j++) {
                if (currentlyCounting == board.getSquareAt(i, j) &&
                        currentlyCounting != -1) {
                    if (alreadyCounted) {
                        result = false;
                    } else {
                        alreadyCounted = true;
                    }
                } else {
                    currentlyCounting = board.getSquareAt(i, j);
                    alreadyCounted = false;
                }
            }
        }

        for (int i=0; i<board.getColCount(); i++) {
            alreadyCounted = false;
            currentlyCounting = -1;
            for (int j=0; j<board.getRowCount(); j++) {
                if (currentlyCounting == board.getSquareAt(j, i) &&
                        currentlyCounting != -1) {
                    if (alreadyCounted) {
                        result = false;
                    } else {
                        alreadyCounted = true;
                    }
                } else {
                    currentlyCounting = board.getSquareAt(j, i);
                    alreadyCounted = false;
                }
            }
        }

        return result;
    }

    /**
     * This methods checks whether the puzzle has any duplicate rows or columns.
     * @param board The puzzle board
     * @return A boolean true if there are no duplicate rows or columns.
     */
    public static boolean noEqualRowsOrColumns(Board board) {
        boolean result;

        result = true;
        // Check rows for duplicates
        for (int i=0; i<board.getRowCount(); i++) {
            if (!rowHasVars(board,i) && board.getRowCount()>i+1) {
                for (int j=i+1; j<board.getRowCount(); j++) {
                    boolean isEqual = true;
                    for (int k=0; k<board.getColCount(); k++) {
                        if (board.getSquareAt(i, k) != board.getSquareAt(j, k)) {
                            isEqual = false;
                        }
                    }
                    if (isEqual) {
                        result = false;
                    }
                }
            }
        }

        // Check columns for duplicates
        for (int i=0; i<board.getColCount(); i++) {
            if (!colHasVars(board,i) && board.getColCount()>i+1) {
                for (int j=i+1; j<board.getColCount(); j++) {
                    boolean isEqual = true;
                    for (int k=0; k<board.getRowCount(); k++) {
                        if (board.getSquareAt(k, i) != board.getSquareAt(k, j)) {
                            isEqual = false;
                        }
                    }
                    if (isEqual) {
                        result = false;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Checks is a row on a board still has empty squares.
     * @param board The board to check
     * @param r The row to check
     * @return The result with true indicating there are still squares to be filled
     */
    private static boolean rowHasVars(Board board, int r) {
        boolean result;

        result = false;
        for (int i=0; i<board.getColCount(); i++) {
            if (board.getSquareAt(r, i) == -1) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks is a column on a board still has empty squares.
     * @param board The board to check
     * @param c The column to check
     * @return The result with true indicating there are still squares to be filled
     */
    private static boolean colHasVars(Board board, int c) {
        boolean result;

        result = false;
        for (int i=0; i<board.getRowCount(); i++) {
            if (board.getSquareAt(i, c) == -1) {
                result = true;
            }
        }
        return result;
    }

}