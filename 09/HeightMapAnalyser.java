import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

  public ArrayList<Integer> findBasinSizes() {
    ArrayList<Integer> basinSizes = new ArrayList<>();

    for (int row = 0; row < heights.size(); row++) {
      for (int entry = 0; entry < heights.get(row).size(); entry++) {
        Point coordinates = new Point(row, entry);
        Integer basinSize = calculateBasinSize(coordinates);

        if (basinSize != 0) {
          basinSizes.add(basinSize);
        }
      }
    }

    return basinSizes;
  }

  private int calculateBasinSize(Point origin) {
    if (heights.get(origin.x).get(origin.y).height == 9 ||
        heights.get(origin.x).get(origin.y).checked) {
      return 0;
    }
    markCoordinates(origin);
    ArrayList<Point> neighbours = getNeighbours(origin);
    int counter = 1;

    if (neighbours.size() == 0) {
      return counter;
    }

    for (Point p : neighbours) {
      counter += calculateBasinSize(p);
    }

    return counter;

  }

  private ArrayList<Point> getNeighbours(Point origin) {
    ArrayList<Point> neighbours = new ArrayList<>();

    if (origin.x != 0) {
      Point neighbourCoordinates = new Point(origin.x - 1, origin.y);
      if (heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).height != 9 &&
          !heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).checked) {
        
        neighbours.add(new Point(neighbourCoordinates));
      }
    }

    if (origin.x < heights.size() - 1) {
      Point neighbourCoordinates = new Point(origin.x + 1, origin.y);
      if (heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).height != 9 &&
          !heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).checked) {
        
        neighbours.add(new Point(neighbourCoordinates));
      }
    }

    if (origin.y != 0) {
      Point neighbourCoordinates = new Point(origin.x, origin.y - 1);
      if (heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).height != 9 &&
          !heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).checked) {
        
        neighbours.add(new Point(neighbourCoordinates));
      }
    }

    if (origin.y < heights.get(0).size() - 1) {
      Point neighbourCoordinates = new Point(origin.x, origin.y + 1);
      if (heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).height != 9 &&
          !heights.get(neighbourCoordinates.x).get(neighbourCoordinates.y).checked) {
        
        neighbours.add(new Point(neighbourCoordinates));
      }
    }

    return neighbours;
  }
  
  private ArrayList<Integer> findLocalMinimums() {
    Set<Point> localMinimumsCoordinates = new HashSet<>();

    for (int row = 0; row < heights.size(); row++) {
      for (int entry = 0; entry < heights.get(row).size(); entry++) {
        Point coordinates = new Point(row, entry);
        Point localMinimaCoordinates = findLocalMinimaSimple(coordinates);
        if (localMinimaCoordinates != null) {
          localMinimumsCoordinates.add(localMinimaCoordinates);
        }
      }
    }

    ArrayList<Integer> localMinimums = new ArrayList<>();
    for (Point coordinate : localMinimumsCoordinates) {
      localMinimums.add(heights.get(coordinate.x).get(coordinate.y).height);
    }
    return localMinimums;
  }

  private Point findLocalMinimaSimple(Point coordinates) {
    Point smallestNeighbour = findSmallestNeighbour(coordinates);

    if (smallestNeighbour == null && hasEqualNeighbour(coordinates)) {
      return null;
    } else if (smallestNeighbour == null) {
      return coordinates;
    }  
    else {
      return findLocalMinimaSimple(smallestNeighbour);
    }
  }

  private boolean hasEqualNeighbour(Point coordinates) {
    int currentValue = heights.get(coordinates.x).get(coordinates.y).height;
    
    if (getTopNeighbourValue(coordinates) != null) {
      if (currentValue == getTopNeighbourValue(coordinates)) {
        return true;
      }
    }

    if (getBottomNeighbourValue(coordinates) != null) {
      if (currentValue == getBottomNeighbourValue(coordinates)) {
        return true;
      }
    }

    if (getLeftNeighbourValue(coordinates) != null) {
      if (currentValue == getLeftNeighbourValue(coordinates)) {
        return true;
      }
    }

    if (getRightNeighbourValue(coordinates) != null) {
      if (currentValue == getRightNeighbourValue(coordinates)) {
        return true;
      }
    }

    return false;
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
    Point smallestNeighbourCoordinates = null;

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
