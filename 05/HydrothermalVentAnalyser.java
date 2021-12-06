import java.util.ArrayList;

public class HydrothermalVentAnalyser {
    HydrothermalVentMap map;

    public HydrothermalVentAnalyser() {
        map = new HydrothermalVentMap();
    }

    private void markHydrothermalVent(Line hydrothermalVent, boolean simplifiedMode) {
        ArrayList<Coordinate> coordinates;
        if (simplifiedMode) {
           coordinates = hydrothermalVent.getAllLineCoordinatesSimplified(); 
        } else {
            coordinates = hydrothermalVent.getAllLineCoordinates();
        }

        if (coordinates != null) {
            for (Coordinate coordinate : coordinates) {
                //coordinate.print();
                map.incrementCoordinate(coordinate.x, coordinate.y);
            }
        }
    }

    private void markHydrothermalVents(ArrayList<Line> hydrothermalVents, boolean simplifiedMode) {
        for (Line HydrothermalVent : hydrothermalVents) {
            this.markHydrothermalVent(HydrothermalVent, simplifiedMode);
        }
    }

    public int countDangerousAreas(ArrayList<Line> hydrothermalVents, boolean simplifiedMode) {
        this.markHydrothermalVents(hydrothermalVents, simplifiedMode);

        int dangerousAreas = map.countDangerousAreas();
        return dangerousAreas;
    }

    public void printMap() {
        map.print();
    }
}
