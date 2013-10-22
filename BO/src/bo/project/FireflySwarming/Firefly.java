package bo.project.FireflySwarming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Firefly {

    private List<Double> position;
    private int solutionSpaceSize;

    public Firefly(int solutionSpaceSize) {
        position = new ArrayList<Double>(solutionSpaceSize);
        this.solutionSpaceSize = solutionSpaceSize;
    }

    public void randomizePosition(double min, double max) {
        for (int c=0; c<solutionSpaceSize; ++c) {
            position.add(c, (new Random().nextDouble()) * (max - min) + min);
        }
    }

    public void moveBy(List<Double> delta) {
        for (int i=0; i<solutionSpaceSize;++i) {
            Double coord = this.position.get(i);
            this.position.set(i,coord + delta.get(i));
        }
    }

    public List<Double> getPosition() {
        return this.position;
    }


}
