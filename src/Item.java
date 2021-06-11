import java.util.ArrayList;

public class Item {

    private ArrayList<Integer> constraints;
    private int value;

    public Item(ArrayList<Integer> constraints, int value) {
        this.constraints = constraints;
        this.value = value;
    }
    public Item(int value) {
        this.constraints = new ArrayList<>();
        this.value = value;
    }

    public ArrayList<Integer> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<Integer> constraints) {
        this.constraints = constraints;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
