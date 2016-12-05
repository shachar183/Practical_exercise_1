import java.util.Arrays;
import java.util.Random;

public class WAVLTree_Tester {
	
	public static void main(String[] args) {
		WAVLTree tree = new WAVLTree();
		Random random = new Random();
		int i = 0;
		int i1,i2;
		while(i<100)
		{
			i++;
			i1=tree.insert(i2=random.nextInt(200000000), "lol");
			if(!tree.TESTisThisTreeOk())
			{
				System.out.println("l12o21l");
				break;
			}
		}	
		System.out.println("l1o1l " + tree.size());
		
		
		// creating a tree and testing it for min, max, search.
		WAVLTree test_tree = example_tree_creator();
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
	
}
