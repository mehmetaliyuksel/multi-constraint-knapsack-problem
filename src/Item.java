import java.util.ArrayList;

public class Item implements Comparable<Item> {

    private final ArrayList<Integer> constraints;
    private final int value;
    private double averageProfit;


    public Item(int value) {
        this.constraints = new ArrayList<>();
        this.value = value;
    }

    public ArrayList<Integer> getConstraints() {
        return constraints;
    }

    public int getValue() {
        return value;
    }

    public double getAverageProfit() {
        return averageProfit;
    }

    public void calculateAverageProfit() {
        double weightSum = 0;
        for (Integer constraint : constraints) {
            weightSum += constraint;
        }
        this.averageProfit = value / weightSum;
    }

    @Override
    public int compareTo(Item o) {
        return Double.compare(this.averageProfit, o.getAverageProfit());
    }
}
