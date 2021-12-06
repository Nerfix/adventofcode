import java.util.ArrayList;

public class Line {
    Coordinate begining;
    Coordinate end;

    public Line(Coordinate begining, Coordinate end) {
        this.begining = begining;
        this.end = end;
    }

    public ArrayList<Coordinate> getAllLineCoordinatesSimplified() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        if (this.isHorizontal()) {
            int difference = begining.compareX(end);
            if (difference > 0) {
                for (int i = end.x; i <= begining.x; i++) {
                    coordinates.add(new Coordinate(i, begining.y));
                }
            } else {
                for (int i = begining.x; i <= end.x; i++) {
                    coordinates.add(new Coordinate(i, begining.y));
                }
            }
        } else if (this.isVertical()) {
            int difference = begining.compareY(end);
            if (difference > 0) {
                for (int i = end.y; i <= begining.y; i++) {
                    coordinates.add(new Coordinate(begining.x, i));
                }
            } else {
                for (int i = begining.y; i <= end.y; i++) {
                    coordinates.add(new Coordinate(begining.x, i));
                }
            }
        } else {
            return null;
        }

        return coordinates;
    }

    public ArrayList<Coordinate> getAllLineCoordinates() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        if (this.isHorizontal()) {
            int difference = begining.compareX(end);
            if (difference > 0) {
                for (int i = end.x; i <= begining.x; i++) {
                    coordinates.add(new Coordinate(i, begining.y));
                }
            } else {
                for (int i = begining.x; i <= end.x; i++) {
                    coordinates.add(new Coordinate(i, begining.y));
                }
            }
        } else if (this.isVertical()) {
            int difference = begining.compareY(end);
            if (difference > 0) {
                for (int i = end.y; i <= begining.y; i++) {
                    coordinates.add(new Coordinate(begining.x, i));
                }
            } else {
                for (int i = begining.y; i <= end.y; i++) {
                    coordinates.add(new Coordinate(begining.x, i));
                }
            }
        } else {
            int xDifference = begining.compareX(end);
            int yDifference = begining.compareY(end);
            
            if (xDifference < 0 && yDifference < 0) {
                // A - both increasing
                for (int i = 0; i <= Math.abs(xDifference); i++) {
                    coordinates.add(new Coordinate(begining.x + i, begining.y + i));
                }
            } else if (xDifference > 0 && yDifference > 0) {
                // B - both decreasing
                for (int i = 0; i <= Math.abs(xDifference); i++) {
                    coordinates.add(new Coordinate(begining.x - i, begining.y - i));
                }
            } else if (xDifference < 0 && yDifference > 0) {
                // C - x increasing y decreasing
                for (int i = 0; i <= Math.abs(xDifference); i++) {
                    coordinates.add(new Coordinate(begining.x + i, begining.y - i));
                }
            } else if (xDifference > 0 && yDifference < 0) {
                // D - x decreasing y increasing
                for (int i = 0; i <= Math.abs(xDifference); i++) {
                    coordinates.add(new Coordinate(begining.x - i, begining.y + i));
                }
            }
        }

        // System.out.println("Line coordinates:");
        // for (Coordinate c : coordinates) {
        //     c.print();
        // }

        return coordinates;
    }

    private boolean isHorizontal() {
        return begining.y == end.y ? true : false;
    }

    private boolean isVertical() {
        return begining.x == end.x ? true : false;
    }
}
