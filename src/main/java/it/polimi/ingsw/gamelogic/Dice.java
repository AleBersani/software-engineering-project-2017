
public class Dice {
    private String color;
    private int value;

    public Dice() {
        color = "";
        value = 0;
    }

    public Dice(String color, int value) {
        this.color = color;
        this.setValue(value);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
