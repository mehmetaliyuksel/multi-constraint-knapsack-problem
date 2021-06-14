import java.util.ArrayList;

public class Item {

    private final ArrayList<Integer> constraints;
    private final int value;

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

    public int getValue() {
        return value;
    }

}
