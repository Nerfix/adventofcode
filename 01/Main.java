import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main {

  final static int CAPACITY = 2000;
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    int part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<Integer> depths = parseInput(fileName);

    int currentDepth = 0;
    int depthCounter = 0;
    for (int i = 0; i < depths.size(); i++) {
      if (currentDepth != 0 && currentDepth < depths.get(i)) {
        depthCounter += 1;
      }

      currentDepth = depths.get(i);
    }

    return depthCounter;
  }

  public static int part2(String fileName) {
    ArrayList<Integer> depths = parseInput(fileName);

    WindowSlice currentWindowSlice = new WindowSlice();
    int depthCounter = 0;

    for (int i = 0; i < depths.size() - 2; i++) {
      if (currentWindowSlice.value1 != 0 &&
          currentWindowSlice.compare(depths.get(i), depths.get(i + 1), depths.get(i + 2)) < 0) {
            depthCounter += 1;
      }

      currentWindowSlice.set(depths.get(i), depths.get(i + 1), depths.get(i + 2));
    }

    return depthCounter;
  }

  public static ArrayList<Integer> parseInput(String fileName) {
    ArrayList<Integer> depths = new ArrayList<>();

    depths.ensureCapacity(CAPACITY);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        depths.add(Integer.parseInt(line));
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return depths;
  }
}