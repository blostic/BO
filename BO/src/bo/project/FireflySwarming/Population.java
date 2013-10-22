package bo.project.FireflySwarming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Population {

    private int populationSize;
    private int solutionSpaceSize;
    private int numberOfGenerations;

    private double alpha;
    private double beta0;
    private double gamma;        // 1 / sqrt(scale)
    private double coordinateMin;
    private double coordinateMax;

    private List<Firefly> fireflies;
    private Chargeable costCounter;

    public Population(int populationSize, int solutionSpaceSize, int numberOfGenerations, double alpha, double beta0,
                      double gamma, double coordinateMin, double coordinateMax) {
        this.populationSize = populationSize;
        this.solutionSpaceSize = solutionSpaceSize;
        this.numberOfGenerations = numberOfGenerations;

        this.alpha = alpha;
        this.beta0 = beta0;
        this.gamma = gamma;

        this.coordinateMin = coordinateMin;
        this.coordinateMax = coordinateMax;

        fireflies = new ArrayList<Firefly>(populationSize);
        for (int i=0; i<populationSize; ++i) {
            fireflies.add(new Firefly(solutionSpaceSize));
            fireflies.get(i).randomizePosition(coordinateMin, coordinateMax);
        }
    }

    public List<Double> simulate() {
        for (int i=0;i<numberOfGenerations;++i) {
            this.iterateSimulation();
            System.out.println(this.getCostCounter().getCost(this.getBestPosition()));
        }

        return this.getBestPosition();
    }

    public void iterateSimulation() {
        for (int i=0;i<populationSize;++i) {
            boolean isMostAttractive = true;
            for (int j=0;j<populationSize;++j) {
                if (this.costCounter.getCost(fireflies.get(i).getPosition()) > this.costCounter.getCost(fireflies.get(j).getPosition())) {
                    fireflies.get(i).moveBy(this.countDeltaVector(fireflies.get(i),fireflies.get(j)));
                    isMostAttractive = false;
                }
            }
            if (isMostAttractive) {
                fireflies.get(i).moveBy(this.generateRandomDeltaVector());
            }

        }
    }

    public List<Double> countDeltaVector(Firefly a, Firefly b) {
        List<Double> delta = new ArrayList<Double>(solutionSpaceSize);

        double radiusSqr = 0.0;
        for (int c=0; c<solutionSpaceSize; ++c) {
            radiusSqr += Math.pow(a.getPosition().get(c) - b.getPosition().get(c), 2);
        }

        double beta = beta0*Math.exp(-gamma * radiusSqr);
        double coord = 0;
        for (int c=0; c<solutionSpaceSize; ++c) {
            coord = beta*(b.getPosition().get(c) - a.getPosition().get(c));
            coord += alpha * (((new Random()).nextGaussian() * (coordinateMax - coordinateMin)) + coordinateMin);
            delta.add(c,Double.valueOf(coord));
        }

        return delta;
    }

    public List<Double> generateRandomDeltaVector() {
        /*
        Firefly randomPositionFirefly = new Firefly(solutionSpaceSize);
        randomPositionFirefly.randomizePosition(coordinateMin, coordinateMax);

        return countDeltaVector(a,randomPositionFirefly);
        */

        List<Double> randomDelta = new ArrayList<Double>(solutionSpaceSize);
        for (int c=0; c<solutionSpaceSize; ++c) {
            randomDelta.add(c, (new Random()).nextGaussian() * (coordinateMax - coordinateMin) * 0.0005);
        }
        return randomDelta;
    }

    public List<Double> getBestPosition() {
        Firefly bestFirefly = fireflies.get(0);
        for (Firefly f : fireflies) {
            if (this.costCounter.getCost(f.getPosition()) < this.costCounter.getCost(bestFirefly.getPosition())) {
                bestFirefly = f;
            }
        }

        return bestFirefly.getPosition();
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta0() {
        return beta0;
    }

    public void setBeta0(double beta0) {
        this.beta0 = beta0;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public Chargeable getCostCounter() {
        return costCounter;
    }

    public void setCostCounter(Chargeable costCounter) {
        this.costCounter = costCounter;
    }

    /* alternative way to count position cost
    public abstract double getCost(List<Double> position);
      */
}
