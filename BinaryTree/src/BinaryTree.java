import javax.management.ListenerNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    TreeNode root;

    public BinaryTree() {
    	System.out.println(); //random
        root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

  //Hw5.1
    public List<Integer> inorderIterative(boolean lookingForNthValue, int n) {
    	// homework
        // to be done iteratively
    	Stack<TreeNode> stack = new Stack<>();
    	ArrayList<Integer> result = new ArrayList<>();

    	//stack.push(root); //put root node in stack
    	//get the next node in stack but don't remove it
		TreeNode curr = root;
		//stack.push(curr);
		
		int counter = 1;
		
    	while (!stack.isEmpty() || curr != null) {
    		
    		//keep pushing the left of curr into stack
    		while (curr != null) {	
    			stack.push(curr); //push the next left node to the stack
    			curr = curr.left;
    		}
    		
    		//we are now at the left-most node 
    		curr = stack.pop();
    		
    		if (lookingForNthValue == true && counter == n) {
    			List<Integer> nthSmallestValue = new ArrayList<>();
    			nthSmallestValue.add(curr.val);
    			return nthSmallestValue;
    		
    		}
    		counter++;
    	
    		result.add(curr.val);
    		
    		curr = curr.right;
    		
    	}
   
        return result;   	
        
    }
    
    //Hw5.2
    private Integer LCA(TreeNode node, int a, int b) {
    	
    	//if the current node is null, there's no target found in this branch
    	if (node == null) {
    		return null;
    	}
    	
    	//target found, stop traversing this branch and get back to last call
    	if (node.val == a || node.val == b) {
    		return node.val;
    	} 
    		
    		//we keep branching towards to left until a target is found or null
    		Integer leftNum = LCA(node.left, a, b);
    		
    		//begin a branching sequence to the right until target found or null found
    		Integer rightNum = LCA(node.right, a, b);
    		
    		if (leftNum != null && rightNum != null) {
    			//targets found in left AND right. Current node is LCA!
    			return node.val;
    		} else if (leftNum != null) {
    			//a target is only found on left side. current node is not LCA so keep going back up
    			return leftNum;
    		} else if (rightNum != null) {
    			
    			return rightNum;
    		} else {
    			return null;
    		}
    		
    }
    
    //Hw5.2
    public int LCA(int v1, int v2) {
    	Integer lca = LCA(root, v1, v2);

    	if (lca == null) {
    		return -1;
    	} else {
    		return lca;
    	}
    	
        // homework
       
    }
    
    //Hw5.3
    public List<List<Integer>> levelOrderTraversal() {
        // homework
        // to be done iteratively

    	 //             1
        //            /  \
        //           2    3
        //          /  \    \
        //         4    5    6
        //        /    /
        //       7    8
        //      /
        //     9
    	
    	List<List<Integer>> resultList = new ArrayList<>();
    	Queue<TreeNode> currLevelNodes = new LinkedList();
    	TreeNode curr = root;
    	if (curr != null) {
    		currLevelNodes.add(curr);
    	}
    	
    	//keep going until there are absolutely no more nodes in the queue(when adding the last level's
    	//left and right values, there were no left or right values found)
    	while (!currLevelNodes.isEmpty()) {
    		
    		List<Integer> nodesAtLevel = new ArrayList<>();
    		
    		int sizeOfQueueAtCurrentLevel = currLevelNodes.size();
    		//Take the next set of treenodes from the queue and add them to a sublist
    		for (int i = 0; i < sizeOfQueueAtCurrentLevel; i++) {
    			
    			//take the current set's left and right nodes and add them to the queue
    			if (currLevelNodes.peek().left != null) {
    				currLevelNodes.add(currLevelNodes.peek().left);
    			}
    			if (currLevelNodes.peek().right != null) {
    				currLevelNodes.add(currLevelNodes.peek().right);
    			}
    			
    			nodesAtLevel.add(i, currLevelNodes.poll().val);
    			
    		}
    		
    		resultList.add(nodesAtLevel);
    		
    	}
    	
        return resultList; // place holder
    }
    
    //Hw5.4 - Extra credit
    public int nthSmallestInBST(int n) {
        // homework - extra credit
    	List<Integer> nums = this.inorderIterative(true, n);
    	return nums.get(0);
    }
    
    public void preorderRecursive(TreeNode node, List<Integer> result) {
        // exercise
    	if (node == null) {
    		return;
    	}	
    	result.add(node.val);
    	preorderRecursive(node.left, result);
    	preorderRecursive(node.right, result);	
    }
    
    public List<Integer> preorderRecursive() {
    	List<Integer> result = new ArrayList<>();
    	preorderRecursive(root, result);
    	
        // exercise
        return result;
    }
    
    public void inorderRecursive(TreeNode node, List<Integer> result) {
    	if (node == null) {
    		return;
    	}	
    	inorderRecursive(node.left, result);
    	result.add(node.val);
    	inorderRecursive(node.right, result);	
    }
    
    public List<Integer> inorderRecursive() {
    	List<Integer> result = new ArrayList<>();
    	inorderRecursive(root, result);
        // exercise
        return result;
    }
    public void postorderRecursive(TreeNode node, List<Integer> result) {
    	if (node == null) {
    		return;
    	}	
    	postorderRecursive(node.left, result);
    	postorderRecursive(node.right, result);	
    	result.add(node.val);
    }
    
    
    List<Integer> postorderRecursive() {
        // exercise
    	List<Integer> result = new ArrayList<>();
    	postorderRecursive(root, result);
        return result;
    }

    public List<Integer> preorderIterative() {
    	Stack<TreeNode> stack = new Stack<>();
    	List<Integer> result = new ArrayList<>();
    	if (root == null) {
    		return result;
    	}	
    	stack.add(root);	
    	while(!stack.isEmpty()) {  		
    		TreeNode node = stack.pop();
    		result.add(node.val);	
    		if (node.right != null) {
    			stack.push(node.right);
    		}
    		if (node.left != null) {
    			stack.push(node.left);
    		}
    	}
        // exercise
        return result;
    }

    private Integer LCA_BST(TreeNode node, int v1, int v2) {
    	
        if (node == null) {
            return null;
        }

        int min = Math.min(v1, v2);
        int max = Math.max(v1, v2);

        if (node.val >= min && node.val <= max) {
            return node.val;
        }

        if (node.val < min) {
            return LCA_BST(node.right, v1, v2);
        }

        return LCA_BST(node.left, v1, v2);
    }
    
    public int LCA_BST(int v1, int v2) {
        // exercise
        return LCA_BST(root, v1, v2);
    }
    
    private int height(TreeNode node) {
    	if (node == null) {
    		return 0;
    	}
    	
    	int leftHeight = height(node.left);
    	int rightHeight = height(node.right);
    	
    	return Math.max(leftHeight, rightHeight) + 1;
    	
    }
    
    public int height() {
        // exercise
        return height(root);
    }

   
}