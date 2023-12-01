package DataStructure.E_Trees.A_BinarySearchTree_Insertion;

public class Tree {

    private TreeNode root;

    public void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        }
        else {
            root.insert(value);
        }
    }


}
