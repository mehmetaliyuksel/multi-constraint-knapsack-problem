import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOManager {

    private final String fileName;

    public IOManager(String fileName) {
        this.fileName = fileName;
    }

    public AbstractMap.SimpleEntry<List<Knapsack>, List<Item>> readFile() {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);

            int numOfKnapsacks = sc.nextInt();
            int numOfItems = sc.nextInt();

            List<Item> items = new ArrayList<>();
            List<Knapsack> knapsacks = new ArrayList<>();

            for (int i = 0; i < numOfItems; i++) {
                items.add(new Item(sc.nextInt()));
            }

            for (int i = 0; i < numOfKnapsacks; i++) {
                knapsacks.add(new Knapsack(sc.nextInt()));
            }

            for (int i = 0; i < numOfKnapsacks; i++) {
                for (int j = 0; j < numOfItems; j++) {
                    items.get(j).getConstraints().add(sc.nextInt());
                }
            }
            sc.close();
            return new AbstractMap.SimpleEntry<>(knapsacks, items);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    public void generateOutput(int value, String genome) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            System.out.println("Writing to output.txt");
            myWriter.write("" + value);
            for (int i = 0; i < genome.length(); i++) {
                myWriter.write(genome.charAt(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while generating the output file.");
            e.printStackTrace();
        }
    }
}
