package system_programming.lab5;

/**
 * Created by nikolay on 04.06.14.
 */
public class Rule {
    private String left;
    private String right;

    public Rule(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
