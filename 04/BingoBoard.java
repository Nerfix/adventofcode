import java.util.ArrayList;

public class BingoBoard {
    final int BINGO_BOARD_SIZE = 5;

    ArrayList<ArrayList<MarkableNumber>> board;

    public BingoBoard() {
        board = new ArrayList<>();
    }

    public BingoBoard(BingoBoard bingoBoard) {
        board = bingoBoard.board;
    }

    public void addRow(ArrayList<MarkableNumber> row) {
        if (row.size() != BINGO_BOARD_SIZE) {
            System.out.println("Error adding row to board, row size doesn't match board size.");
        }

        board.add(row);
    }

    public void markNumber(int number) {
        //System.out.println("Marking number: " + number);
        //System.out.println("Bingo Board rows: " + board.size());

        for (int row = 0; row < BINGO_BOARD_SIZE; row++) {
            for (int col = 0; col < BINGO_BOARD_SIZE; col++) {
                if (board.get(row).get(col).getValue() == number) {
                    board.get(row).get(col).mark();
                }
            }
        }
    }

    public boolean checkForBingo() {
        for (int row = 0; row < BINGO_BOARD_SIZE; row++) {
            int markedNumbers = 0;
            for (int col = 0; col < BINGO_BOARD_SIZE; col++) {
                if (board.get(row).get(col).isMarked()) {
                    markedNumbers += 1;
                }
            }

            if (markedNumbers == BINGO_BOARD_SIZE) {
                return true;
            }
        }

        for (int row = 0; row < BINGO_BOARD_SIZE; row++) {
            int markedNumbers = 0;
            for (int col = 0; col < BINGO_BOARD_SIZE; col++) {
                if (board.get(col).get(row).isMarked()) {
                    markedNumbers += 1;
                }
            }

            if (markedNumbers == BINGO_BOARD_SIZE) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Integer> getUnmarkedNumbers() {
        ArrayList<Integer> unmarkedNumbers = new ArrayList<>();

        for (int row = 0; row < BINGO_BOARD_SIZE; row++) {
            for (int col = 0; col < BINGO_BOARD_SIZE; col++) {
                if (!board.get(row).get(col).isMarked()) {
                    unmarkedNumbers.add(board.get(row).get(col).getValue());
                }
            }
        }

        return unmarkedNumbers;
    }

    public void print() {
        //System.out.println("Board number of rows: " + board.size());
        for (ArrayList<MarkableNumber> row : board) {
            
            //System.out.println("Board row size: " + row.size());
            for (MarkableNumber number : row) {
                if (number.getValue() > 9) {
                    System.out.print(number.getValue() + " ");
                } else {
                    System.out.print(" " + number.getValue() + " ");
                }
            }

            System.out.println();
        }
    }
}
