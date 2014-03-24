package system_programming.lab3;

/**
 * Created by nikolay on 3/24/14.
 */
public class Production {

    private Character beginState;
    private Character value;
    private Character endState;

    public Production(Character beginState, Character value, Character endState) {
        this.beginState = Character.toUpperCase(beginState);
        this.value = Character.toLowerCase(value);
        this.endState = Character.toUpperCase(endState);
    }

    public Character getBeginState() {
        return beginState;
    }

    public Character getValue() {
        return value;
    }

    public Character getEndState() {
        return endState;
    }



}
