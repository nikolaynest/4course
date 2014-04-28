package system_programming.lab3;

import java.util.Set;

/**
 * Created by nikolay on 3/24/14.
 */
public class MyDKA extends DKA {


    protected MyDKA(String tape, Character beginState, Set<Character> endStates) {
        super(tape, beginState, endStates);
    }

    @Override
    public void checkTape() {
        char[] charTape = tape.toCharArray();
        Character current;
        Character currentState = beginState;
        boolean isEndFlag=false;
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
                System.out.println("word: " + tape);
                System.out.println("Sorry, " + e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("Sorry, " + e.getMessage());

            }
        }
    }


    @Override
    protected Character transitionFunction(Character state, Character symbol) throws Exception {
        switch (state) {
            case 'S':
                switch (symbol) {
                    case 'с':
                        return 'B';
                    case 'т':
                        return 'B';
                    default:
                        throw new NotValidSymbolException("symbol >" + symbol + "< is not valid for state >" + state + "<");
                }
            case 'A':
                switch (symbol) {
                    case 'а':
                        return 'C';
                    case 'г':
                        return 'C';
                    default:
                        throw new NotValidSymbolException("symbol >" + symbol + "< is not valid for state >" + state + "<");
                }
            case 'B':
                switch (symbol) {
                    case 'а':
                        return 'D';
                    case 'у':
                        return 'C';
                    default:
                        throw new NotValidSymbolException("symbol >" + symbol + "< is not valid for state >" + state + "<");
                }
            case 'C':
                switch (symbol) {
                    case '<':
                        return null;
                    case 'і':
                        return 'C';
                    default:
                        throw new NotValidSymbolException("symbol >" + symbol + "< is not valid for state >" + state + "<");
                }
            case 'D':
                switch (symbol) {
                    case 'ш':
                        return 'A';
                    case 'и':
                        return 'C';
                    default:
                        throw new NotValidSymbolException("symbol >" + symbol + "< is not valid for state >" + state + "<");
                }
            default:
                throw new Exception("State >" + state + "< is not valid");
        }

    }
}
