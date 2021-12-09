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
    HeightMapAnalyser heightMapAnalyser = new HeightMapAnalyser(parseInput(fileName));
    return heightMapAnalyser.calculateRiskLevel();
  }

  public static int part2(String fileName) {

    return 0;
  }

  public static ArrayList<ArrayList<Zone>> parseInput(String fileName) {
    ArrayList<ArrayList<Zone>> heightMap = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      
      while ((line = br.readLine()) != null) {
        ArrayList<Zone> heights = new ArrayList<>();
        for (char height : line.toCharArray()) {
          heights.add(new Zone(Integer.parseInt("" + height)));
        }
        heightMap.add(heights);
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return heightMap;
  }
}