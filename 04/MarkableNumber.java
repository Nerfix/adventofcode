public class MarkableNumber {
    int value;
    boolean marked;

    public MarkableNumber(int value) {
        this.value = value;
        marked = false;
    }

    public boolean isMarked() {
        return marked;
    }

    public void mark() {
        marked = true;
    }

    public int getValue() {
        return value;
    }
}
