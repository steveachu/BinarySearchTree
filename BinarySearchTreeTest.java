import java.text.*;
import java.util.*;

public class BinarySearchTreeTest {
   public static void main(String[] args) {
      BinarySearchTree tree = new BinarySearchTree();
      int heightOfTree, listLength, temp;
      double efficiencyOfTree;
      Random generator = new Random();
      DecimalFormat oneDigit = new DecimalFormat("#.##");
      List treeArrayList = new ArrayList();
      
      tree.add(50);
      tree.add(40);
      tree.add(70);
      tree.add(30);
      tree.add(45);
      tree.add(60);
      tree.add(90);
      
      heightOfTree = tree.getHeight();
      efficiencyOfTree = tree.getEfficiency();
      System.out.println("Height of tree: " + heightOfTree);
      System.out.println("Efficiency of tree: " + oneDigit
       .format(efficiencyOfTree) + "%");
      tree.printInOrder();
      tree.clear();
      
      // degenerate tree
      tree.add(30);
      tree.add(40);
      tree.add(45);
      tree.add(50);
      tree.add(60);
      tree.add(70);
      tree.add(90);
      
      heightOfTree = tree.getHeight();
      efficiencyOfTree = tree.getEfficiency();
      System.out.println("Height of tree: " + heightOfTree);
      System.out.println("Efficiency of tree: " + oneDigit
       .format(efficiencyOfTree) + "%");
      tree.printInOrder();
      tree.clear();
      
      for (int i = 1; i <= 100; i++) {
         treeArrayList.addLast(i);
      }
      while (treeArrayList.getCount() != 0) {
         listLength = generator.nextInt(treeArrayList.getCount());
         temp = (int) treeArrayList.get(listLength);
         System.out.print(temp + ",");
         tree.add(temp);
         treeArrayList.removeAt(listLength);
      }
      System.out.println();
      heightOfTree = tree.getHeight();
      efficiencyOfTree = tree.getEfficiency();
      System.out.println("Height of tree: " + heightOfTree);
      System.out.println("Efficiency of tree: " + oneDigit
       .format(efficiencyOfTree) + "%");
   }
}

/*
run:
Height of tree: 2
Efficiency of tree: 100%
90  70  60  50  45  40  30  
Height of tree: 6
Efficiency of tree: 5.51%
90  70  60  50  45  40  30  
85,21,49,52,17,59,36,67,3,22,65,29,26,37,48,98,66,73,97,7,71,41,39,69,58,45,76,
25,46,50,96,40,53,62,38,13,64,27,61,86,60,20,33,28,18,6,57,4,77,90,63,54,88,44,
24,100,1,51,83,95,87,56,78,75,82,5,99,12,81,93,19,91,72,30,42,55,70,11,47,84,92,
23,74,2,89,80,32,79,68,16,15,31,43,9,94,35,8,10,34,14,
Height of tree: 14
Efficiency of tree: 0.31%
BUILD SUCCESSFUL (total time: 0 seconds)
 */