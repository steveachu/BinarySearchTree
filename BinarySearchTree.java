// Implement a Binary Search Tree, where each vertex contains a Key and a Value.
// Since BSTs use ordering to determine left/right child relationships,
// any key in a BST must be Comparable. So all methods will take and
// return Comparables for keys and Objects vor values.
public class BinarySearchTree {
   private class Vertex {      
      private Comparable mKey;
      private Vertex mLeft;
      private Vertex mRight;
      private Vertex mParent;

      public Vertex(Comparable key) {
         mKey = key;
      }
   }
   private Vertex mRoot;
   private int mCount;
   
   public void add(Comparable key) {
      // if the BST is empty, there will be no root node.
      if (mCount == 0) {
         mRoot = new Vertex(key);
      }
      else {
         // find the appropriate place in tree to place the item.
         // start at the root. if item is less than root, go left, otherwise
         // go right. continue this process until there is no child at the left
         // or right; insert the item in a new node at that position.
         Vertex v = mRoot;
         Vertex toInsert = new Vertex(key);
         while (true) {
            int compValue = key.compareTo(v.mKey);
            if (compValue > 0) { // item is less than current vertex
               if (v.mLeft == null) { // no child to left of v; insert there.
                  v.mLeft = toInsert;
                  toInsert.mParent = v;
                  break;
               }
               else { // resume search at v's left
                  v = v.mLeft;
               }
            }
            else { // item is greater than current vertex
               if (v.mRight == null) { // no child to right of v; insert there.
                  v.mRight = toInsert;
                  toInsert.mParent = v;
                  break;
               }
               else { // resume search at v's right.
                  v = v.mRight;
               }
            }
         }
      }
      mCount++;
   }

   public boolean contains(Comparable key) {
      // find the Vertex containing the item
      Vertex find = findKey(key);
      return find != null;
   }

   private Vertex findMinimum(Vertex v) {
      if (v == null) {
         return null;
      }
      if (v.mLeft == null) {
         return v;
      }
      return findMinimum(v.mLeft);
   }

   private Vertex findKey(Comparable key) {
      Vertex v = mRoot;
      while (v != null) {
         int compValue = key.compareTo(v.mKey);
         if (compValue == 0) {
            return v;
         }
         if (compValue < 0) {
            v = v.mLeft;
         }
         else {
            v = v.mRight;
         }
      }
      return null;
   }

   // A straightforward, no-frills method for removing a key from the tree.
   // There are many shorter ways of doing this, especially with recursion,
   // but we will stick with something simple.
   public boolean remove(Comparable key) {
      Vertex toRemove = findKey(key);
      if (toRemove == null) {
         return false;
      }

      // case 1: no children. remove this node by unlinking the parent.
      if (toRemove.mLeft == null && toRemove.mRight == null) {
         if (toRemove == mRoot) // only one vertex in tree
         {
            mRoot = null;
         }
         else {
            if (toRemove.mParent.mLeft == toRemove) {
               toRemove.mParent.mLeft = null;
            }
            else {
               toRemove.mParent.mRight = null;
            }
         }
      }
      else if (toRemove.mLeft == null) {
         // this means v.mRight is not null, and v has one child.
         // replace v with its mRight.
         if (toRemove.mParent.mLeft == toRemove) {
            toRemove.mParent.mLeft = toRemove.mRight;
         }
         else {
            toRemove.mParent.mRight = toRemove.mRight;
         }
      }
      else if (toRemove.mRight == null) {
         // this means v.mLeft is not null, and v has one child.
         // replace v with its mLeft
         if (toRemove.mParent.mLeft == toRemove) {
            toRemove.mParent.mLeft = toRemove.mLeft;
         }
         else {
            toRemove.mParent.mRight = toRemove.mLeft;
         }
      }
      else {
         // v has two children. find the smallest element in v's right
         // subtree. replace v with that item.
         Vertex smallest = findMinimum(toRemove.mRight);
         toRemove.mKey = smallest.mKey;

         // observe: smallest has no left child. why?
         if (smallest.mParent == toRemove) {
            // smallest item is a direct child of toRemove. must be on
            // toRemove's right, so replace toRemove's right with smallest's
            // right.
            toRemove.mRight = smallest.mRight;
         }
         else {
            // smallest item is a grandchild or later of toRemove.
            // smallest's parent needs to be rerouted so its left goes to
            // smallest's old right.
            smallest.mParent.mLeft = smallest.mRight;
         }
      }
      return true;
   }

   public int getHeight() {
      return getHeight(mRoot);
   }

   public double getEfficiency() {
      int height = getHeight();
      double totalVertices = Math.pow(2, height + 1) - 1;      
      return mCount / totalVertices * 100;
   }

   public void printInOrder() {
      printInOrder(mRoot);
      System.out.println();
   }

   public void clear() {
      mRoot = null;
      mCount = 0;
   }

   private int getHeight(Vertex v) {
      if (v.mLeft == null && v.mRight == null) {
         return 0;
      }
      else if (v.mLeft == null) {
         return 1 + getHeight(v.mRight);
      }
      else if (v.mRight == null) {
         return 1 + getHeight(v.mLeft);
      }
      else {
         return Math.max(getHeight(v.mRight), getHeight(v.mLeft)) + 1;
      }

   }

   private void printInOrder(Vertex v) {
      if (v != null) {
         printInOrder(v.mLeft);
         System.out.print(v.mKey + "  ");
         printInOrder(v.mRight);
      }
   }
}