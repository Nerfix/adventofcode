import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    int part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<Line> hydrothermalVents = parseInput(fileName);

    //System.out.println("Number of Vents: " + hydrothermalVents.size());
    HydrothermalVentAnalyser hydrothermalVentAnalyser = new HydrothermalVentAnalyser();
    int dangerousAreas = hydrothermalVentAnalyser.countDangerousAreas(hydrothermalVents, true);

    //hydrothermalVentAnalyser.printMap();

    return dangerousAreas;
  }

  public static int part2(String fileName) {
    ArrayList<Line> hydrothermalVents = parseInput(fileName);

    //System.out.println("Number of Vents: " + hydrothermalVents.size());
    HydrothermalVentAnalyser hydrothermalVentAnalyser = new HydrothermalVentAnalyser();
    int dangerousAreas = hydrothermalVentAnalyser.countDangerousAreas(hydrothermalVents, false);

    //hydrothermalVentAnalyser.printMap();

    return dangerousAreas;
  }

  public static ArrayList<Line> parseInput(String fileName) {
    ArrayList<Line> hydrothermalVents = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        ArrayList<Coordinate> lineCoordinates = new ArrayList<>();
        for (String point : line.split("->")) {
          String[] position = point.trim().split(",");
          lineCoordinates.add(new Coordinate(Integer.parseInt(position[0]), Integer.parseInt(position[1])));
        }

        hydrothermalVents.add(new Line(lineCoordinates.get(0), lineCoordinates.get(1)));
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return hydrothermalVents;
  }
}