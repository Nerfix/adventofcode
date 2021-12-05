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
    Bingo bingo = parseInput(fileName);
    int result = bingo.runBingo();
    return result;
  }

  public static int part2(String fileName) {
    Bingo bingo = parseInput(fileName);
    int result = bingo.runReverseBingo();
    return result;
  }

  public static Bingo parseInput(String fileName) {
    Bingo bingo = new Bingo();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;

        line = br.readLine();

        for (String entry : line.split(",")) {
            bingo.addChosenNumber(Integer.parseInt(entry));
        }
        line = br.readLine();
        
        BingoBoard bingoBoard = new BingoBoard();

        while ((line = br.readLine()) != null) {
            
            if (line.length() > 0) {
                // populate board
                ArrayList<MarkableNumber> boardRow = new ArrayList<>();
                //System.out.println("Line: " + line);
                for (String entry : line.split("\\s+")) {
                    if (entry.length() > 0) {
                        //System.out.print("[" + entry + "]");
                        boardRow.add(new MarkableNumber(Integer.parseInt(entry)));
                    }
                }
                //System.out.println();
                //System.out.println("Board Row size:" + boardRow.size());
                //System.out.println("Board size: " + bingoBoard.board.size());
                bingoBoard.addRow(boardRow);
            } else {
                // add board to bingo
                //System.out.println("Adding bingo board");
                //bingoBoard.print();
                bingo.addBingoBoard(new BingoBoard(bingoBoard));
                // reset board
                bingoBoard = new BingoBoard();
            }
        }

        bingo.addBingoBoard(new BingoBoard(bingoBoard));
    } catch (IOException ex) {
      System.out.println("Can't open input file.");
    }

    return bingo;
  }
}