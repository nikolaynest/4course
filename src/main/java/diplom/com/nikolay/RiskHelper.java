package diplom.com.nikolay;

/**
 * Created by nikolay on 27.04.14.
 */
public class RiskHelper {
    private final String ROOT_NAME = "Root";
    public final Risk treeNode;

    public RiskHelper() {
        treeNode = new Risk(ROOT_NAME, 1.0, true);
    }

    public void addNode(Risk node, String name, double probability, boolean accept){

        if (node.up!=null){
            addNode(node.up, name, probability, accept);
        } else{
            node.up = new Risk(name, probability, accept);
            node.up.parent = node;
        }
        if (node.down!=null){
            addNode(node.down, name, probability, accept);
        } else {
            node.down = new Risk(name, 1.0 - probability, !accept);
            node.down.parent = node;
        }
    }

    public void inOrderTreeWalk(Risk node){
        if (node != null){
            inOrderTreeWalk(node.up);
            treatment(node);
            inOrderTreeWalk(node.down);
        }
    }

    private void treatment(Risk node) {
        System.out.println(node.toString());
    }
}
