package nl.npipf.tgh.binarytroubles;

/**
 * Created by Matthijs on 10-11-2014.
 */
public class Board {
    private int squares[][];
    private int rows;
    private int cols;

    public Board() {
        setupBoard(10, 10);
    }

    public Board(int r, int c) {
        setupBoard(r, c);
    }

    public void setupBoard(int r, int c) {
        squares = new int[r][c];
        rows = r;
        cols = c;
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                squares[i][j] = -1;
            }
        }
    }

    public int getSquareAt(int r, int c) {
        return squares[r][c];
    }

    public void setSquareAt(int r, int c, int value) {
        squares[r][c] = value;
    }

    public int getRowCount() {
        return rows;
    }

    public int getColCount() {
        return cols;
    }

    /*private void printSquare(int r, int c) {
        if (squares[r][c] != -1) {
            System.out.print(squares[r][c]);
        } else {
            System.out.print(" ");
        }
    }*/

    public void print() {
        /*for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                System.out.print("+-");
            }
            System.out.println("+");

            for (int j=0; j<cols; j++) {
                System.out.print("|");
                printSquare(i,j);
            }
            System.out.println("|");

        }
        for (int j=0; j<cols; j++) {
            System.out.print("+-");
        }
        System.out.println("+");*/
    }

    public int[][] getBoard(){
        return squares;
    }

    public Board copy() {
        Board aCopy = new Board();
        aCopy.cols = cols;
        aCopy.rows = rows;
        aCopy.squares = new int[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                aCopy.setSquareAt(i, j, getSquareAt(i,j));
            }
        }
        return aCopy;
    }

}