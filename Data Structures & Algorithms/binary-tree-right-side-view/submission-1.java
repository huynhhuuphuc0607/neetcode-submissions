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
    // BFS + Queue
    // time: O(N)
    // space: O(D): the maximum number of nodes per level
    // public List<Integer> rightSideView(TreeNode root) {
    //     if(root == null)
    //         return new ArrayList<>();

    //     List<Integer> ls = new ArrayList<>();

    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.offer(root);
    //     while(!q.isEmpty()) {
    //         int numNodes = q.size();
    //         while(numNodes > 0) {
    //             TreeNode currentNode = q.poll();
    //             if(numNodes == 1) {
    //                 ls.add(currentNode.val);
    //             }

    //             if(currentNode.left != null)
    //                 q.offer(currentNode.left);
    //             if(currentNode.right != null)
    //                 q.offer(currentNode.right);
                
    //             numNodes--;
    //         }
    //     }

    //     return ls;
    // }

    // DFS (most optimized)
    // time: O(N)
    // space: O(H): recursion stack
    List<Integer> answer;
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return new ArrayList<>();

        answer = new ArrayList<>();
        helper(root, 0);
        return answer;
    }

    public void helper(TreeNode root, int level) {
        if(root == null)
            return;

        if(answer.size() == level)
            answer.add(root.val);

        helper(root.right, level + 1);
        helper(root.left, level + 1);
    }
}