import java.util.ArrayList;

public class SchoolOfFish {
  final static int INITIAL_OFFSPRING_CYCLE = 8;
  final static int OFFSPRING_CYCLE = 6;
  ArrayList<Integer> schoolOfFish;

  public SchoolOfFish(ArrayList<Integer> schoolOfFish) {
    this.schoolOfFish = schoolOfFish;
  }

  public void addFish() {
    schoolOfFish.add(INITIAL_OFFSPRING_CYCLE);
  }
}
