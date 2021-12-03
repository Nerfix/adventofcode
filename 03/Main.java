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
    DiagnosticReport diagnosticReport = parseInput(fileName);

    if (diagnosticReport.isEmpty()) {
      return 0;
    }

    int gRate = findGammaRate(diagnosticReport);
    int eRate = ~gRate & getBitMask(diagnosticReport.bitWordSize);

    int powerConsumption = gRate * eRate;

    return powerConsumption;
  }

  public static int part2(String fileName) {
    DiagnosticReport diagnosticReport = parseInput(fileName);

    int oxygenGeneratorRating = getLifeSupportData(diagnosticReport.bitWords, diagnosticReport.bitWordSize, LifeSupportSystems.OXYGEN_GENERATOR);
    int carbonDioxideScrubberRating = getLifeSupportData(diagnosticReport.bitWords, diagnosticReport.bitWordSize, LifeSupportSystems.CO2_SCRUBBER);

    int lifeSupportRating = oxygenGeneratorRating * carbonDioxideScrubberRating;

    return lifeSupportRating;
  }

  public static int getLifeSupportData(ArrayList<Integer> bitWords, int index, LifeSupportSystems lifeSupportSystem) {
    boolean dataTypeFlag = lifeSupportSystem == LifeSupportSystems.OXYGEN_GENERATOR ? true : false;

    if (bitWords.size() == 1) {
      return bitWords.get(0);
    }

    ArrayList<Integer> filteredBitWords = new ArrayList<>();
    int commonBit = findCommonBit(bitWords, index, dataTypeFlag);

    for (int i = 0; i < bitWords.size(); i++) {
      int bitPosition = isBitSet(bitWords.get(i), index) ? 1 : 0;
      if (bitPosition == commonBit) {
        filteredBitWords.add(bitWords.get(i));
      }
    }

    return getLifeSupportData(filteredBitWords, index -= 1, lifeSupportSystem);
  }

  public static int getBitMask(int bitWordSize) {
    int bitMask = 0;

    for (int i = 0; i < bitWordSize; i++) {
      bitMask = bitMask << 1;
      bitMask += 1;
    }

    return bitMask;
  }

  public static int findGammaRate(DiagnosticReport diagnosticReport) {
    int gRate = 0;
    
    for (int i = diagnosticReport.bitWordSize; i > 0; i--) {
      if (findCommonBit(diagnosticReport.bitWords, i, true) > 0) {
        gRate = gRate << 1;
        gRate += 1;
      } else {
        gRate = gRate << 1;
      }
    }
    
    return gRate;
  }

  public static int findCommonBit(ArrayList<Integer> bitWords, int index, boolean mostCommon) {
    int setBits = 0;
    int emptyBits = 0;
    
    for (int i = 0; i < bitWords.size(); i++) {
      if (isBitSet(bitWords.get(i), index)) {
        setBits += 1;
      } else {
        emptyBits += 1;
      }
    }

    int mostCommonBit = 0;

    if (mostCommon) {
      mostCommonBit = setBits - emptyBits >= 0 ? 1 : 0;
    } else {
      mostCommonBit = setBits - emptyBits >= 0 ? 0 : 1;
    }
    
    return mostCommonBit;
  }

  public static boolean isBitSet(int value, int position) {
    return ((value >> (position - 1)) & 1) > 0 ? true : false;
  }

  public static DiagnosticReport parseInput(String fileName) {
    ArrayList<Integer> bitWords = new ArrayList<>();
    int bitWordSize = 0;

    bitWords.ensureCapacity(CAPACITY);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (bitWordSize == 0) {
          bitWordSize = line.length();
        }
        bitWords.add(Integer.parseInt(line, 2));
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    DiagnosticReport diagnosticReport = new DiagnosticReport(bitWords, bitWordSize);

    return diagnosticReport;
  }
}