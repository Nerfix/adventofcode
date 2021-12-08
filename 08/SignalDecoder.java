import java.util.ArrayList;
import java.util.Collections;
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
    for (SignalEntry signalEntry : signalEntries) {
      HashMap<Character, Character> signalMap = constructSignalMap(signalEntry.signals)
      
    }
  }

  private ArrayList<Character> decodeSignals(ArrayList<String> signals) {
    HashMap<Character, Character> decodedSignals = initialiseDecodedSignals();
    HashMap<Character, Integer> occurances = initialiseOccurances();
    
    for (String signal : signals) {
      for (char segment : signal.toCharArray()) {
        int currentOccurance = occurances.get(segment);
        occurances.put(segment, currentOccurance + 1);
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
          decodedSignals.put(key, 'f');
          break;
      }
      if (occurances.get(key) == 4) {
        // this is segment E
        decodedSignals.put(key, 'e');
      } else


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

  private ArrayList<Character> initialiseDecodedSignals() {
    return new ArrayList<>(Collections.nCopies(7, 'h'));
  }
}
