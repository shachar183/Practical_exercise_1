/**
 *
 * WAVLTree
 *
 * An implementation of a WAVL Tree with
 * distinct integer keys and info
 *
 */

public class WAVLTree {
	final WAVLNode WAVL_emptyNode = new WAVLNode();
	private WAVLNode WAVL_root = WAVL_emptyNode;
	private int size = 0;
  /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
  public boolean empty() {
    return !(WAVL_root==WAVL_emptyNode); 
  }

 /**
   * public String search(int k)
   *
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   */
  public String search(int k)
  {
	  WAVLNode WAVL_tempNode = searchNode(k);
	  if(WAVL_tempNode!=null)
	  {
		  return WAVL_tempNode.info;
	  }else{
		  return null;
	  }
  }

  private  WAVLNode searchNode(int k)
  {
	  if(empty()){
		  return null;
	  }
	  
	  WAVLNode WAVL_tempNode = searchInsertionNode(k);
	  if (WAVL_tempNode.key==k)
	  {
		  return WAVL_tempNode;
	  }else{
		  return null;
	  }
  }

  
  private WAVLNode searchInsertionNode(int k)
  {
	  if(empty()){
		  return null;
	  }else{
		  WAVLNode WAVL_tempNode = WAVL_root;
		  while (WAVL_tempNode != WAVL_emptyNode)
		  {
			  if(WAVL_tempNode.key>k)
			  {
				  WAVL_tempNode=WAVL_tempNode.rightNode;
			  }else if(WAVL_tempNode.key<k){
				  WAVL_tempNode=WAVL_tempNode.leftNode;
			  }else{
				  break;
			  }
		  }
		  return WAVL_tempNode;
	  }
	  
  }
  
  /**
   * public int insert(int k, String i)
   *
   * inserts an item with key k and info i to the WAVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   */
   public int insert(int k, String i) {
	  return 42;	// to be replaced by student code
   }

  /**
   * public int delete(int k)
   *
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * returns -1 if an item with key k was not found in the tree.
   */
   public int delete(int k)
   {
	   return 42;	// to be replaced by student code
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty
    */
   public String min(WAVLNode root)
   {
	   if (empty()){
		   return null;
	   }
	   
	   return getSmallestNode().info;
   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
    */
   public String max()
   {
	   WAVLNode WAVL_tempNode = WAVL_root;
	   if (empty()){
		   return null;
	   }
	   while(WAVL_tempNode != WAVL_emptyNode){
		   WAVL_tempNode = WAVL_tempNode.rightNode;
	   }
	   return WAVL_tempNode.info;
   }


  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
  public int[] keysToArray()
  {
	  if(empty())
      {
      	return new int[0]; // return empty array if empty.
      }
      
	  int[] arr = new int[size]; 
      WAVLNode WAVL_tempNode = getSmallestNode();
      int i = 0; // counter
      while(i < size)
      {
	        arr[i]=WAVL_tempNode.key;
	        i++;
	        
	        if(WAVL_tempNode.rightNode!=WAVL_emptyNode)
	        {
	        	WAVL_tempNode=WAVL_tempNode.rightNode;
	        	while(WAVL_tempNode.leftNode!=WAVL_emptyNode)
	        	{
	        		WAVL_tempNode=WAVL_tempNode.leftNode;
	        	}
	        }else if(WAVL_tempNode.parentNode!=null){
	        	WAVL_tempNode=WAVL_tempNode.parentNode;
	        }
		}
      return arr;  
  }

  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
        if(empty())
        {
        	return new String[0]; // return empty array if empty.
        }
        
	  	String[] arr = new String[size]; 
        WAVLNode WAVL_tempNode = getSmallestNode();
        int i = 0; // counter
        while(i < size)
        {
	        arr[i]=WAVL_tempNode.info;
	        i++;
	        if(WAVL_tempNode.rightNode!=WAVL_emptyNode)
	        {
	        	WAVL_tempNode=WAVL_tempNode.rightNode;
	        	while(WAVL_tempNode.leftNode!=WAVL_emptyNode)
	        	{
	        		WAVL_tempNode=WAVL_tempNode.leftNode;
	        	}
	        }else if(WAVL_tempNode.parentNode!=null){
	        	WAVL_tempNode=WAVL_tempNode.parentNode;
	        }
  		}
        return arr;                    
  }
  
  private WAVLNode getSmallestNode()
  {
	  if(!empty())
	  {
		  WAVLNode WAVL_tempNode = WAVL_root;
		  while(WAVL_tempNode.leftNode!=WAVL_emptyNode)
		  {
			  WAVL_tempNode=WAVL_tempNode.leftNode;
		  }
		  return WAVL_tempNode;
	  }else{
		  return WAVL_emptyNode; 
	  }
  }
  
   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    *
    * precondition: none
    * postcondition: none
    */
   public int size()
   {
	   return size; 
   }

   /**
    * private static void promote(WAVLNode node)
    *
    * promote the node.
    *
    * precondition: none
    * postcondition: none
    */
   private static void promote(WAVLNode node)
   {
	   
   }
   
   /**
    * private static void demote(WAVLNode node)
    *
    * demotes the node.
    *
    * precondition: none
    * postcondition: none
    */
   private static void demote(WAVLNode node)
   {
	   
   }
   
   /**
    * private static WAVLNode rotateLeft(WAVLNode node)
    *
    * performs a left rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private static WAVLNode rotateLeft(WAVLNode node)
   {
	   WAVLNode pivotNode = node.rightNode;
	   node.rightNode = pivotNode.leftNode;
	   pivotNode.leftNode = node;
	   return pivotNode;
   }
   
   /**
    * private static WAVLNode rotateRight(WAVLNode node)
    *
    * performs a right rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private static WAVLNode rotateRight(WAVLNode node)
   {
	   WAVLNode pivotNode = node.leftNode;
	   node.leftNode = pivotNode.rightNode;
	   pivotNode.rightNode = node;
	   return pivotNode;
   }
   
   /**
    * private static WAVLNode doubleRotateLeftRight(WAVLNode node)
    *
    * performs a left rotation and then right rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private static WAVLNode doubleRotateLeftRight(WAVLNode node)
   {
	   node.leftNode = rotateLeft(node.leftNode);
	   WAVLNode pivotNode = rotateRight(node);
	   return pivotNode;
   }
   
   /**
    * private static WAVLNode doubleRotateRightLeft(WAVLNode node)
    *
    * performs a right rotation and then left rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private static WAVLNode doubleRotateRightLeft(WAVLNode node)
   {
	   node.rightNode = rotateRight(node.rightNode);
	   WAVLNode pivotNode = rotateLeft(node);
	   return pivotNode;
   }
   
  /**
   * public class WAVLNode
   *
   * If you wish to implement classes other than WAVLTree 
   * (for example WAVLNode), do it in this file, not in 
   * another file.
   * This is an example which can be deleted if no such classes are necessary.
   */
  public class WAVLNode{
	  WAVLNode parentNode;
	  WAVLNode leftNode;
	  WAVLNode rightNode;
	  boolean rankDiff;
	  int key;
	  String info;  
  }

  
  
}
  

