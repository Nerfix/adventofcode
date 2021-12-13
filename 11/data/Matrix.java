package data;

import java.util.ArrayList;
import java.awt.Point;

public class Matrix<T> {
    private ArrayList<ArrayList<T>> matrix;
    public final int rows;
    public final int cols;

    public Matrix(ArrayList<ArrayList<T>> matrix) {
        this.matrix = matrix;
        this.rows = matrix.size();
        this.cols = matrix.get(0).size();
    }

    public T get(Point coordinates){
        return matrix.get(coordinates.y).get(coordinates.x);
    }

    public void set(Point coordinates, T element) {
        matrix.get(coordinates.y).set(coordinates.x, element);
    }

    public ArrayList<Point> getNeighbours(Point coordinates) {
        ArrayList<Point> neighbours = new ArrayList<>();

        // for matrix of size 3 (3x3) and bigger
        // ABC
        // DEF
        // GHI

        // if col == 0 and row == 0                                     -> Top left corner(A),      3 neighbours (col + 1; row), (col, row + 1), (col + 1, row + 1)
        // if 0 < col < matrix.width - 1 and row == 0                   -> Top row(B),              5 neighbours (col + 1; row), (col, row + 1), (col - 1; row), (col - 1, row + 1)
        // if col == matrix.width - 1 and row == 0                      -> Top right corner(C),     3 neighbours (col - 1; row), (col, row + 1), (col - 1; row + 1)
        // if col == 0 and 0 < row < matrix.height                      -> Left col(D),             5 neighbours (col; row - 1), (col + 1; row - 1), (col + 1; row), (col; row + 1), (col + 1; row + 1)
        // if col == matrix.width - 1  and 0 < row < matrix.height      -> Right col(F),            5 neighbours (col; row - 1), (col; row + 1), (col - 1; row - 1), (col - 1; row), (col - 1; row + 1)
        // if col == 0 and row == matrix.height - 1                     -> Bottom left corner(G)    3 neighbours (col; row - 1), (col + 1; row - 1), (col + 1; row)
        // if 0 < col < matrix.width - 1 and row == matrix.height - 1   -> Bottom row(H)            5 neighbours (col; row - 1), (col - 1; row - 1), (col - 1; row), (col + 1, row - 1), (col + 1; row)
        // if col == matrix.width - 1 and row == matrix.height - 1      -> Bottom right corner(I)   3 neighbours (col; row - 1), (col - 1; row - 1), (col - 1: row)
        // else                                                         -> Inner(E)                 8 neighbouts (col - 1; row - 1), (col; row - 1), (col + 1; row - 1), (col - 1; row), (col + 1; row), (col - 1; row + 1), (col; row + 1), (col + 1; row + 1)
        int row = coordinates.y;
        int col = coordinates.x;
        if (row == 0) {
            if (col == 0) {
                // A - 3
                // (col + 1; row), (col, row + 1), (col + 1, row + 1)
                neighbours.add(new Point(col + 1, row));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col + 1, row + 1));
            } else if (col > 0 && col < matrix.get(row).size() - 1) {
                // B - 5
                // (col + 1; row), (col, row + 1), (col - 1; row), (col - 1, row + 1), (col + 1; row + 1)
                neighbours.add(new Point(col + 1, row));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col - 1, row));
                neighbours.add(new Point(col - 1, row + 1));
                neighbours.add(new Point(col + 1, row + 1));
            } else if (col == matrix.get(row).size() - 1) {
                // C - 3
                // (col - 1, row), (col, row + 1), (col - 1, row + 1)
                neighbours.add(new Point(col - 1, row));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col - 1, row + 1));
            }
        } else if (row > 0 && row < matrix.size() - 1) {
            if (col == 0) {
                // D - 5
                // (col, row - 1), (col + 1, row - 1), (col + 1, row), (col, row + 1), (col + 1, row + 1)
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col + 1, row - 1));
                neighbours.add(new Point(col + 1, row));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col + 1, row + 1));
            } else if (col > 0 && col < matrix.get(row).size() - 1) {
                // E - 8
                // (col - 1, row - 1),  (col, row - 1),     (col + 1, row - 1), (col - 1, row), 
                // (col + 1, row),      (col - 1, row + 1), (col, row + 1),     (col + 1, row + 1)
                neighbours.add(new Point(col - 1, row - 1));
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col + 1, row - 1));
                neighbours.add(new Point(col - 1, row));
                neighbours.add(new Point(col + 1, row));
                neighbours.add(new Point(col - 1, row + 1));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col + 1, row + 1));
            } else if (col == matrix.get(row).size() - 1) {
                // F - 5 
                // (col, row - 1), (col, row + 1), (col - 1, row - 1), (col - 1, row), (col - 1, row + 1)
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col, row + 1));
                neighbours.add(new Point(col - 1, row - 1));
                neighbours.add(new Point(col - 1, row));
                neighbours.add(new Point(col - 1, row + 1));                
            }
        } else if (row == matrix.size() - 1) {
            if (col == 0) {
                // G - 3 
                // (col, row - 1), (col + 1, row - 1), (col + 1, row)
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col + 1, row - 1));
                neighbours.add(new Point(col + 1, row));                 
            } else if (col > 0 && col < matrix.get(row).size() - 1) {
                // H - 5
                // (col, row - 1), (col - 1, row - 1), (col - 1, row), (col + 1, row - 1), (col + 1, row)
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col - 1, row - 1));
                neighbours.add(new Point(col - 1, row));
                neighbours.add(new Point(col + 1, row - 1));
                neighbours.add(new Point(col + 1, row));
            } else if (col == matrix.get(row).size() - 1) {
                // I - 3
                // (col, row - 1), (col - 1, row - 1), (col - 1, row)
                neighbours.add(new Point(col, row - 1));
                neighbours.add(new Point(col - 1, row - 1));
                neighbours.add(new Point(col - 1, row));
            }
        }

        return neighbours;
    }
}
