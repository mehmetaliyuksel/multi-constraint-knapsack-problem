import java.util.ArrayList;

public class Item {

    private final ArrayList<Integer> constraints;
    private final int value;
    private final double averageProfit;


    public Item(int value) {
        this.constraints = new ArrayList<>();
        this.value = value;
        this.averageProfit=calculateAverageProfit();
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
    public double calculateAverageProfit(){
        double weightSum=0;
        for (Integer constraint : constraints) {
            weightSum += constraint;
        }
        return value/weightSum;
    }

}
