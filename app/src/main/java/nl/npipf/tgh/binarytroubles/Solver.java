package nl.npipf.tgh.binarytroubles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Matthijs on 10-11-2014.
 */
public class Solver {

    private static Random instance = null;

    private Solver() { }  // Exists only to defeat instantiation.

    private static Random getRandom() {
        if(instance == null) {
            instance = new Random();
        }
        return instance;
    }

    /**
     * This method returns a list of solutions to a given binary puzzle
     * @param board
     * @param minSolutions
     * @return
     */
    public static List<Board> solve(Board board, int minSolutions) {
        List<Board> result = new ArrayList<Board>();

        findSolutions(board,result,0,0,minSolutions);

        return result;
    }

    /**
     * This method recursively searches for solutions to the given binary puzzle.
     * @param board The given binary puzzle
     * @param solutions A list with the found solutions
     * @param r The current row
     * @param c The current column
     * @param minSolutions The number of solutions to be found, 0 is unlimited.
     */
    private static void findSolutions(Board board, List<Board> solutions,
                                      int r, int c, int minSolutions) {
        if (minSolutions == 0 || minSolutions > solutions.size()) {
            if (Rules.equalDistribution(board) &&
                    Rules.noMoreThanTwoOfTheSameNumbers(board) &&
                    Rules.noEqualRowsOrColumns(board)) {
                if (r == board.getRowCount()) {
                    solutions.add(board);
                } else {
                    if (board.getSquareAt(r, c) == -1) {
                        if (getRandom().nextInt(2) == 0) {
                            for (int v=1; v>=0; v--) {
                                Board aCopy = board.copy();
                                aCopy.setSquareAt(r, c, v);
                                if (c < board.getColCount()-1) {
                                    findSolutions(aCopy,solutions,r,c+1, minSolutions);
                                } else {
                                    findSolutions(aCopy,solutions,r+1,0, minSolutions);
                                }
                            }
                        } else {
                            for (int v=0; v<2; v++) {
                                Board aCopy = board.copy();
                                aCopy.setSquareAt(r, c, v);
                                if (c < board.getColCount()-1) {
                                    findSolutions(aCopy,solutions,r,c+1, minSolutions);
                                } else {
                                    findSolutions(aCopy,solutions,r+1,0, minSolutions);
                                }
                            }
                        }
                    } else {
                        if (c < board.getColCount()-1) {
                            findSolutions(board,solutions,r,c+1, minSolutions);
                        } else {
                            findSolutions(board,solutions,r+1,0, minSolutions);
                        }
                    }
                }
            }
        }
    }

}