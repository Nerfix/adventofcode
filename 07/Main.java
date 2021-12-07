import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    long part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<Integer> positions = parseInput(fileName);
    PositionAnalyser positionAnalyser = new PositionAnalyser();

    return positionAnalyser.calculateFuelNeeded(positions);
  }

  public static long part2(String fileName) {
    ArrayList<Integer> positions = parseInput(fileName);
    PositionAnalyser positionAnalyser = new PositionAnalyser();

    return positionAnalyser.calculateFuelNeededImproved(positions);
  }

  public static ArrayList<Integer> parseInput(String fileName) {
    ArrayList<Integer> submarinePositions = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        for (String token: line.split(",")) {
          submarinePositions.add(Integer.parseInt(token));
        }
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return submarinePositions;
  }
}