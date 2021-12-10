public class TokenGenerator {
  public static Token generateToken(char ch) {
    switch(ch) {
      case '(':
        return Token.OPEN_PARENTHESIS;
      case ')':
        return Token.CLOSE_PARENTHESIS;
      case '[':
        return Token.OPEN_BRACKET;
      case ']':
        return Token.CLOSE_BRACKET;
      case '{':
        return Token.OPEN_CURLY_BRACKET;
      case '}':
        return Token.CLOSE_CURLY_BRACKET;
      case '<':
        return Token.OPEN_ANGLE_BRACKET;
      case '>':
        return Token.CLOSE_ANGLE_BRACKET;
      default:
        return null;
    }
  }
}
