public class WindowSlice {
  public int value1;
  public int value2;
  public int value3;

  public WindowSlice() {
    this.value1 = 0;
    this.value2 = 0;
    this.value3 = 0;
  }
  
  public WindowSlice(int value1, int value2, int value3) {
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
  }

  public void set(int value1, int value2, int value3) {
    this.value1 = value1;
    this.value2 = value2;
    this.value3 = value3;
  }

  public int compare(int value1, int value2, int value3) {
    int sliceSum = this.value1 + this.value2 + this.value3;
    int otherSum = value1 + value2 + value3;

    return sliceSum - otherSum;
  }
}
