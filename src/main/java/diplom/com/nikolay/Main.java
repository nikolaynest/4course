package diplom.com.nikolay;

import java.util.ArrayList;

public class Main {

    public void testArrayList(){
        ArrayList<ArrayList>lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        lists.add(list);
        list = new ArrayList<>();
        list.add(4);list.add(5); list.add(6);
        lists.add(list);

        lists.get(0).add(new Integer(10));

        for (ArrayList<Integer> lis: lists){
            System.out.println("another list:");
            for(Integer i: lis){
                System.out.println(i.toString());
            }
        }
    }


    public static void main(String... args) {

        RiskHelper myHelper = new RiskHelper();
        myHelper.addNode(myHelper.treeNode,"A", 0.2, true);
        myHelper.inOrderTreeWalk(myHelper.treeNode);
        System.out.println();
        System.out.println("after add B node:");
        myHelper.addNode(myHelper.treeNode, "B", 0.4, true);
        myHelper.inOrderTreeWalk(myHelper.treeNode);
        System.out.println();
        System.out.println("after add C node");
        myHelper.addNode(myHelper.treeNode, "С", 0.1, true);
        myHelper.inOrderTreeWalk(myHelper.treeNode);
    }
}
