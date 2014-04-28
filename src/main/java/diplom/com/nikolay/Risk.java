package diplom.com.nikolay;

/**
 * Created by nikolay on 27.04.14.
 */
public class Risk {

    String name;
    double probability;
    //приемлемость фактора(да, нет)
    private boolean acceptable;
    Risk up;
    Risk down;
    Risk parent;

    public Risk(String name, double probability, boolean acceptable) {
        this.name = name;
        this.probability = probability;
        this.acceptable = acceptable;
    }


    @Override
    public String toString() {
        return "Risk{" +
                "name='" + name + '\'' +
                ", probability=" + probability +
                ", acceptable=" + acceptable +
                '}';
    }
}
