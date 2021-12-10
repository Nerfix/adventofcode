import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TokenChecker {
  public TokenChecker() {}

  // @param input the string to validate
  // @return token the corrupted token or null of none
  public static Token getCorruptedToken(ArrayList<Token> input) {
    Deque<Token> tokensStack = new ArrayDeque<>();

    for (Token token : input) {
      if (isOpenToken(token)) {
        tokensStack.push(token);
      } else {
        if (tokenPairIsLegal(tokensStack.peek(), token)) {
          tokensStack.pop();
        } else {
          return token;
        }
      }
    }

    return null;
  }

  public static ArrayList<Token> getClosingCharacters(ArrayList<Token> input) {
    ArrayList<Token> closingCharacters = new ArrayList<>();

    if (getCorruptedToken(input) == null) {
      Deque<Token> tokensStack = new ArrayDeque<>();

      for (Token token : input) {
        if (isOpenToken(token)) {
          tokensStack.push(token);
        } else {
          tokensStack.pop();
        }
      }
      
      while(!tokensStack.isEmpty()) {
        closingCharacters.add(tokensStack.pop());
      }
    }
    return closingCharacters;
  }

  private static boolean tokenPairIsLegal(Token token1, Token token2) {
    if (token1 == Token.OPEN_ANGLE_BRACKET && token2 == Token.CLOSE_ANGLE_BRACKET) {
      return true;
    }

    if (token1 == Token.OPEN_BRACKET && token2 == Token.CLOSE_BRACKET) {
      return true;
    }

    if (token1 == Token.OPEN_CURLY_BRACKET && token2 == Token.CLOSE_CURLY_BRACKET) {
      return true;
    }

    if (token1 == Token.OPEN_PARENTHESIS && token2 == Token.CLOSE_PARENTHESIS) {
      return true;
    }

    return false;
  }

  private static boolean isOpenToken(Token token) {
    switch(token) {
      case OPEN_ANGLE_BRACKET:
      case OPEN_BRACKET:
      case OPEN_CURLY_BRACKET:
      case OPEN_PARENTHESIS:
        return true;
      default:
        return false;
    }
  }
}
