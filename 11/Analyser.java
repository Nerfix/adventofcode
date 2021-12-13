import java.util.ArrayList;
import java.awt.Point;

import data.Matrix;

public class Analyser {
    Matrix<DumboOctopus> matrix;

    public Analyser(ArrayList<ArrayList<DumboOctopus>> input) {
        matrix = new Matrix<DumboOctopus>(input);
    }

    public int analyse(int steps) {
        int totalFlashes = 0;
        for (int i = 0; i < steps; i++) {
            totalFlashes += simulateStep();
        }
        return totalFlashes;
    }

    public boolean isSwarmSynchronised() {
        // increase charge by 1
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                if (matrix.get(new Point(col, row)).getCharge() != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private int simulateStep() {
        int totalFlashes = 0;

        // increase charge by 1
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                matrix.get(new Point(col, row)).incrementCharge();
            }
        }

        // check charge level and flash(set charge to 0), then increase charge of neighbours...
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                totalFlashes += propagateFlash(new Point(col, row));
            }
        }

        // reset for next step
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                matrix.get(new Point(col, row)).resetFlash();
            }
        }

        //printMatrix();
        //System.out.println();
        return totalFlashes;
    }

    private int propagateFlash(Point coordinates) {
        int numberOfFlashes = 0;
        if (matrix.get(coordinates).getCharge() > 9 && !matrix.get(coordinates).hasFlashed()) {
            matrix.get(coordinates).flash();

            numberOfFlashes += 1;

            ArrayList<Point> neighbours = matrix.getNeighbours(coordinates);

            for (Point neighbour : neighbours) {
                matrix.get(neighbour).incrementCharge();
                numberOfFlashes += propagateFlash(neighbour);
            }
        }

        return numberOfFlashes;
    }

    public void printMatrix() {
        for (int row = 0; row < matrix.rows; row++) {
            for (int col = 0; col < matrix.cols; col++) {
                System.out.print(matrix.get(new Point(col, row)).getCharge());
            }
            System.out.println();
        }
    }
}
