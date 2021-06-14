import java.util.*;

public class Main {
    public static int POPULATION_SIZE = 200;
    public static int NUM_OF_GENERATIONS = 20000000;
    public static double MUTATION_RATE = 0.05;
    public static double CROSS_OVER_RATE = 0.5;
    public static String INPUT_FILE = "test1.txt";
    public static String OUTPUT_FILE = "output.txt";
    public static List<Item> items;
    public static List<Knapsack> knapsacks;

    public static void main(String[] args) {
        Map<String, String> options = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                if (args[i].length() < 2)
                    throw new IllegalArgumentException("Not a valid argument: " + args[i]);
                options.put(args[i].substring(1), args[i + 1]);
            }
        }

        if (options.containsKey("i"))
            INPUT_FILE = options.get("i");
        if (options.containsKey("o"))
            OUTPUT_FILE = options.get("o");
        if (options.containsKey("p"))
            POPULATION_SIZE = Integer.parseInt(options.get("p"));
        if (options.containsKey("g"))
            NUM_OF_GENERATIONS = Integer.parseInt(options.get("g"));
        if (options.containsKey("m"))
            MUTATION_RATE = Double.parseDouble(options.get("m"));
        if (options.containsKey("c"))
            CROSS_OVER_RATE = Double.parseDouble(options.get("c"));

        IOManager ioManager = new IOManager(INPUT_FILE, OUTPUT_FILE);
        AbstractMap.SimpleEntry<List<Knapsack>, List<Item>> entry = ioManager.readFile();
        knapsacks = entry.getKey();
        items = entry.getValue();

        MultiDimensionalKnapsackSolver multiDimensionalKnapsackSolver =
                new MultiDimensionalKnapsackSolver(POPULATION_SIZE, NUM_OF_GENERATIONS, MUTATION_RATE, CROSS_OVER_RATE,
                        items, knapsacks);

        multiDimensionalKnapsackSolver.run();

        Individual bestSolution = multiDimensionalKnapsackSolver.getBestSolution();
        ioManager.generateOutput(bestSolution.getFitness(), bestSolution.getGenome());
    }

}
