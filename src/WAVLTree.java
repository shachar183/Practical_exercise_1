
/* TODO 
	1. check that the number of rebalancing actions is correct.
	2. see if we can generalize the rotate and double rotate.
	3. see if we can check the functions for more edge cases.
	4. Document the complexity of each function.
	5. excel of actions.
	6. go over the documentation of each function.
	7. delete test function in the end of the file.
	8. add comments in the code.
	9. after testing - change variables and functions to private/static when needed.
	
*/




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
	// TODO change to private
	public WAVLNode WAVL_root = WAVL_emptyNode;
	private int size = 0;

	  
   /**
    * public int size()
    *
    * returns the number of nodes in the tree.
    *
    * time complexity: O(1)
    * @pre: none
    * @post: none
    */
   public int size()
   {
	   return size; 
   }

   /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
   public boolean empty() {
	   return (WAVL_root==WAVL_emptyNode); 
   }
  
   /**
   * public String min()
   *
   * time complexity: O(log(h))
   * returns the info of the item with the smallest key in the tree,
   * or null if the tree is empty
   * @pre: none
   * @post: none
   */
  public String min()
  {
	   if (empty()){
		   return null;
	   }
	   
	   return getSmallestNode().info;
  }

/**
    * public String max()
    *
    * time complexity: O(log(h))
    * returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
    * @pre: none
    * @post: none 
    */
  public String max()
  {
	   WAVLNode WAVL_tempNode = WAVL_root;
	   if (empty()){
		   return null;
	   }
	   while(WAVL_tempNode.rightNode != WAVL_emptyNode){
		   WAVL_tempNode = WAVL_tempNode.rightNode;
	   }
	   return WAVL_tempNode.info;
  }

/**
   * public String search(int k)
   * 
   * time complexity: O(log(h))
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   * @pre: none
   * @post: none 
   */
  public String search(int k)
  {
	  WAVLNode WAVL_tempNode = searchNode(k);
	  if(WAVL_tempNode!=null){
		  return WAVL_tempNode.info;
	  }else{
		  return null;
	  }
  }
  
  /**
   * private  WAVLNode searchNode(int k)
   *
   * time complexity: O(log(h))
   * returns the node of an key k if it exists in the tree
   * otherwise, returns null
   * @pre: none
   * @post: none 
   */
  private  WAVLNode searchNode(int k)
  {
	  if(empty()){
		  return null;
	  }
	  
	  WAVLNode WAVL_tempNode = searchInsertionPlace(k);
	  if (WAVL_tempNode.key==k)
	  {
		  return WAVL_tempNode;
	  }else{
		  return null;
	  }
  }

/**
   * private  WAVLNode searchInsertionPlace(int k)
   *
   * time complexity: O(log(h))
   * returns the node of an key k or where k should be added.
   * otherwise, returns null
   * @pre: none
   * @post: none 
   */
  private WAVLNode searchInsertionPlace(int k)
  {
	  if(empty()){
		  return null;
	  }else{
		  WAVLNode WAVL_tempNode = WAVL_root;
		  while (WAVL_tempNode.key != k)
		  {
			  if(WAVL_tempNode.key>k) // we need to search in the left side.
			  {
				  if(WAVL_tempNode.leftNode.isExternalLeaf()) // we reached the end of the branch
				  {
					  return WAVL_tempNode;
				  }else{
					  WAVL_tempNode=WAVL_tempNode.leftNode;
				  }
			  }else{ // we need to search in the right side.
				  if(WAVL_tempNode.rightNode.isExternalLeaf()) // we reached the end of the branch
				  {
					  return WAVL_tempNode;
				  }else{
					  WAVL_tempNode=WAVL_tempNode.rightNode;
				  }
			  }
		  }
		  return WAVL_tempNode;
	  }
	  
  }

/**
   * private WAVLNode getSmallestNode()
   * 
   * time complexity: O(log(h))
   * Returns pointer of the node with the smallest key in the tree
   * or null if the tree is empty.
   * @pre: none
   * @post: none 
   */
  
  private WAVLNode getSmallestNode()
  {
	  if(empty())
	  {
		 return null; 
	  }else{
		 WAVLNode WAVL_tempNode = WAVL_root;
		 while(WAVL_tempNode.leftNode!=WAVL_emptyNode) // going all the way down the left side of the tree
		 {
			 WAVL_tempNode=WAVL_tempNode.leftNode;
		 }
		 return WAVL_tempNode;
	  }
  }

/**
   * public int[] keysToArray()
   * 
   * time complexity: O(n)
   * returns a sorted array which contains all keys in the tree, or an empty array if the tree is empty.
   * @pre: none
   * @post: none 
   */
  public int[] keysToArray()
  {
	  int[] arr = new int[size]; 
      WAVLNode WAVL_tempNode = getSmallestNode();
      int counter = 0; 
      while(WAVL_tempNode!=null)
      {
	        arr[counter]=WAVL_tempNode.key;
	        counter++;
	        WAVL_tempNode = WAVL_tempNode.getSuccessor();
      }
      return arr;  
  }

/**
   * public String[] infoToArray()
   *
   * time complexity: O(n)
   * returns an array which contains the info of each key in the tree sorted respectively,
   * or an empty array if the tree is empty.
   * @pre: none
   * @post: none 
   */
  public String[] infoToArray()
  {
	  	String[] arr = new String[size]; 
        WAVLNode WAVL_tempNode = getSmallestNode();
        int counter = 0; 
        while(WAVL_tempNode!=null)
        {
	        arr[counter]=WAVL_tempNode.info;
	        counter++;
	        WAVL_tempNode = WAVL_tempNode.getSuccessor();
  		}
        return arr;                    
  }

/**
   * public int insert(int k, String i)
   * 
   * time complexity: O(log(h))
   * inserts an item with key k and info i to the WAVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   * @pre: item to insert, consisted of key k and info i.
   * @post: the WAVL Tree now has the new node, if the key didn't exist already in the tree. 
   */
   public int insert(int k, String i) 
   {
	   if(empty())  //if empty: Start a new tree.
	   {	
		   startNewRoot(k, i);
		   return 0;
	   }					
	   WAVLNode parent = searchInsertionPlace(k); 	//finds the desired insertion place.
	   if (parent.key == k)
	   {	
		   return -1; //if already exist: return -1.
	   }										
	   return addNewLeaf(parent, k, i);	//add the new leaf.
   }

/**
    * private int addNewLeaf(WAVLNode parent, int k, String i)
    *
    * time complexity: O(log(h))
    * create a new leaf node with key k and info i under parent.
    * returns # of rebalances.
    * @pre: receives place to insert, key and info of the new leaf.
    * @post: the tree now has the new leaf.
    */
   private int addNewLeaf(WAVLNode parent, int k, String i){ 
	   size++;
	   boolean parentIsLeaf = parent.isLeaf();
	   WAVLNode node = new WAVLNode(parent,WAVL_emptyNode,WAVL_emptyNode,1,k,i);
	   if(parent.key>k){
		  parent.leftNode = node;
	   }else{
		   parent.rightNode = node;
	   }
	   if(parentIsLeaf){
		  return promote(parent);
	   }else{
		  return 0;
	   }
   }

/**
    * private int promote(WAVLNode WAVL_Node)
    *
    * worst case time complexity: O(log(h)).
    * promote the node and fixes the tree if needed.
    * return number of rebalances.
    * @pre: none
    * @post: none
    */
   private int promote(WAVLNode node)
   {
	   int rebalanceCounter = 0;
	   WAVLNode partnerNode, innerNode;
	   while(node != WAVL_root){ 		// "because in the end it doesn't even matter"
		   if(node.rankDiff == 2){										
			   node.rankDiff = 1;
			   return rebalanceCounter + 1;   //fixed problem!
		   }	
		   
		   partnerNode=node.getPartner();
		   if((partnerNode.rankDiff == 1) && (!partnerNode.isExternalLeaf())){				
			   partnerNode.rankDiff = 2;
			   node = node.parentNode; 		// need to promote parent node.	
			   rebalanceCounter++;
		   }else{							// needs to be rotated.
			   if(node.isLeftNode()){					
				   innerNode=node.rightNode;	//get inner node.
			   }else{			
				   innerNode=node.leftNode;
			   }
			   if((innerNode.rankDiff == 2) || (innerNode.isExternalLeaf())){		// needs single rotation.
				   rebalanceCounter++;										
				   if(node.isLeftNode()){		//node became the parent.
					   rotateRight(node.parentNode);  				
					   fixRanksAfterRotate(node,node.rightNode);
				   }else{
					   rotateLeft(node.parentNode);  				
					   fixRanksAfterRotate(node,node.leftNode);
				   }			
				   return rebalanceCounter;		//problem solved!
				   
			   }else{		// needs double rotation.
				   rebalanceCounter+= 2;										
				   if(node.isLeftNode()){
					   node = doubleRotateLeftRight(node.parentNode);
					   fixRanksAfterDoubleRotate(node,node.rightNode);
				   }else{
					   node = doubleRotateRightLeft(node.parentNode);
					   fixRanksAfterDoubleRotate(node,node.leftNode);
				   }			
				   return rebalanceCounter;		//problem solved!
			   }
		   }
	   }
	   return rebalanceCounter; 
   }

	/**
	 * private void fixRanksAfterRotate(WAVLNode newParent, WAVLNode lastParent)
	 *
	 * time complexity - O(1)
	 * fix ranks after rotate.
	 * @pre: old and new parent nodes.
	 * @post: node rank differences are correct now.
	 */
	private void fixRanksAfterRotate(WAVLNode newParent, WAVLNode lastParent){
		   newParent.rankDiff = lastParent.rankDiff;
		   lastParent.rankDiff = 1;
		   lastParent.rightNode.rankDiff = 1;
		   lastParent.leftNode.rankDiff = 1;	   
	}
	
	/**
    * private void fixRanksAfterDoubleRotate(WAVLNode newParent, WAVLNode lastParent)
    *
	* time complexity - O(1)
	* fix ranks after double rotate.
	* @pre: old and new parent nodes.
	* @post: node rank differences are correct now.    
    */
   private void fixRanksAfterDoubleRotate(WAVLNode newParent, WAVLNode lastParent){
	   newParent.rankDiff = lastParent.rankDiff;
	   lastParent.rankDiff = 1;
	   newParent.leftNode.leftNode.rankDiff = 1;
	   newParent.rightNode.rightNode.rankDiff = 1;
   }
   
/**
    * private void createNewRoot(int k, String i)
    *
	* time complexity - O(1)
    * create a new root node with key k and info i
    * @pre: tree is currently empty
    * @post: tree is now of size 1 and its root is a node with key k and info i.
    */
   
   private void startNewRoot(int k, String i){
	   WAVL_root = new WAVLNode(null, WAVL_emptyNode, WAVL_emptyNode, 1, k ,i);
	   size = 1;
   }

/**
   * public int delete(int k)
   *
   * time complexity - O(log(h))
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * returns -1 if an item with key k was not found in the tree.
   * @pre: none.
   * @post: if the tree had a node with key k, it now doesn't, and the tree is a valid WAVL tree.
   */
   public int delete(int k)
   {
	   WAVLNode deleteNode = searchNode(k); // finds the node we wish to delete
	   if(deleteNode == null){
		   return -1;
	   }else{
		   if(size == 1){
			   WAVL_root = WAVL_emptyNode;
			   size --;
			   return 0;
		   }else{
			   return deleteNode(deleteNode);
		   }
	   }
   }
   
   /**
    * private int deleteUnaryNode(WAVLNode deleteNode)
    *
    * time complexity - O(log(h))
    * delete the node deleteNode from the WAVL tree.
    * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
    * @pre: tree.size!=0
    * @pre: node is an unary node
    * @post: return number of rebalancing operations.
    */
   private int deleteUnaryNode(WAVLNode deleteNode){
	   if(deleteNode.rightNode.isExternalLeaf()){
		   swap(deleteNode,deleteNode.leftNode);
		   return deleteLeafNode(deleteNode.leftNode);
	   }else{
		   swap(deleteNode,deleteNode.rightNode);
		   return deleteLeafNode(deleteNode.rightNode);		   
	   }
   }

   
   /**
    * private int deleteLeafNode(WAVLNode deleteNode)
    *
    * time complexity - O(log(h))
    * delete the node deleteNode from the WAVL tree.
    * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
    * @pre: tree.size!=0
    * @pre: node is a leaf
    * @post: return number of rebalancing operations.
    */
   private int deleteLeafNode(WAVLNode deleteNode){
	   int rebalancingCounter = 0; 
	   WAVLNode deleteNodeParent = deleteNode.parentNode;
	   WAVLNode partnerNode = deleteNode.getPartner();
	   int partnerNode_rankDiff = partnerNode.rankDiff;
	   if(partnerNode.isExternalLeaf())
	   {
		   partnerNode_rankDiff = 2;
	   }
	
		// case 1: deleteNode is a leaf and we don't need to demote its father.
	   if(deleteNode.rankDiff == 1 && partnerNode_rankDiff == 1){		   
		   deleteNodeLeafFromParent(deleteNode);
		   size --;
		   return rebalancingCounter;
	   }
	   
	   // case2 : parent's other child is external leaf and deleteNode is leaf
	   if(deleteNode.rankDiff == 1 && partnerNode.isExternalLeaf()){
		   deleteNodeLeafFromParent(deleteNode);
		   size --;
		   return demote(deleteNodeParent,rebalancingCounter);
	   }
	   
	   // case 3: two leaves with rank difference two.
	   if(deleteNode.rankDiff == 2 && partnerNode_rankDiff==2){
		   deleteNodeLeafFromParent(deleteNode);
		   partnerNode.rankDiff = 1;
		   size --;
		   return demote(deleteNodeParent,rebalancingCounter);
	   }
	   
		WAVLNode partnerNode_externalChild,partnerNode_internalChild;
		if(deleteNode.isLeftNode()){
			partnerNode_externalChild = partnerNode.rightNode;
			partnerNode_internalChild = partnerNode.leftNode;
		}else{
			partnerNode_externalChild = partnerNode.leftNode;
			partnerNode_internalChild = partnerNode.rightNode;
		}
	   
	   // case 4: one is a leaf and the other child's external child has rank difference of one and 
	   // the internal child has rank difference of one or two.
	   if(partnerNode_externalChild.rankDiff == 1 && !partnerNode_externalChild.isExternalLeaf()){
		   if(deleteNode.isLeftNode()){
			   deleteNodeLeafFromParent(deleteNode);
			   WAVLNode topNode = rotateLeft(deleteNodeParent);
			   topNode.rankDiff = topNode.leftNode.rankDiff;
			   topNode.leftNode.rankDiff = 1;
			   if(topNode.leftNode.isLeaf()){
				   topNode.leftNode.rankDiff = 2;
			   }
			   topNode.rightNode.rankDiff = 2;
		   }else{
			   deleteNodeLeafFromParent(deleteNode);
			   WAVLNode topNode = rotateRight(deleteNodeParent);
			   topNode.rankDiff = topNode.rightNode.rankDiff;
			   topNode.rightNode.rankDiff = 1;
			   if(topNode.rightNode.isLeaf()){
				   topNode.rightNode.rankDiff = 2;
			   }
			   topNode.leftNode.rankDiff = 2;
		   }
		   size --;
		   return rebalancingCounter + 1;    
	   }
	   
	   // case 5: one is a leaf and the other child's external child has rank difference
	   // of two or an external leaf and the internal child has rank difference of one.	   
	   if((partnerNode_externalChild.rankDiff == 2 || partnerNode_externalChild.isExternalLeaf())
			   && partnerNode_internalChild.rankDiff == 1){
		   WAVLNode topNode;
		   if(deleteNode.isLeftNode()){
			   deleteNodeLeafFromParent(deleteNode);
			   topNode = doubleRotateRightLeft(deleteNodeParent);
			   topNode.rankDiff = topNode.leftNode.rankDiff;
		   }else{
			   deleteNodeLeafFromParent(deleteNode);
			   topNode = doubleRotateLeftRight(deleteNodeParent);
			   topNode.rankDiff = topNode.rightNode.rankDiff;
		   }
		   topNode.leftNode.rankDiff = 2;
		   topNode.rightNode.rankDiff = 2;
		   size --;
		   return rebalancingCounter + 2; 
	   }
	   return rebalancingCounter;
   }
   
   /**
    * private void deleteLeafFromParent(WAVLNode deleteNode)
    *
    * time complexity - O(1)
    * deletes the node deleteNode from the WAVL tree.
    * @pre: deleteNode is a leaf and not root.
	* @post: deleteNode was replaced as an emptyNode.
    */
   private void deleteNodeLeafFromParent(WAVLNode deleteNode){
	   if(deleteNode.isLeftNode()){ //is left child
		   deleteNode.parentNode.leftNode = WAVL_emptyNode;
	   }else{
		   deleteNode.parentNode.rightNode = WAVL_emptyNode;
	   }
   }
   
   
   /**
    * private int deleteNode(WAVLNode deleteNode)
    *
    * time complexity - O(log(h))
    * delete the node deleteNode from the WAVL tree.
    * the tree must remain valid (keep its invariants).
    * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
    * @pre - deleteNode exists in the tree
    * @post - the tree doesn't have deleteNode anymore and is a valid WAVL tree.
    */
   private int deleteNode(WAVLNode deleteNode){
	   int rebalancingCounter = 0;
	   // case 1: node is a junction(has two children).
	   if(deleteNode.isJunction()){
		   WAVLNode deleteNodeSuccesor = deleteNode.getSuccessor();
		   swap(deleteNode,deleteNodeSuccesor);
		   return deleteNode(deleteNodeSuccesor);
	   } 
	   // case 2: node is a leaf.
	   if(deleteNode.isLeaf()){
		   return deleteLeafNode(deleteNode);
	   }
	   // case 3: node is an unary node.
	   if(deleteNode.isUnary()){
		   return deleteUnaryNode(deleteNode);
	   }
	   return rebalancingCounter;
   }

/**
    * private static void swap(WAVLNode node)
    *
    * time complexity - O(1)
    * swap the nodes.
    * @pre: node1 and node2 are not null.
    * @post: the two nodes were swapped.
    */
   private static void swap(WAVLNode node1 , WAVLNode node2)
   {
	   	int key = node1.key;
	   	String info = node1.info;
	   	node1.key = node2.key;
	   	node1.info = node2.info;
	   	node2.key = key;
	   	node2.info = info;
   }
/**
    * private static void demote(WAVLNode node)
    *
    * worst case time complexity - O(log(h))
    * demotes the node.
    * returns how many rebalancing actions were taken.
    * @pre: deleteNode is not null
    * @post: the rank differences in the tree are correct now.
    */
   private int demote(WAVLNode deleteNode , int rebalancingCounter)
   {
	    WAVLNode partnerNode;
 	    WAVLNode deleteNodeParent;
 	    WAVLNode partnerNode_externalChild,partnerNode_internalChild;
	    WAVLNode topNode;
	   	while(deleteNode!=WAVL_root){ // "In the end, it doesn't even matter"
			// case 1: node rank difference is 1
	   		deleteNodeParent = deleteNode.parentNode;
	   		partnerNode = deleteNode.getPartner();
			if(deleteNode.rankDiff==1){
				deleteNode.rankDiff=2;
				return rebalancingCounter+1;
			}
			
			// case 2: both node and partner node has rank difference 2
			if(deleteNode.rankDiff == 2 && partnerNode.rankDiff == 2){
				partnerNode.rankDiff = 1;
				rebalancingCounter ++;
				deleteNode = deleteNode.parentNode;
				continue;
			}
			
			if(deleteNode.isLeftNode()){
				partnerNode_externalChild = partnerNode.rightNode;
				partnerNode_internalChild = partnerNode.leftNode;
			}else{
				partnerNode_externalChild = partnerNode.leftNode;
				partnerNode_internalChild = partnerNode.rightNode;
			}
			
			//case 3: node is with rank difference 2, and partner with 1, with both of his children has rank difference 2.
			if(partnerNode_internalChild.rankDiff == 2 && partnerNode_externalChild.rankDiff == 2){
				partnerNode_externalChild.rankDiff = 1;
				partnerNode_internalChild.rankDiff = 1;
				rebalancingCounter +=2;
				deleteNode = deleteNode.parentNode;
				continue;
			}
			
		   // case 4: node is with rank difference 2, and partner with 1, external child has rank difference of one and 
		   // the internal child has rank difference of one or two.
		   if(partnerNode_externalChild.rankDiff == 1 && !partnerNode_externalChild.isExternalLeaf()){
			   if(deleteNode.isLeftNode()){
				   topNode = rotateLeft(deleteNodeParent);
				   topNode.rankDiff = topNode.leftNode.rankDiff;
				   topNode.leftNode.rankDiff = 1;
				   if(topNode.leftNode.isLeaf())
				   {
					   topNode.leftNode.rankDiff = 2;
				   }
				   topNode.rightNode.rankDiff = 2;
			   }else{
				   topNode = rotateRight(deleteNodeParent);
				   topNode.rankDiff = topNode.rightNode.rankDiff;
				   topNode.leftNode.rankDiff = 2;
				   topNode.rightNode.rankDiff = 1;
				   if(topNode.rightNode.isLeaf())
				   {
					   topNode.rightNode.rankDiff = 2;
				   }
			   }
			   return rebalancingCounter + 1;    
		   }
		   
		   // case 5: node is with rank difference 2 and partner with 1,  external child has rank difference
		   // of 2 or an external leaf and the internal child has rank difference of 1.	   
		   if((partnerNode_externalChild.rankDiff == 2 || partnerNode_externalChild.isExternalLeaf())
				   && partnerNode_internalChild.rankDiff == 1){
			   if(deleteNode.isLeftNode()){
				   topNode = doubleRotateRightLeft(deleteNodeParent);
				   topNode.rankDiff = topNode.leftNode.rankDiff;
			   }else{
				   topNode = doubleRotateLeftRight(deleteNodeParent);
				   topNode.rankDiff = topNode.rightNode.rankDiff;
			   }
			   topNode.leftNode.rankDiff = 2;
			   topNode.rightNode.rankDiff = 2;
			   topNode.leftNode.leftNode.rankDiff = 1;
			   topNode.rightNode.rightNode.rankDiff = 1;
			   return rebalancingCounter + 2; 
		   }
	   }   
	   return rebalancingCounter + 1;
    }

    /**
    * private static WAVLNode rotateLeft(WAVLNode node)
    *
    * time complexity - O(1)
    * performs a left rotation of the tree.
    *
    * @pre: receives the node which needs to be rotated down.
    * @post: left rotation was done.
    */
   private WAVLNode rotateLeft(WAVLNode node)
   {
	   WAVLNode pivotNode = node.rightNode;
	   if(node.parentNode!=null){
		   if(node.parentNode.leftNode==node)
		   {
			   node.parentNode.leftNode = pivotNode;
		   }else{
			   node.parentNode.rightNode = pivotNode;
		   }
	   }else{
		   WAVL_root = pivotNode;
	   }
	   node.rightNode = pivotNode.leftNode;
	   pivotNode.leftNode = node;
	   pivotNode.parentNode = pivotNode.leftNode.parentNode;
	   pivotNode.leftNode.parentNode = pivotNode;
	   pivotNode.leftNode.rightNode.parentNode = pivotNode.leftNode;
	   return pivotNode;
   }
   
   /**
    * private static WAVLNode rotateRight(WAVLNode node)
    *
    * time complexity - O(1)
    * performs a right rotation of the tree.
    *
    * @pre: receives the node which needs to be rotated down.
    * @post: right rotation was done.
    */
   private WAVLNode rotateRight(WAVLNode node)
   {
	   WAVLNode pivotNode = node.leftNode;
	   if(node.parentNode!=null){
		   if(node.parentNode.leftNode==node)
		   {
			   node.parentNode.leftNode = pivotNode;
		   }else{
			   node.parentNode.rightNode = pivotNode;
		   }
	   }else{
		   WAVL_root = pivotNode;
	   }
	   node.leftNode = pivotNode.rightNode;
	   pivotNode.rightNode = node;
	   pivotNode.parentNode = pivotNode.rightNode.parentNode;
	   pivotNode.rightNode.parentNode = pivotNode;
	   pivotNode.rightNode.leftNode.parentNode = pivotNode.rightNode;
	   return pivotNode;
   }
   
   /**
    * private static WAVLNode doubleRotateLeftRight(WAVLNode node)
    *
    * time complexity - O(1)
    * performs a left rotation and then right rotation of the tree.
    *
    * @pre: receives the node which needs to be rotated down.
    * @post: double left right rotation was done.
    */
   private WAVLNode doubleRotateLeftRight(WAVLNode node)
   {
	   node.leftNode = rotateLeft(node.leftNode);
	   return rotateRight(node);
   }
   
   /**
    * private static WAVLNode doubleRotateRightLeft(WAVLNode node)
    *
    * time complexity - O(1)
    * performs a right rotation and then left rotation of the tree.
    *
    * @pre: receives the node which needs to be rotated down.
    * @post: double right left rotation was done.
    */
   private WAVLNode doubleRotateRightLeft(WAVLNode node)
   {
	   rotateRight(node.rightNode);
	   return rotateLeft(node);
   }
   
  /**
   * public class WAVLNode
   * the WAVL tree is consisted from connection of WAVL nodes.
   *
   */
  public class WAVLNode{
	  WAVLNode parentNode;
	  WAVLNode leftNode;
	  WAVLNode rightNode;
	  int rankDiff; 
	  int key;
	  String info;  
	  
	  /**
	   * Constructor for WAVL node.
	   */
	  public WAVLNode(){
		  parentNode = null;
		  leftNode = WAVL_emptyNode;
		  rightNode = WAVL_emptyNode;
		  rankDiff = 1;
	  }
	  
	  /**
	   * Constructor for WAVL node.
	   */
	  public WAVLNode(WAVLNode parentnode, WAVLNode leftnode,
			  WAVLNode rightnode, int rankdiff, int node_key, String node_info){
		  parentNode = parentnode;
		  rightNode = rightnode;
		  leftNode = leftnode;
		  rankDiff = rankdiff;
		  key=node_key;
		  info=node_info;
	  }
	  
	  /**
	  * public boolean isLeaf()
	  * 
	  * time complexity - O(1)
	  * return true if Node has no children.
	  * @pre - none.
	  * @post - none.
	  */
	  public boolean isLeaf()
	  {
		  return leftNode==rightNode;
	  }
	  
	  /**
	  * public boolean isUnary()
	  * 
	  * time complexity - O(1)
	  * return true if Node has only one children
	  * @pre - none.
	  * @post - none.
	  */
	  public boolean isUnary()
	  {
		  if((!isLeaf()) && (leftNode.isExternalLeaf() || rightNode.isExternalLeaf())){
			  return true;
		  }
		  return false;
	  }
	  
	  /**
	  * public boolean isJunction()
	  * 
	  * time complexity - O(1)
	  * return true if Node has two children
	  * @pre - none.
	  * @post - none.
	  */
	  public boolean isJunction()
	  {
		  return (!(leftNode.isExternalLeaf() || rightNode.isExternalLeaf() ));
	  }
	  
	  /** public boolean isExternalLeaf()
	  * 
	  * time complexity - O(1)
	  * return true if Node is an external leaf.
	  * @pre - node is not the root.
	  * @post - none.
	  */
	  public boolean isExternalLeaf()
	  {
		  return this==WAVL_emptyNode;
	  }
	  
	  /** public boolean isLeftNode()
	  * 
	  * time complexity - O(1)
	  * returns whether the node is a left node. 
	  * 
	  * @pre: node is not the root.
	  * @post: none.
	  */
	  public boolean isLeftNode()
	  {
		return this==this.parentNode.leftNode;   
	  }
	  
	  /** public WAVLNode getPartner()
	  * 
	  * time complexity - O(1)
	  * return the node's brother node if exist or null otherwise.
	  * @pre: none.
	  * @post: none.
	  */
	  public WAVLNode getPartner()
	  {
		  if(this.parentNode!=null){
			  if(this.isLeftNode()){ 						//Node is a left node.
				  return this.parentNode.rightNode;
			  }else{										//Node is a right node.
				  return this.parentNode.leftNode;
			  }
		  }
		  return null;
	  }
	  
	  /**
	   * private WAVLNode getSuccessor(WAVLNode WAVL_Node)
	   * 
	   * worst case time complexity - O(log(h)), average time complexity - O(1)
	   * Returns pointer of the first node with a bigger key then WAVL_Node 
	   * TODO what happens if we have only one node in the tree? is it ok to return itself?
	   * @pre - none.
	   * @post - none.
	   */
	  public WAVLNode getSuccessor(){
		  WAVLNode WAVL_Node = this;
		  if(!WAVL_Node.rightNode.isExternalLeaf())
	      {
			WAVL_Node=WAVL_Node.rightNode;
	      	while(!WAVL_Node.leftNode.isExternalLeaf())
	      	{
	      		WAVL_Node=WAVL_Node.leftNode;
      		}
	      }else{
	    	  while(WAVL_Node.parentNode!=null && WAVL_Node == WAVL_Node.parentNode.rightNode){
	    		  WAVL_Node=WAVL_Node.parentNode;
    		  }
			  if(WAVL_Node.parentNode!=null){ 
				  WAVL_Node=WAVL_Node.parentNode;
			  }else{
				  return null;
			  }
	      }
		  return WAVL_Node;
	  }
	  
	  /**
	  * private WAVLNode getPredecessor(WAVLNode WAVL_Node)
	  * 
	  * worst case time complexity - O(log(h)), average time complexity - O(1)
	  * Returns pointer of the first node with a bigger key then WAVL_Node 
	  * @pre - none.
	  * @post - none.
	  */
	  public WAVLNode getPredecessor(){
		  WAVLNode WAVL_Node = this;
		  if(!WAVL_Node.leftNode.isExternalLeaf()){
			  WAVL_Node=WAVL_Node.leftNode;
	      	  while(!WAVL_Node.rightNode.isExternalLeaf()){
	      		WAVL_Node=WAVL_Node.rightNode;
	      	  }
	      }else{
	    	  while(WAVL_Node.parentNode!=null && WAVL_Node == WAVL_Node.parentNode.leftNode){
	    		  WAVL_Node=WAVL_Node.parentNode;
    		  }
			  if(WAVL_Node.parentNode!=null){
				  WAVL_Node=WAVL_Node.parentNode;
			  }else{
				  return null;
			  }
	      }
		  return WAVL_Node;
	  }
  }

  
  // for testing:
  
	public boolean testTreeRanks()
	{
		if(!empty())
		{
			
			 WAVLNode WAVL_tempNode = getSmallestNode();
			 int counter = 0;
			 int lastKey = WAVL_tempNode.key;
			 while(counter<size-1){
				 WAVL_tempNode=WAVL_tempNode.getSuccessor();
				 if(lastKey>=WAVL_tempNode.key){
					 System.out.println("Keys not in order ERROR");
					 return false;
				 }
				 counter++;
			 }
			 if(WAVL_tempNode.getSuccessor()!=null){
				 System.out.println("Size is not right");
				 return false;
			 }
			 
			 WAVL_tempNode = getSmallestNode();
			 counter = 0;
			 boolean passedThroughALeaf = false;
			 int rank = 0;
			 do{
				 if (WAVL_tempNode.isLeaf())
				 {
					 if(passedThroughALeaf)
					 {
						 if(rank!=0){
							 System.out.println("Ranks problem");
							 return false;
						 }
					 }else{
						 passedThroughALeaf=true;
						 rank = 0;
					 }
				 }
				 do{
					 if(WAVL_tempNode.rightNode!=WAVL_emptyNode)
				     {
						WAVL_tempNode=WAVL_tempNode.rightNode;
						rank -= WAVL_tempNode.rankDiff;
						while(WAVL_tempNode.leftNode!=WAVL_emptyNode)
						{
							 WAVL_tempNode=WAVL_tempNode.leftNode;
							 rank -= WAVL_tempNode.rankDiff;
						}
				      }else if(WAVL_tempNode.parentNode!=null){
				    	  while(WAVL_tempNode.parentNode!=null && WAVL_tempNode ==WAVL_tempNode.parentNode.rightNode){
				    		  rank += WAVL_tempNode.rankDiff;
					    	  WAVL_tempNode=WAVL_tempNode.parentNode;
				    	  }
				    	  if(WAVL_tempNode.parentNode!=null)
				    	  {
				    		  rank += WAVL_tempNode.rankDiff;
					    	  WAVL_tempNode=WAVL_tempNode.parentNode;
				    	  }
				      }
					 counter++;
				 }while((!WAVL_tempNode.isLeaf()) && counter<size);
			 }while(counter<size);
			 return true;
		}else{
			return true;
		}
	}
  
  
  
  
}
  

