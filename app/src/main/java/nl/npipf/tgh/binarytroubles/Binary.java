package nl.npipf.tgh.binarytroubles;

import android.util.Log;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Matthijs on 10-11-2014.
 */
public class Binary {

    Board board;

    public Binary() {
        generatePuzzle(10, 10, 70);
    }

    public Binary(int r, int c, int e) {
        generatePuzzle(r, c, e);
    }

    public boolean puzzleGenerated() {
        return (board != null);
    }

    public void printBoard() {
        //board.print();
    }

    public int[][] getBoard(){
        return board.getBoard();
    }

    public void findSolution() {
        List<Board> solutions = Solver.solve(board, 1);
        if (solutions.size() > 0) {
            board = solutions.get(0);
        }
    }

    public int[][] getSolution(){
        List<Board> solutions = Solver.solve(board, 1);
        if(solutions.size() > 0){
            return solutions.get(0).getBoard();
        }
        else{
            throw new UnsupportedOperationException();
        }
    }

    public void generatePuzzle(int r, int c, int e) {
        board = Generator.generate(r, c, e);
    }

}