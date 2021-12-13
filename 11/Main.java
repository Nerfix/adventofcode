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
    Analyser analyser = new Analyser(parseInput(fileName));
    int flashes = analyser.analyse(100);
    return flashes;
  }

  public static int part2(String fileName) {
    Analyser analyser = new Analyser(parseInput(fileName));
    int steps = 0;

    while (!analyser.isSwarmSynchronised()) {
      analyser.analyse(1);
      steps += 1;
    }
    
    return steps;
  }

  public static ArrayList<ArrayList<DumboOctopus>> parseInput(String fileName) {
    ArrayList<ArrayList<DumboOctopus>> octopusSwarm = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
    
      while ((line = br.readLine()) != null) {
        ArrayList<DumboOctopus> octopusLine = new ArrayList<>();
        for (char ch : line.toCharArray()) {
          octopusLine.add(new DumboOctopus(Integer.parseInt("" + ch)));
        }
        octopusSwarm.add(octopusLine);
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return octopusSwarm;
  }
}