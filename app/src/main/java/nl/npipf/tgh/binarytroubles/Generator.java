package nl.npipf.tgh.binarytroubles;

import java.util.List;
import java.util.Random;

/**
 * Created by Matthijs on 10-11-2014.
 */
public class Generator {

    private static Random instance = null;

    private Generator() { }  // Exists only to defeat instantiation.

    private static Random getRandom() {
        if(instance == null) {
            instance = new Random();
        }
        return instance;
    }

    /**
     * Generate a new binary puzzle.
     * @param r number of rows
     * @param c number of columns
     * @param e number of empty squares
     * @return the generated puzzle
     */
    public static Board generate(int r, int c, int e) {
        Board result = null;

        while (result == null) { // Try several times, depending on initial 'solved' situation.
            Board puzzle = new Board(r,c);
            List<Board> solutions = Solver.solve(puzzle, 1);
            if (solutions.size() > 0) {
                puzzle = solutions.get(0);
            }

            result = emptySquares(puzzle,e);
        }

        return result;
    }

    /**
     * Recursively clear cells.
     * @param board The board to clear cells from
     * @param emptySquares The number of cells to clear
     * @return The resulting board.
     */
    private static Board emptySquares(Board board, int emptySquares) {
        Board result = null;
        int[] indexList = generateRandomList(board.getRowCount()*board.getColCount());

        if (emptySquares > 0) {
            for (int i=0; i<(indexList.length-(emptySquares-1)); i++) {
                if (board.getSquareAt(indexList[i]/board.getColCount(),
                        indexList[i]%board.getColCount()) != -1) {
                    Board aCopy = board.copy();
                    aCopy.setSquareAt(indexList[i]/board.getColCount(),
                            indexList[i]%board.getColCount(), -1);
                    if (hasUniqueSolution(aCopy)) {
                        Board returnVal = emptySquares(aCopy,emptySquares-1);
                        if (returnVal != null) {
                            result = returnVal;
                            break;
                        }
                    }

                }

            }
        } else {
            result = board;
        }

        return result;
    }

    private static boolean hasUniqueSolution(Board board) {
        List<Board> solutions = Solver.solve(board, 2);
        return (solutions.size() == 1);
    }

    private static int[] generateRandomList(int length) {
        int [] result = new int[length];

        for (int i=0; i<length; i++) {
            result[i] = i;
        }

        for (int j=0; j<10; j++) {
            for (int i=0; i<length; i++) {
                int r = getRandom().nextInt(length);
                int t = result[i];
                result[i] = result[r];
                result[r]= t;
            }
        }

        return result;
    }

}