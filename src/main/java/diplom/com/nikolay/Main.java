package diplom.com.nikolay;

import java.util.ArrayList;
import java.util.List;

public class Main {

//    public void testArrayList(){
//        ArrayList<ArrayList>lists = new ArrayList<>();
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        lists.add(list);
//        list = new ArrayList<>();
//        list.add(4);list.add(5); list.add(6);
//        lists.add(list);
//
//        lists.get(0).add(new Integer(10));
//
//        for (ArrayList<Integer> list1: lists){
//            System.out.println("another list:");
//            for(Integer i: list1){
//                System.out.println(i.toString());
//            }
//        }
//    }


    public static void main(String... args) {

        RiskHelper myHelper = RiskHelper.getInstance();

        myHelper.addNode(myHelper.getTreeNode(),"A", 0.2, true);
        myHelper.inOrderTreeWalk(myHelper.getTreeNode());
        System.out.println();

        System.out.println("after add B node:");
        myHelper.addNode(myHelper.getTreeNode(), "B", 0.4, true);
        myHelper.inOrderTreeWalk(myHelper.getTreeNode());
        System.out.println();

        System.out.println("after add C node");
        myHelper.addNode(myHelper.getTreeNode(), "С", 0.1, true);
        myHelper.inOrderTreeWalk(myHelper.getTreeNode());

//        System.out.println();
//        System.out.println("after add D node");
//        myHelper.addNode(myHelper.treeNode, "D", 0.3, true);
//        myHelper.inOrderTreeWalk(myHelper.treeNode);


        System.out.println("\n leafs:");
        myHelper.getLeafNodes(myHelper.getTreeNode());
        ArrayList<Risk> leafs = (ArrayList<Risk>) myHelper.getLeafNodes();
        for (Risk r: leafs){
            System.out.println(r.toString());
        }

        System.out.println("\n scenarios:");
//        for (List<Risk> list:myHelper.getScenarios()){
//            System.out.println("scenario:");
//            for (Risk r:list){
//                myHelper.printRisk(r);
//            }
//        }
        ArrayList<StringBuilder> sb = myHelper.analizeScenarios(myHelper.getScenarios());
        for (StringBuilder s: sb){
            System.out.println(s);
        }
    }
}
