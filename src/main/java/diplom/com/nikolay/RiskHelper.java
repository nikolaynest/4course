package diplom.com.nikolay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nikolay on 27.04.14.
 */
public class RiskHelper {

    private static final RiskHelper instance = new RiskHelper();
    private final Risk treeNode;
    private final String ROOT_NAME = "Root";
    private List<Risk> leafNodes = new ArrayList<>();
    private RiskHelper(){
        treeNode = new Risk(ROOT_NAME, 1.0, true);
    }

    public static RiskHelper getInstance() {
        return instance;
    }

    public Risk getTreeNode() {
        return treeNode;
    }

    public List<Risk> getLeafNodes() {
        return leafNodes;
    }

    public void resetLeafNodes() {
        this.leafNodes = new ArrayList<>();
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
            printRisk(node);
            inOrderTreeWalk(node.down);
        }
    }

    public void seekLeafNodesAndAdd(Risk node){

        if (node.up!=null){
            seekLeafNodesAndAdd(node.up);
        } else {
            leafNodes.add(node);
        }
        if (node.down!=null){
            seekLeafNodesAndAdd(node.down);
        } else{
            if (!leafNodes.contains(node)) {
                leafNodes.add(node);
            }
        }

    }

    public void printRisk(Risk node) {
        System.out.println(node.toString());
    }

    public ArrayList<Scenario> getScenarios(){
        ArrayList<Scenario> lists = new ArrayList<>();
        for (Risk r:leafNodes){
            Scenario scenario = new Scenario();
            while (r.parent!=null){
                scenario.getList().add(r);
                r = r.parent;
            }
            lists.add(scenario);
        }
        return lists;
    }





    public static double round(double value, int digits){
        double dig = Math.pow(10,digits);
        return Math.round(value*dig)/dig;
    }
}
