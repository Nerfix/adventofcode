import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Bingo {
    ArrayList<BingoBoard> bingoBoards;
    ArrayList<Integer> chosenNumbers;

    public Bingo() {
        bingoBoards = new ArrayList<>();
        chosenNumbers = new ArrayList<>();
    }

    public void setChosenNumbers(ArrayList<Integer> chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
    }

    public void addChosenNumber(int chosenNumber) {
        chosenNumbers.add(chosenNumber);
    }

    public void addBingoBoard(BingoBoard bingoBoard) {
        bingoBoards.add(bingoBoard);
    }

    public int runBingo() {
        int result = 0;

        for (int i = 0; i < chosenNumbers.size(); i++) {
            int currentChosenNumber = chosenNumbers.get(i);
            for (int j = 0; j < bingoBoards.size(); j++) {
                bingoBoards.get(j).markNumber(currentChosenNumber);
                if (bingoBoards.get(j).checkForBingo()) {
                    ArrayList<Integer> unmarkedNumbers = bingoBoards.get(j).getUnmarkedNumbers();
                    int sum = unmarkedNumbers.stream().reduce(0, Integer::sum);
                    return result = sum * currentChosenNumber;
                }
            }
        }

        return result;
    }

    public int runReverseBingo() {
        int result = 0;

        Set<Integer> winningBoards = new HashSet<>();

        for (int i = 0; i < chosenNumbers.size(); i++) {
            int currentChosenNumber = chosenNumbers.get(i);
            for (int j = 0; j < bingoBoards.size(); j++) {
                bingoBoards.get(j).markNumber(currentChosenNumber);
                if (bingoBoards.get(j).checkForBingo()) {
                    if (!winningBoards.contains(j) && winningBoards.size() == bingoBoards.size() - 1) {
                        ArrayList<Integer> unmarkedNumbers = bingoBoards.get(j).getUnmarkedNumbers();
                        int sum = unmarkedNumbers.stream().reduce(0, Integer::sum);
                        return result = sum * currentChosenNumber;
                    } else {
                        winningBoards.add(j);
                    }
                }
            }
        }

        return result;
    }
}
