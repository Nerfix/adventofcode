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
    TimedFishAnalyser fishAnalyser = new TimedFishAnalyser(parseInput(fileName));

    int result = fishAnalyser.simulate(80);
    return result;
  }

  public static long part2(String fileName) {
    TimedFishAnalyser fishAnalyser = new TimedFishAnalyser(parseInput(fileName));

    long result = fishAnalyser.simulateOptimised(256);
    return result;
  }

  public static ArrayList<Integer> parseInput(String fileName) {
    ArrayList<Integer> schoolOfFish = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        for (String fish : line.split(",")) {
          schoolOfFish.add(Integer.parseInt(fish));
        }
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return schoolOfFish;
  }
}