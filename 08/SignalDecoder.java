import java.util.ArrayList;
import java.util.HashMap;

public class SignalDecoder {
  public int decodeSimple(ArrayList<SignalEntry> signalEntries) {
    int count = 0;

    for (SignalEntry signalEntry : signalEntries) {
      for (String signal : signalEntry.output) {
        if(isSimpleDigit(signal)) {
          count += 1;
        }
      }
    }

    return count;
  }

  private boolean isSimpleDigit(String signal) {
    switch(signal.length()){
      case 2:
      case 3:
      case 4:
      case 7:
        return true;
      default:
        return false;
    }
  }

  public int decode(ArrayList<SignalEntry> signalEntries) {
    int finalOutput = 0;
    for (SignalEntry signalEntry : signalEntries) {
      HashMap<Character, Character> signalMap = decodeSignals(signalEntry.signals);
      int decodedOutput = decodeOutput(signalEntry.output, signalMap);
      finalOutput += decodedOutput;
    }

    return finalOutput;
  }

  private HashMap<Character, Character> decodeSignals(ArrayList<String> signals) {
    HashMap<Character, Character> decodedSignals = initialiseDecodedSignals();
    HashMap<Character, Integer> occurances = initialiseOccurances();
    String signalForOne = "";
    String signalForFour = "";
    String signalForSeven = "";
    String signalForEight = "";

    for (String signal : signals) {
      if (signal.length() == 2) {
        signalForOne = signal;
      } else if (signal.length() == 3) {
        signalForSeven = signal;
      } else if (signal.length() == 4) {
        signalForFour = signal;
      } else if (signal.length() == 7) {
        signalForEight = signal;
      }

      for (char segment : signal.toCharArray()) {
        int currentOccurance = occurances.get(segment);
        occurances.put(segment, currentOccurance + 1);
      }
    }

    for (char ch : signalForSeven.toCharArray()) {
      if (!signalForOne.contains("" + ch)) {
        decodedSignals.put(ch, 'a');
      }
    }

    for (Character key : occurances.keySet()) {
      // Decoded
      // aaaa
      //f    b
      //f    b
      // gggg
      //e    c
      //e    c
      // dddd
      switch(occurances.get(key)) {
        case 4:
          decodedSignals.put(key, 'e');
          break;
        case 6:
          decodedSignals.put(key, 'b');
          break;
        case 8:
          if (decodedSignals.get(key) == null) {
            decodedSignals.put(key, 'c');
          }
          break;
        case 9:
          decodedSignals.put(key, 'f');
          break;
      }
    }

    for (char ch : signalForFour.toCharArray()) {
      if (decodedSignals.get(ch) == null) {
        decodedSignals.put(ch, 'd');
      }
    }

    for (char ch : signalForEight.toCharArray()) {
      if (decodedSignals.get(ch) == null) {
        decodedSignals.put(ch, 'g');
      }
    }

    return decodedSignals;
  }

  private int decodeOutput(ArrayList<String> output, HashMap<Character, Character> signalMap) {
    String fullOutputDecoded = "";

    for (String signal : output) {
      fullOutputDecoded += decodeNumber(signal, signalMap);
    }

    return Integer.parseInt(fullOutputDecoded);
  }

  private int decodeNumber(String signal, HashMap<Character, Character> signalMap) {
    String decodedNumber = "";
    for (char segment : signal.toCharArray()) {
      decodedNumber += signalMap.get(segment);
    }

    switch (decodedNumber.length()) {
      case 2:
        return 1;
      case 3:
        return 7;
      case 4:
        return 4;
      case 7:
        return 8;
      case 5:
        // could be 2, 3 or 5
        if (decodedNumber.contains("e")) {
          return 2;
        } else if (decodedNumber.contains("b")) {
          return 5;
        } else {
          return 3;
        }
      case 6:
        // could be 0, 6 or 9
        if (!decodedNumber.contains("d")) {
          return 0;
        } else if (!decodedNumber.contains("c")) {
          return 6;
        } else {
          return 9;
        }
      default:
        return -1;
    }
  }

  private HashMap<Character, Integer> initialiseOccurances() {
    HashMap<Character, Integer> occurances = new HashMap<>();
    occurances.put('a', 0);
    occurances.put('b', 0);
    occurances.put('c', 0);
    occurances.put('d', 0);
    occurances.put('e', 0);
    occurances.put('f', 0);
    occurances.put('g', 0);

    return occurances;
  }

  private HashMap<Character, Character> initialiseDecodedSignals() {
    HashMap<Character, Character> decodedSignals = new HashMap<>();
    decodedSignals.put('a', null);
    decodedSignals.put('b', null);
    decodedSignals.put('c', null);
    decodedSignals.put('d', null);
    decodedSignals.put('e', null);
    decodedSignals.put('f', null);
    decodedSignals.put('g', null);

    return decodedSignals;
  }
}
