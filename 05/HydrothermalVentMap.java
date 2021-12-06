import java.util.ArrayList;

public class HydrothermalVentMap {

    final int MAP_SIZE = 1000;
    final int DANGEROUS_AREA_THRESHOLD = 2;
    ArrayList<ArrayList<Integer>> coordinates;

    public HydrothermalVentMap() {
        coordinates = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            coordinates.add(new ArrayList<>());
            for (int j = 0; j < MAP_SIZE; j++) {
                coordinates.get(i).add(0);
            }
        }
    }

    public void incrementCoordinate(int x, int y) {
        int currentValue = coordinates.get(y).get(x);
        coordinates.get(y).set(x, currentValue + 1);
    }

    public ArrayList<ArrayList<Integer>> getCoordinates(){
        return coordinates;
    }

    public int countDangerousAreas() {
        int dangerousAreas = 0;
        for (ArrayList<Integer> rows : coordinates) {
            for (int value : rows) {
                if (value >= DANGEROUS_AREA_THRESHOLD) {
                    dangerousAreas += 1;
                }
            }
        }

        return dangerousAreas;
    }

    public void print() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                System.out.print(coordinates.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
