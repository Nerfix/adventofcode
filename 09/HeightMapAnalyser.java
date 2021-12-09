import java.awt.Point;
import java.util.ArrayList;

public class HeightMapAnalyser {
  ArrayList<ArrayList<Zone>> heights;
  public HeightMapAnalyser(ArrayList<ArrayList<Zone>> heights) {
    this.heights = heights;
  }

  public int calculateRiskLevel() {
    ArrayList<Integer> localMinimums = findLocalMinimums();
    int riskLevel = 0;

    for (Integer minima : localMinimums) {
      riskLevel += 1 + minima;
    }

    return riskLevel;
  }
  
  private ArrayList<Integer> findLocalMinimums() {
    ArrayList<Integer> localMinimums = new ArrayList<>();

    for (int row = 0; row < heights.size(); row++) {
      for (int entry = 0; entry < heights.get(row).size(); entry++) {
        Point coordinates = new Point(row, entry);
        Point localMinimaCoordinates = findLocalMinima(coordinates);
        if (localMinimaCoordinates != null) {
          int minima = heights.get(localMinimaCoordinates.x).get(localMinimaCoordinates.y).height;
          localMinimums.add(minima);
        }
      }
    }

    return localMinimums;
  }

  private Point findLocalMinima(Point coordinates) {
    if (isVisited(coordinates)) {
      return null;
    }

    markCoordinates(coordinates);
    Point smallestNeighbour = findSmallestNeighbour(coordinates);

    if (smallestNeighbour.equals(coordinates)) {
      return coordinates;
    } else {
      return findLocalMinima(smallestNeighbour);
    }
  }

  private boolean isVisited(Point coordinates) {
    return heights.get(coordinates.x).get(coordinates.y).checked;
  }

  private void markCoordinates(Point coordinates) {
    Zone currentZone = heights.get(coordinates.x).get(coordinates.y);
    currentZone.checked = true;
    heights.get(coordinates.x).set(coordinates.y, currentZone);
  }

  private Point findSmallestNeighbour(Point coordinates) {
    int currentValue = heights.get(coordinates.x).get(coordinates.y).height;
    Point smallestNeighbourCoordinates = new Point(coordinates);

    if (getTopNeighbourValue(coordinates) != null) {
      if (currentValue > getTopNeighbourValue(coordinates)) {
        currentValue = getTopNeighbourValue(coordinates);
        smallestNeighbourCoordinates = new Point(coordinates.x - 1, coordinates.y);
      }
    }

    if (getBottomNeighbourValue(coordinates) != null) {
      if (currentValue > getBottomNeighbourValue(coordinates)) {
        currentValue = getBottomNeighbourValue(coordinates);
        smallestNeighbourCoordinates = new Point(coordinates.x + 1, coordinates.y);
      }
    }

    if (getLeftNeighbourValue(coordinates) != null) {
      if (currentValue > getLeftNeighbourValue(coordinates)) {
        currentValue = getLeftNeighbourValue(coordinates);
        smallestNeighbourCoordinates = new Point(coordinates.x, coordinates.y - 1);
      }
    }

    if (getRightNeighbourValue(coordinates) != null) {
      if (currentValue > getRightNeighbourValue(coordinates)) {
        currentValue = getRightNeighbourValue(coordinates);
        smallestNeighbourCoordinates = new Point(coordinates.x, coordinates.y + 1);
      }
    }

    return smallestNeighbourCoordinates;
  }

  private Integer getTopNeighbourValue(Point coordinates) {
    if (coordinates.x == 0) {
      return null;
    } else {
      return heights.get(coordinates.x - 1).get(coordinates.y).height;
    }
  }

  private Integer getBottomNeighbourValue(Point coordinates) {
    if (coordinates.x == heights.size() - 1) {
      return null;
    } else {
      return heights.get(coordinates.x + 1).get(coordinates.y).height;
    }
  }

  private Integer getLeftNeighbourValue(Point coordinates) {
    if (coordinates.y == 0) {
      return null;
    } else {
      return heights.get(coordinates.x).get(coordinates.y - 1).height;
    }
  }

  private Integer getRightNeighbourValue(Point coordinates) {
    if (coordinates.y == heights.get(0).size() - 1) {
      return null;
    } else {
      return heights.get(coordinates.x).get(coordinates.y + 1).height;
    }
  }
}
