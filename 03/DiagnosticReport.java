import java.util.ArrayList;

public class DiagnosticReport {
    ArrayList<Integer> bitWords;
    int bitWordSize;

    public DiagnosticReport(ArrayList<Integer> bitWords, int bitWordSize) {
        this.bitWords = bitWords;
        this.bitWordSize = bitWordSize;
    }

    public ArrayList<Integer> getBitWords() {
        return bitWords;
    }

    public int getBitWordSize() {
        return bitWordSize;
    }

    public boolean isEmpty() {
        return bitWords.size() > 0 ? false : true;
    }
}
