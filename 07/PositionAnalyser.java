import java.util.ArrayList;
import java.util.Comparator;

public class PositionAnalyser {
  public PositionAnalyser() {}

  public int calculateFuelNeeded(ArrayList<Integer> positions) {
    positions.sort(Comparator.naturalOrder());

    int median = positions.get(positions.size() / 2);

    int fuelNeeded = 0;
    for (int i = 0; i < positions.size(); i++) {
      fuelNeeded += Math.abs(median - positions.get(i));
    }

    return fuelNeeded;
  }

  public long calculateFuelNeededImproved(ArrayList<Integer> positions) {
    positions.sort(Comparator.naturalOrder());

    long mean = Math.round(calculateMean(positions)) - 1;

    long fuelNeeded = 0;
    for (int i = 0; i < positions.size(); i++) {
      fuelNeeded += calculateFuelForMoves(Math.abs(mean - positions.get(i)));
    }

    return fuelNeeded;
  }

  public double calculateMean(ArrayList<Integer> positions) {
    int sum = 0;

    for (Integer position: positions) {
      sum += position;
    }

    return sum / (double)positions.size();
  }

  public long calculateFuelForMoves(long moves) {
    int sum = 0;
    for (int i = 0; i < moves; i++) {
      sum += i + 1;
    }

    return sum;
  }
}
