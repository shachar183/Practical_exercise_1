import java.util.Arrays;
import java.util.Random;


public class WAVLTree_Tester {
	
	public static void main(String[] args) {
		//create a random tree and test if there are any error while inserting the items.
		WAVLTree test_tree = new WAVLTree();;
		for(int i= 0; i<1000000; i++){
			example_random_tree_insertor(test_tree,10,500);
			example_random_tree_deletor(test_tree, 100, 500);
		}
		
		System.out.println("__________________________________________________________________");
		System.out.println("");

		// creating a tree and testing it for min, max, search.
		test_tree = example_tree_creator();
		if(!test_tree.min().equals("1")){
			System.out.println("error 1: in min function");
		}
		if(!test_tree.max().equals("9")){
			System.out.println("error 2: in max function");
		}
		if(!test_tree.search(3).equals("3")){
			System.out.println("error 3: in search function");
		}
		if(!(test_tree.search(4) == null)){
			System.out.println("error 4: in search function");
		}
		int [] sorted_keys_array = {1,2,3,5,6,7,9};
		if(!Arrays.equals(test_tree.keysToArray(),sorted_keys_array)){
			System.out.println("error 5: in keys to array function");
		}
		String [] sorted_info_array = {"1","2","3","5","6","7","9"};
		if(!Arrays.equals(test_tree.infoToArray(),sorted_info_array)){
			System.out.println("error 6: in info to array function");
		}
		WAVLTree empty_tree = new WAVLTree();
		if(empty_tree.empty() != true){
			System.out.println("error 7: in empty function");
		}
		if(test_tree.empty() == true){
			System.out.println("error 8: in empty function");
		}
		if(empty_tree.size() != 0){
			System.out.println("error 9: in size function");
		}
		if(test_tree.size() != 7){
			System.out.println("error 10: in size function");
		}
		System.out.println("Done!");
	}
	
	public static WAVLTree example_tree_creator(){
		WAVLTree tree = new WAVLTree();
		tree.insert(5, "5");
		tree.insert(3, "3");
		tree.insert(6, "6");
		tree.insert(7, "7");
		tree.insert(1, "1");
		tree.insert(9, "9");
		tree.insert(2, "2");
		return tree;
	}
	
	public static WAVLTree example_random_tree_insertor(WAVLTree tree,int randomInsertionNumber, int randomMaxInteger){
		Random random = new Random();
		int lastKey;
		int lastTreeSize =tree.size();
		for(int i = 0; i < randomInsertionNumber; i++){
			tree.insert(lastKey = random.nextInt(randomMaxInteger), "info");
			if(!tree.testTreeRanks()){
				System.out.println("Error in rebalancing after inserting item key " + lastKey);
				return tree;
			}
		}
//		System.out.println("Finished inserting 	" + (tree.size()-lastTreeSize) + "	 random items to tree without any errors.");
		return tree;
	}
	
	public static WAVLTree example_random_tree_deletor(WAVLTree tree,int randomDeletionNumber, int randomMaxInteger){
		Random random = new Random();
		int lastKey;
		int lastTreeSize =tree.size();
//		printBinaryTree(tree.WAVL_root, 0,tree.WAVL_emptyNode);

		for(int i = 0; i < randomDeletionNumber; i++){
			if(tree.delete(lastKey = random.nextInt(randomMaxInteger))!=-1){
//				System.out.println("__________________________________________________________________");
//				System.out.println("");
//				System.out.println("deleting item " + lastKey);
//				System.out.println("__________________________________________________________________");
//				System.out.println("");
//				printBinaryTree(tree.WAVL_root, 0,tree.WAVL_emptyNode);
				if(!tree.testTreeRanks()){
					System.out.println("Error in rebalancing after deleting item key " + lastKey);
					return tree;
				}
			}
		}
//		System.out.println("Finished deleting	" + (lastTreeSize-tree.size()) + "	 random items to tree without any errors.");
		return tree;
	}
	public static void printBinaryTree(WAVLTree.WAVLNode root, int level, WAVLTree.WAVLNode empty){
	    if(root==null || root == empty)
	         return;
	    printBinaryTree(root.rightNode, level+1,empty);
	    if(level!=0){
	        for(int i=0;i<level-1;i++)
	            System.out.print("|\t");
	            System.out.println("|---" + root.rankDiff + "---"+root.key);
	    }
	    else
	        System.out.println(root.key);
	    printBinaryTree(root.leftNode, level+1,empty);
	} 	
}