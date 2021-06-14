import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MultiDimensionalKnapsackSolver {

    private final int POPULATION_SIZE;
    private final int NUM_OF_GENERATIONS;
    private final double MUTATION_RATE;
    private final double CROSS_OVER_RATE;
    private final List<Item> items;
    private final List<Knapsack> knapsacks;
    private final List<Individual> population;
    private final List<Integer> parentIndices;

    public MultiDimensionalKnapsackSolver(int POPULATION_SIZE, int NUM_OF_GENERATIONS, double MUTATION_RATE,
                                          double CROSS_OVER_RATE, List<Item> items, List<Knapsack> knapsacks) {
        this.POPULATION_SIZE = POPULATION_SIZE;
        this.NUM_OF_GENERATIONS = NUM_OF_GENERATIONS;
        this.MUTATION_RATE = MUTATION_RATE;
        this.CROSS_OVER_RATE = CROSS_OVER_RATE;
        this.items = items;
        this.knapsacks = knapsacks;
        this.population = new ArrayList<>();
        this.parentIndices = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            parentIndices.add(i);
        }
    }

    private Individual randomGenome(int size) {
        Random rand = new Random();
        Individual ind;
        StringBuilder genome = new StringBuilder();

        genome.append("0".repeat(items.size()));
        for (int i = 0; i < size; i++) {
            if (rand.nextBoolean()) {
                genome.setCharAt(i, '1');
                if (!isFeasible(genome.toString()))
                    genome.setCharAt(i, '0');
            }
        }
        ind = new Individual(genome.toString(), evaluateFitness(items, genome.toString()));
        return ind;
    }

    private int evaluateFitness(List<Item> items, String genome) {
        int fitness = 0;
        if (!isFeasible(genome))
            return 0;
        for (int i = 0; i < items.size(); i++) {
            fitness = (genome.charAt(i) == '1') ? (fitness + items.get(i).getValue()) : fitness;
        }
        return fitness;
    }

    private boolean isFeasible(String genome) {
        int totalConstraintSum;
        for (int i = 0; i < knapsacks.size(); i++) {
            totalConstraintSum = 0;
            for (int j = 0; j < items.size(); j++) {
                if (genome.charAt(j) == '1')
                    totalConstraintSum += items.get(j).getConstraints().get(i);
            }
            if (totalConstraintSum > knapsacks.get(i).getCapacity())
                return false;
        }
        return true;
    }

    private List<Individual> selectParents(List<Individual> population) {

        Collections.shuffle(parentIndices);
        List<Individual> parents = new ArrayList<>();

        if (population.get(parentIndices.get(0)).getFitness() > population.get(parentIndices.get(1)).getFitness())
            parents.add(population.get(parentIndices.get(0)));
        else
            parents.add(population.get(parentIndices.get(1)));

        if (population.get(parentIndices.get(2)).getFitness() > population.get(parentIndices.get(3)).getFitness())
            parents.add(population.get(parentIndices.get(2)));
        else
            parents.add(population.get(parentIndices.get(3)));
        return parents;
    }

    private List<Individual> crossOver(Individual male, Individual female) {
        List<Individual> children = new ArrayList<>();
        int midPointIndex =
                female.getGenome().length() - (int) Math.floor(female.getGenome().length() * CROSS_OVER_RATE);

        String maleFirstPart = male.getGenome().substring(0, midPointIndex);
        String maleLastPart = male.getGenome().substring(midPointIndex);

        String femaleFirstPart = female.getGenome().substring(0, midPointIndex);
        String femaleLastPart = female.getGenome().substring(midPointIndex);

        String firstChild = maleFirstPart + femaleLastPart;
        String secondChild = femaleFirstPart + maleLastPart;
        children.add(new Individual(firstChild, evaluateFitness(items, firstChild)));
        children.add(new Individual(secondChild, evaluateFitness(items, secondChild)));

        return children;
    }

    private Individual mutate(Individual individual) {
        Random rand = new Random();
        for (int i = 0; i < individual.getGenome().length(); i++) {
            if (rand.nextDouble() < MUTATION_RATE) {
                String genome = individual.getGenome();
                String mutatedGenome;
                if (genome.charAt(i) == '1')
                    mutatedGenome = genome.substring(0, i) + '0' + genome.substring(i + 1);
                else
                    mutatedGenome = genome.substring(0, i) + '1' + genome.substring(i + 1);
                individual.setGenome(mutatedGenome);
                individual.setFitness(evaluateFitness(items, mutatedGenome));
            }
        }
        return individual;
    }


    private void generateNewGeneration(List<Individual> population) {
        boolean firstChild = false;
        boolean secondChild = false;

        Collections.sort(population);
        List<Individual> parents = selectParents(population);
        List<Individual> children = crossOver(parents.get(0), parents.get(1));
        children.set(0, mutate(children.get(0)));
        children.set(1, mutate(children.get(1)));

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (firstChild && secondChild)
                break;
            if (population.get(i).getGenome().equals(children.get(0).getGenome()))
                firstChild = true;
            if (population.get(i).getGenome().equals(children.get(1).getGenome()))
                secondChild = true;
        }
        if (!firstChild)
            population.set(0, children.get(0));
        if (!secondChild)
            population.set(1, (children.get(1)));
    }

    public Individual getBestSolution() {
        return population.get(population.size() - 1);
    }

    public void run() {

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(randomGenome(items.size()));
        }
        for (int i = 0; i < NUM_OF_GENERATIONS; i++) {
            generateNewGeneration(population);
            System.out.println(population.get(population.size() - 1).getFitness() + " \tGENERATION = " + i);
        }
    }
}
