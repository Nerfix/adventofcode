import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

class Main {
  public static void main(String[] args) {
    String fileName = "input.txt";

    int part1Answer = part1(fileName);
    System.out.println("Answer to part 1: " + part1Answer);
    
    long part2Answer = part2(fileName); 
    System.out.println("Answer to part 2: " + part2Answer);
  }

  public static int part1(String fileName) {
    ArrayList<ArrayList<Token>> tokenizedInput = parseInput(fileName);
    ArrayList<Token> corruptedTokens = new ArrayList<>();

    for (ArrayList<Token> tokens : tokenizedInput) {
      Token corruptedToken = TokenChecker.getCorruptedToken(tokens);
      
      if (corruptedToken != null) {
        corruptedTokens.add(corruptedToken);
      }
    }

    int syntaxErrorScore = calculateSyntaxErrorScore(corruptedTokens);
    return syntaxErrorScore;
  }

  public static Long part2(String fileName) {
    ArrayList<ArrayList<Token>> tokenizedInput = parseInput(fileName);
    ArrayList<Long> incompleteCharacterScores = new ArrayList<>();

    for (ArrayList<Token> tokens : tokenizedInput) {
      ArrayList<Token> incompleteCharacters = TokenChecker.getClosingCharacters(tokens);
      
      if (incompleteCharacters.size() > 0) {
        long incompleteCharacterScore = calculateIncompleteCharacterScore(incompleteCharacters);
        incompleteCharacterScores.add(incompleteCharacterScore);
      }
    }

    incompleteCharacterScores.sort(Comparator.naturalOrder());

    return incompleteCharacterScores.get(incompleteCharacterScores.size() / 2);
  }

  private static int calculateSyntaxErrorScore(ArrayList<Token> tokens) {
    int syntaxErrorScore = 0;

    for (Token token : tokens) {
      switch(token) {
        case CLOSE_PARENTHESIS:
          syntaxErrorScore += 3;
          break;
        case CLOSE_BRACKET:
          syntaxErrorScore += 57;
          break;
        case CLOSE_CURLY_BRACKET:
          syntaxErrorScore += 1197;
          break;
        case CLOSE_ANGLE_BRACKET:
          syntaxErrorScore += 25137;
          break;
        default:
          break;
      }
    }

    return syntaxErrorScore;
  }

  private static long calculateIncompleteCharacterScore(ArrayList<Token> tokens) {
    long incompleteCharacterScore = 0;

    for (int i = 0; i < tokens.size(); i++) {
      incompleteCharacterScore *= 5;

      switch(tokens.get(i)) {
        case OPEN_PARENTHESIS:
          incompleteCharacterScore += 1;
          break;
        case OPEN_BRACKET:
          incompleteCharacterScore += 2;
          break;
        case OPEN_CURLY_BRACKET:
          incompleteCharacterScore += 3;
          break;
        case OPEN_ANGLE_BRACKET:
          incompleteCharacterScore += 4;
          break;
        default:
          break;
      }
    }

    return incompleteCharacterScore;
  }

  public static ArrayList<ArrayList<Token>> parseInput(String fileName) {
    ArrayList<ArrayList<Token>> tokenizedInput = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        ArrayList<Token> tokens = new ArrayList<>();
        for (char ch : line.toCharArray()) {
          tokens.add(TokenGenerator.generateToken(ch));
        }
        tokenizedInput.add(tokens);
      }
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return tokenizedInput;
  }
}