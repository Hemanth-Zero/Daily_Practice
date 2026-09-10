/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static int count =0;
    public int[] counter(TreeNode root){
        int sum=root.val;
        int n = 1;
        if(root.left != null) {
           int[] left = counter(root.left);
           sum+= left[0]; 
            n+=left[1];
        }
        if(root.right != null) {
           int[] right = counter(root.right);
           sum+= right[0]; 
            n+=right[1];
        }
        if(sum/n == root.val) count++;
        return new int[]{sum,n};
    }
    public int averageOfSubtree(TreeNode root) {
        count =0;
        int[] sum =counter(root);
        return count;
    }
}