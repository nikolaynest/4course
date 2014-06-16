package diplom.com.nikolay;

import java.util.ArrayList;
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

    public void getLeafNodes(Risk node){

        if (node.up!=null){
            getLeafNodes(node.up);
        } else {
            leafNodes.add(node);
        }
        if (node.down!=null){
            getLeafNodes(node.down);
        } else{
            if (!leafNodes.contains(node)) {
                leafNodes.add(node);
            }
        }

    }

    public void printRisk(Risk node) {
        System.out.println(node.toString());
    }

    public ArrayList<List<Risk>> getScenarios(){
        ArrayList<List<Risk>> lists = new ArrayList<>();
        for (Risk r:leafNodes){
            List<Risk> scenario = new ArrayList<>();
            while (r.parent!=null){
                scenario.add(r);
                r = r.parent;
            }
            lists.add(scenario);
        }
        return lists;
    }

    public ArrayList<StringBuilder> analizeScenarios(ArrayList<List<Risk>> lists){
        double result;
        StringBuilder sb;
        ArrayList<StringBuilder> sbList = new ArrayList<>();

        for (List<Risk> list: lists){
            result = 1.0;
            sb = new StringBuilder("");
            for (Risk risk: list){
                result *= risk.probability;
                sb.append("{Name="+risk.name);
                sb.append(" ,acceptable="+risk.isAcceptable()+"} ");
            }

            sb.append("value="+round(result, 3));
            sbList.add(sb);
        }
        return sbList;
    }

    private double round(double value, int digits){
        double dig = Math.pow(10,digits);
        return Math.round(value*dig)/dig;
    }
}
