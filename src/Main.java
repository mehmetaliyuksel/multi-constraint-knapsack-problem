import java.util.*;

public class Main {
    public static final int POPULATION_SIZE = 200;
    public static int itemSize = 0;
    public static int generationCount = 0;
    public static List<Item> items;
    public static List<Knapsack> knapsacks;
    public final static int T = 2; // TOURNAMENT_SELECTION_LIMIT
    public final static double MUTATION_CONST = 0.05;


    public static Individual randomGenome(int size) {
        Random rand = new Random();
        Individual ind;
        int fit;
        StringBuilder genome = new StringBuilder();

        genome.append("0".repeat(Math.max(0, itemSize)));
        for (int i = 0; i < size; i++) {
            if (rand.nextBoolean()) {
                genome.setCharAt(i, '1');
                if (!checkFeasibility(genome.toString()))
                    genome.setCharAt(i, '0');
            }
        }
        ind = new Individual(genome.toString(), evaluateFitness(items, genome.toString()));
        return ind;
    }

    public static int evaluateFitness(List<Item> items, String genome) {
        int fitness = 0;
        if (!checkFeasibility(genome))
            return 0;
        for (int i = 0; i < itemSize; i++) {
            fitness = (genome.charAt(i) == '1') ? (fitness + items.get(i).getValue()) : fitness;
        }
        return fitness;
    }

    public static boolean checkFeasibility(String genome) {
        int totalConstraintSum;
        for (int i = 0; i < knapsacks.size(); i++) {
            totalConstraintSum = 0;
            for (int j = 0; j < itemSize; j++) {
                if (genome.charAt(j) == '1')
                    totalConstraintSum += items.get(j).getConstraints().get(i);
            }
            if (totalConstraintSum > knapsacks.get(i).getCapacity())
                return false;
        }
        return true;
    }

    public static List<Individual> selectParents(List<Individual> population) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        List<Individual> parents = new ArrayList<>();

        if (population.get(list.get(0)).getFitness() > population.get(list.get(1)).getFitness())
            parents.add(population.get(list.get(0)));
        else
            parents.add(population.get(list.get(1)));

        if (population.get(list.get(2)).getFitness() > population.get(list.get(3)).getFitness())
            parents.add(population.get(list.get(2)));
        else
            parents.add(population.get(list.get(3)));

        return parents;
    }

    public static List<Individual> crossOver(Individual male, Individual female) {
        List<Individual> children = new ArrayList<>();
        int midPointIndex = (female.getGenome().length() / 2) - 1;

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

    public static Individual mutate(Individual individual) {
        Random rand = new Random();
        for (int i = 0; i < individual.getGenome().length(); i++) {
            if (rand.nextDouble() < MUTATION_CONST) {
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


    public static void generateNewGeneration(List<Individual> population) {
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

    public static void main(String[] args) {
        IOManager ioManager = new IOManager("sample1.txt");

        AbstractMap.SimpleEntry<List<Knapsack>, List<Item>> entry = ioManager.readFile();
        knapsacks = entry.getKey();
        items = entry.getValue();
        itemSize = items.size();
        List<Individual> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(randomGenome(itemSize));
        }
        for (int i = 0; i < 120000; i++) {
            generateNewGeneration(population);
            System.out.println(population.get(population.size() - 1).getFitness());
        }

        ioManager.generateOutput(population.get(population.size() - 1).getFitness(),
                population.get(population.size() - 1).getGenome());
    }

}
