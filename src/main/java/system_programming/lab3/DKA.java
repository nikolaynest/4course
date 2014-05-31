package system_programming.lab3;

import java.util.Set;

/**
 * Created by nikolay on 3/24/14.
 */
public abstract class DKA {

    protected String tape;
    protected Character beginState;
    protected Set<Character> endStates;

    protected DKA(String tape,Character beginState, Set<Character> endStates) {
        this.tape = tape;
        this.beginState = beginState;
        this.endStates = endStates;
    }

    abstract void checkTape();
    protected abstract Character transitionFunction(Character state, Character symbol) throws Exception;

}