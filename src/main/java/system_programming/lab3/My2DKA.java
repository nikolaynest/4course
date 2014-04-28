package system_programming.lab3;

import java.util.Set;

/**
 * Created by nikolay on 3/24/14.
 */
public class My2DKA extends DKA{
    private Rules rules;
    protected My2DKA(String tape, Character beginState, Set<Character> endStates) {
        super(tape, beginState, endStates);
        rules = new MyRules();
    }

    @Override
    void checkTape() {
        char[] charTape = tape.toCharArray();
        Character current;
        Character currentState = beginState;
        boolean isEndFlag = false;
        for (int i = 0; i < charTape.length; i++) {
            current = charTape[i];
            try {
                currentState = transitionFunction(currentState, current);
                if (currentState == null) {
                    System.out.println("Your tape of characters :\'" + tape + "\' is acceptable for this language!");
                    return;
                } else if (i == charTape.length-1) {
                    for(Character endCh: endStates){
                        if (current == endCh){
                            isEndFlag = true;
                        }
                    }
                    if(!isEndFlag){
                        System.out.println("Your tape of characters: \'" + tape + "\' is NOT acceptable for this language");
                        return;
                    }
                }
            } catch (NotValidSymbolException e) {
                System.out.println("word: " + tape+e.getMessage());
                break;
            }
        }
    }

    @Override
    protected Character transitionFunction(Character state, Character symbol) throws NotValidSymbolException {
        for(Production p: rules.getRules()){
            if (p.getBeginState().equals(state) && p.getValue().equals(symbol)){
                return p.getEndState();
            }
        }
        throw new NotValidSymbolException(" not valid for this language");
    }
}
