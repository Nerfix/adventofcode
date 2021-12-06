public class Coordinate{
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getDifference(Coordinate newCoordinate) {
        int xDifference = Math.abs(x - newCoordinate.x);
        int yDifference = Math.abs(y - newCoordinate.y);

        return new Coordinate(xDifference, yDifference);
    }

    public int getXDifference(Coordinate newCoordinate) {
        return Math.abs(x - newCoordinate.x);
    }

    public int compareX(Coordinate newCoordinate) {
        return x - newCoordinate.x;
    }

    public int compareY(Coordinate newCoordinate) {
        return y - newCoordinate.y;
    }

    public void print() {
        System.out.println(x + "," + y);
    }
}
