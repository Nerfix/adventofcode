import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    int part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<SignalEntry> signalEntries = parseInput(fileName);
    SignalDecoder signalDecoder = new SignalDecoder();

    return signalDecoder.decodeSimple(signalEntries);
  }

  public static int part2(String fileName) {

    return 0;
  }

  public static ArrayList<SignalEntry> parseInput(String fileName) {
    ArrayList<SignalEntry> signalEntries = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] segments = line.split(" \\| ");

        ArrayList<String> signals = new ArrayList<>(Arrays.asList(segments[0].split("\\s+")));
        ArrayList<String> output = new ArrayList<>(Arrays.asList(segments[1].split("\\s+")));

        signalEntries.add(new SignalEntry(signals, output));
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return signalEntries;
  }
}