import java.util.ArrayList;

public class TimedFishAnalyser {
  SchoolOfFish schoolOfFish;

  public TimedFishAnalyser(ArrayList<Integer> schoolOfFish) {
    this.schoolOfFish = new SchoolOfFish(schoolOfFish);
  }

  public int simulate(int iterations) {
    for (int i = 0; i < iterations; i++) {
      //System.out.println("Simulating day: " + (i + 1));
      simulateDay();
    }

    return schoolOfFish.schoolOfFish.size();
  }

  private void simulateDay() {
    int newFishes = 0;

    for (int i = 0; i < schoolOfFish.schoolOfFish.size(); i++) {
      int currentFishState = schoolOfFish.schoolOfFish.get(i);
      int newFishState;

      if (currentFishState == 0) {
        newFishState = SchoolOfFish.OFFSPRING_CYCLE;
        newFishes += 1;
      } else {
        newFishState = currentFishState - 1;
      }
      schoolOfFish.schoolOfFish.set(i, newFishState);
    }

    for (int i = 0; i < newFishes; i++) {
      schoolOfFish.addFish();
    }
  }

  public long simulateOptimised(int iterations) {
    ArrayList<Long> stages = initialiseStages();

    for (Integer fish : schoolOfFish.schoolOfFish) {
      long currentStage = stages.get(fish);
      stages.set(fish, currentStage + 1);
    }

    for (int i = 0; i < iterations; i++) {
      //System.out.println("Simulating day: " + (i + 1));
      stages = simulateDayOptimised(stages);
    }

    // for (Long stage: stages) {
    //   System.out.print(stage + ", ");
    // }

    //System.out.println();

    long finalNumberofFishes = stages.stream().reduce(0L, (a, b) -> a + b);
    return finalNumberofFishes;
  }

  private ArrayList<Long> initialiseStages() {
    ArrayList<Long> stages = new ArrayList<>();

    for (int i = 0; i < 9; i++) {
      stages.add(0L);
    }

    return stages;
  }

  private ArrayList<Long> simulateDayOptimised(ArrayList<Long> stages) {
    ArrayList<Long> newStages = initialiseStages();
    for (int i = 0; i < stages.size(); i++) {
      if (i == 0) {
        // Move to 6
        newStages.set(6, stages.get(i));
        // Spawn at 8 eq to the number of fish in stage
        newStages.set(8, stages.get(i));
      } else {
        long currentStageValue = newStages.get(i - 1);
        newStages.set(i - 1, stages.get(i) + currentStageValue);
      }
    }

    return newStages;
  }
}
