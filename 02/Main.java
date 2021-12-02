import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main {

  final static int CAPACITY = 1000;
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    int part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<Command> commands = parseInput(fileName);

    int finalDistance = 0;
    int finalDepth = 0;
    for (int i = 0; i < commands.size(); i++) {
      if (commands.get(i).direction.equals("forward")) {
        finalDistance += commands.get(i).value;
      } else if (commands.get(i).direction.equals("down")) {
        finalDepth += commands.get(i).value;
      } else if (commands.get(i).direction.equals("up")){
        finalDepth -= commands.get(i).value;
      }
    }

    int product = finalDepth * finalDistance;

    return product;
  }

  public static int part2(String fileName) {
    ArrayList<Command> commands = parseInput(fileName);

    int finalDistance = 0;
    int finalDepth = 0;
    int aim = 0;
    for (int i = 0; i < commands.size(); i++) {
      if (commands.get(i).direction.equals("forward")) {
        finalDistance += commands.get(i).value;
        finalDepth += aim * commands.get(i).value;
      } else if (commands.get(i).direction.equals("down")) {
        aim += commands.get(i).value;
      } else if (commands.get(i).direction.equals("up")){
        aim -= commands.get(i).value;
      }
    }

    int product = finalDepth * finalDistance;

    return product;
  }

  public static ArrayList<Command> parseInput(String fileName) {
    ArrayList<Command> commands = new ArrayList<>();

    commands.ensureCapacity(CAPACITY);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] tokens = line.split("\\s+");

        commands.add(new Command(tokens[0], Integer.parseInt(tokens[1])));
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return commands;
  }
}