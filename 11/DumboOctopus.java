public class DumboOctopus {
    int charge;
    boolean hasFlashed;

    public DumboOctopus(int charge) {
        this.charge = charge;
        hasFlashed = false;
    }

    public int getCharge() {
        return charge;
    }

    public boolean hasFlashed() {
        return hasFlashed;
    }

    public void flash() {
        charge = 0;
        hasFlashed = true;
    }

    public void resetFlash() {
        hasFlashed = false;
    }

    public void incrementCharge() {
        if (!hasFlashed) {
            charge += 1;
        }
    }
}
