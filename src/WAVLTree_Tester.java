import java.util.Arrays;
import java.util.Random;
//package com.quicklyjava;

import java.io.File;
import java.io.IOException;
import java.util.Date;
 
import jxl.*;
import jxl.write.*;
import jxl.write.Boolean;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

public class WAVLTree_Tester {
	
	public static void main(String[] args) throws WriteException, IOException {
		//create a random tree and test if there are any error while inserting the items.
		WAVLTree test_tree = new WAVLTree();
		writeExcel();
//		for(int i= 0; i<1000000; i++){
//			example_random_tree_insertor(test_tree,10,500);
//			example_random_tree_deletor(test_tree, 100, 500);
//		}
//		
//		System.out.println("__________________________________________________________________");
//		System.out.println("");
//
//		// creating a tree and testing it for min, max, search, keysToArray, infoToArray, size.
//		test_tree = example_tree_creator();
//		if(!test_tree.min().equals("1")){
//			System.out.println("error 1: in min function");
//		}
//		if(!test_tree.max().equals("9")){
//			System.out.println("error 2: in max function");
//		}
//		if(!test_tree.search(3).equals("3")){
//			System.out.println("error 3: in search function");
//		}
//		if(!(test_tree.search(4) == null)){
//			System.out.println("error 4: in search function");
//		}
//		int [] sorted_keys_array = {1,2,3,5,6,7,9};
//		if(!Arrays.equals(test_tree.keysToArray(),sorted_keys_array)){
//			System.out.println("error 5: in keys to array function");
//		}
//		String [] sorted_info_array = {"1","2","3","5","6","7","9"};
//		if(!Arrays.equals(test_tree.infoToArray(),sorted_info_array)){
//			System.out.println("error 6: in info to array function");
//		}
//		WAVLTree empty_tree = new WAVLTree();
//		if(empty_tree.empty() != true){
//			System.out.println("error 7: in empty function");
//		}
//		if(test_tree.empty() == true){
//			System.out.println("error 8: in empty function");
//		}
//		if(empty_tree.size() != 0){
//			System.out.println("error 9: in size function");
//		}
//		if(test_tree.size() != 7){
//			System.out.println("error 10: in size function");
//		}
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
	

	 
	private static void writeExcel() throws WriteException, IOException {
		String filePath = "C:\\Users\\Shachar\\workspace\\Practical_exercise_1\\src\\output.xls";
		WritableWorkbook workBook = null;
		try {
			//initialize workbook
			workBook = Workbook.createWorkbook(new File(filePath));
			//create sheet with name as Employee and index 0
			WritableSheet sheet = workBook.createSheet("WAVLTree measures", 0);
			// create font style for header cells
			WritableFont headerCellFont = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD, true);
			//create format for header cells
			WritableCellFormat headerCellFormat = new WritableCellFormat(
					headerCellFont);
			// create header cells
			Label headerCell1 = new Label(0, 0, "ID", headerCellFormat);
			Label headerCell2 = new Label(1, 0, "Number of actions", headerCellFormat);
			Label headerCell3 = new Label(2, 0, "Average number of actions for insert action", headerCellFormat);
			Label headerCell4 = new Label(3, 0, "Average number of actions for delete action", headerCellFormat);
			Label headerCell5 = new Label(4, 0, "Maximum number of actions for insert action", headerCellFormat);
			Label headerCell6 = new Label(5, 0, "Maximum number of actions for delete action", headerCellFormat);
			// add header cells to sheet
			sheet.addCell(headerCell1);
			sheet.addCell(headerCell2);
			sheet.addCell(headerCell3);
			sheet.addCell(headerCell4);
			sheet.addCell(headerCell5);
			sheet.addCell(headerCell6);
			
			for(int i=1; i<11;i++){
				WAVLTree test_tree = new WAVLTree();
				// insertion part
				Object[] tree_insert_avg_max = random_tree_insertor(test_tree, 10000*i, 10000^100000000);
				test_tree = (WAVLTree) tree_insert_avg_max[0];
				int average_insert_rebalancing_actions = (int) tree_insert_avg_max[1];
				int max_insert_rebalancing_actions = (int) tree_insert_avg_max[2];
				// deletion part
				Object[] tree_delete_avg_max = random_tree_deletor(test_tree);
				test_tree = (WAVLTree) tree_delete_avg_max[0];
				int average_delete_rebalancing_actions = (int) tree_delete_avg_max[1];
				int max_delete_rebalancing_actions = (int) tree_delete_avg_max[2];
				Label id = new Label(0, i, String.format("%s",i));
				Label number_of_actions = new Label(1, 1,String.format("%s", i*10000));
				Label xl_average_insert_rebalancing_actions = new Label(2, i,String.format("%s",average_insert_rebalancing_actions));
				Label xl_average_delete_rebalancing_actions = new Label(3, i,String.format("%s",average_delete_rebalancing_actions));
				Label xl_max_insert_rebalancing_actions = new Label(4, i, String.format("%s",max_insert_rebalancing_actions));
				Label xl_max_delete_rebalancing_actions = new Label(5, i, String.format("%s",max_delete_rebalancing_actions));
				sheet.addCell(id);
				sheet.addCell(number_of_actions);
				sheet.addCell(xl_average_insert_rebalancing_actions);
				sheet.addCell(xl_average_delete_rebalancing_actions);
				sheet.addCell(xl_max_insert_rebalancing_actions);
				sheet.addCell(xl_max_delete_rebalancing_actions);
			}
			//write workbook
			workBook.write();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} finally {
			//close workbook
			workBook.close();
		}
	}
	public static Object[] random_tree_insertor(WAVLTree tree,int randomInsertionNumber, int randomMaxInteger){
		Random random = new Random();
		int lastKey;
		int lastTreeSize =tree.size();
		int average_rebalancing_actions = 0;
		int max_rebalancing_actions = 0;
		int currect_rebalancing_actions = 0;
		for(int i = 0; i < randomInsertionNumber; i++){
			currect_rebalancing_actions = tree.insert(lastKey = random.nextInt(randomMaxInteger), "info");
			if(!tree.testTreeRanks()){
				System.out.println("Error in rebalancing after inserting item key " + lastKey);
//				return tree;
			}
			if (currect_rebalancing_actions > max_rebalancing_actions){
				max_rebalancing_actions = currect_rebalancing_actions;
			}
			average_rebalancing_actions += currect_rebalancing_actions;
		}
		average_rebalancing_actions = average_rebalancing_actions/randomInsertionNumber;
//		
		System.out.println("Finished inserting 	" + (tree.size()-lastTreeSize) + "	 random items to tree without any errors.");
		Object [] a = {tree, average_rebalancing_actions, max_rebalancing_actions};
		return a;
	}
	
	public static Object[] random_tree_deletor(WAVLTree tree){
		Random random = new Random();
		int lastKey;
		int lastTreeSize =tree.size();
//		printBinaryTree(tree.WAVL_root, 0,tree.WAVL_emptyNode);
		int average_rebalancing_actions = 0;
		int max_rebalancing_actions = 0;
		int currect_rebalancing_actions = 0;
		while(tree.size() > 0){
			int smallest = tree.getSmallestNode().key;
//			int smallest_key = Integer.parseInt(smallest);
			currect_rebalancing_actions = tree.delete(smallest);
			if(!tree.testTreeRanks()){
				System.out.println("Error in rebalancing after deleting item key ");
//				return tree;
			}
			if (currect_rebalancing_actions > max_rebalancing_actions){
				max_rebalancing_actions = currect_rebalancing_actions;
			}
			average_rebalancing_actions += currect_rebalancing_actions;
		}
		average_rebalancing_actions = average_rebalancing_actions/lastTreeSize;
		Object [] a = {tree, average_rebalancing_actions, max_rebalancing_actions};
		return a;
//		System.out.println("Finished deleting	" + (lastTreeSize-tree.size()) + "	 random items to tree without any errors.");
	}
	
}