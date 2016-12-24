/**
 * WAVLTree
 *
 * An implementation of a WAVL Tree with
 * distinct integer keys and info
 */
public class WAVLTree {
	final WAVLNode WAVL_emptyNode = new WAVLNode();
	private WAVLNode WAVL_root = WAVL_emptyNode;
	private int size = 0;

	  
   /**
    * public int size()
    * returns the number of nodes in the tree.
    * time complexity: O(1)
    * @pre none
    * @post none
    */
   public int size()
   {
	   return size; 
   }

   /**
   * public boolean empty()
   * returns true if and only if the tree is empty
   * time complexity: O(1)
   * @pre none
   * @post none
   */
   public boolean empty() {
	   return (WAVL_root==WAVL_emptyNode); 
   }
  
   /**
   * public String min()
   * @return the info of the item with the smallest key in the tree, 
   * or null if the tree is empty
   * @complexity O(log(n))
   * @dependencies getSmallestNode - O(log(n))
   * @pre none
   * @post none
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
    * @return the info of the item with the largest key in the tree, 
    * or null if the tree is empty.
    * @complexity O(log(n))
    * @pre none
    * @post none 
    */
  public String max()
  {
	   if (empty()){return null;} // no values, so nothing to return
	   
	   WAVLNode WAVL_tempNode = WAVL_root;
	   while(WAVL_tempNode.rightNode != WAVL_emptyNode){
		   WAVL_tempNode = WAVL_tempNode.rightNode;
	   }
	   return WAVL_tempNode.info;
  }

/**
   * public String search(int nodeKey)
   * @return the info of an item with key nodeKey if it exists in the tree. 
   * otherwise, returns null.
   * @param nodeKey the key of the node you are searching for.
   * @complexity O(log(n))
   * @dependencies searchNode - O(log(n))
   * @pre none
   * @post none 
   */
  public String search(int nodeKey)
  {
	  WAVLNode WAVL_tempNode = searchNode(nodeKey);
	  if(WAVL_tempNode!=null){
		  return WAVL_tempNode.info;
	  }else{
		  return null;
	  }
  }
  
  /**
   * public int[] keysToArray()
   * @return a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   * @complexity O(n)
   * @dependencies getSmallestNode - O(log(n)), getSuccessor - O(log(n)) (amortize- O(1))
   * @pre none
   * @post none 
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
   * @return an array which contains the info of each key in the tree sorted respectively,
   * or an empty array if the tree is empty.
   * @complexity: O(n)
   * @dependencies getSmallestNode - O(log(n)), getSuccessor - O(log(n)) (amortize- O(1))
   * @pre none
   * @post none 
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
   * private  WAVLNode searchNode(int nodeKey)
   * @return the node with a key = nodeKey if it exists in the tree. 
   * otherwise, returns null.
   * @complexity O(log(n))
   * @dependencies searchInsertionPlace - O(log(n))
   * @param nodeKey the key of the node you are searching for.
   * @pre none
   * @post none 
   */
  private  WAVLNode searchNode(int nodeKey)
  {
	  if(empty()){return null;} //no nodes, then nothing to return.
	  
	  WAVLNode WAVL_tempNode = searchInsertionPlace(nodeKey);
	  if (WAVL_tempNode.key==nodeKey){ // (=found a node with .key=nodeKey)
		  return WAVL_tempNode;
	  }else{
		  return null;
	  }
  }

/**
   * private  WAVLNode searchInsertionPlace(int nodeKey)
   * @return the node of an key nodeKey or where it should be added. 
   * if tree is empty, returns null.
   * @param nodeKey the key of the node you are searching for.
   * @complexity O(log(n))
   * @pre none
   * @post none 
   */
  private WAVLNode searchInsertionPlace(int nodeKey)
  {
	  if(empty()){return null;} //no nodes, then nothing to return.
	  
	  WAVLNode WAVL_tempNode = WAVL_root;
	  while (WAVL_tempNode.key != nodeKey){
		  if(WAVL_tempNode.key>nodeKey){					// we need to search in the left side.
			  if(WAVL_tempNode.leftNode.isExternalLeaf()) 		// if true we reached the end of the branch.
			  {
				  return WAVL_tempNode;
			  }else{
				  WAVL_tempNode=WAVL_tempNode.leftNode;
			  }
		  }else{ 											// we need to search in the right side.
			  if(WAVL_tempNode.rightNode.isExternalLeaf()) 		// if true we reached the end of the branch
			  {
				  return WAVL_tempNode;
			  }else{
				  WAVL_tempNode=WAVL_tempNode.rightNode;
			  }
		  }
	  }
	  return WAVL_tempNode;
  }

   /**
   * private WAVLNode getSmallestNode()
   * @return pointer of the node with the smallest key in the tree. 
   * or null if the tree is empty.
   * @complexity O(log(n))
   * @pre none
   * @post none 
   */
  private WAVLNode getSmallestNode()
  {
	  if(empty())
	  {
		 return null; 
	  }
	 WAVLNode WAVL_tempNode = WAVL_root;
	 while(WAVL_tempNode.leftNode!=WAVL_emptyNode){ // going all the way down the left side of the tree
		 WAVL_tempNode=WAVL_tempNode.leftNode;
	 }
	 return WAVL_tempNode;
  }

   /**
   * public int insert(int nodeKey, String nodeInfo)
   * inserts an item with key nodeKey and info nodeInfo to the WAVL tree.
   * the tree must remain valid (keep its invariants).
   * @return the number of rebalancing operations,
   * returns -1 if an item with key nodeKey already exists in the tree.
   * @complexity O(log(n))
   * @dependencies - startNewRoot - O(1), searchInsertionPlace - O(log(n)), addNewLeaf - O(log(n))
   * @param nodeKey key of the new node
   * @param nodeInfo key of the new node
   * @pre none
   * @post the WAVL Tree now has the new node, if the key didn't exist already in the tree. 
   */
   public int insert(int nodeKey, String nodeInfo){
	   if(empty()){  										//if empty: Start a new tree.
		   startNewRoot(nodeKey, nodeInfo);
		   return 0;
	   }					
	   WAVLNode parent = searchInsertionPlace(nodeKey); 	//if not: finds the desired insertion place.
	   if (parent.key == nodeKey){	
		   return -1; 											//if already exist: return -1.
	   }										
	   return addNewLeaf(parent, nodeKey, nodeInfo);			//if not: add the new leaf.
   }

/**
    * private int addNewLeaf(WAVLNode parent, int nodeKey, String nodeInfo)
    * create a new leaf node with key k and info i under parent.
    * returns # of rebalances.
    * @complexity O(log(n))
    * @dependencies promote - O(log(n))
    * @param parent pointer to the new node parent.
    * @param nodeKey key of the new node
    * @param nodeInfo info of the new node
    * @pre receives legal place to insert, key and info of the new leaf.
    * @post the tree now has the new leaf.
    */
   private int addNewLeaf(WAVLNode parent, int nodeKey, String nodeInfo){ 
	   size++;
	   boolean parentIsLeaf = parent.isLeaf();
	   WAVLNode node = new WAVLNode(parent,WAVL_emptyNode,WAVL_emptyNode,1,nodeKey,nodeInfo);
	   if(parent.key>nodeKey){
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
    * private void createNewRoot(int k, String i)
    * create a new root node with key k and info i
	* @complexity O(1)
    * @pre tree is currently empty
    * @post tree is now of size 1 and its root is a node with key k and info i.
    */
   
   private void startNewRoot(int nodeKey, String nodeInfo){
	   WAVL_root = new WAVLNode(null, WAVL_emptyNode, WAVL_emptyNode, 1, nodeKey ,nodeInfo);
	   size = 1;
   }

/**
    * private int promote(WAVLNode node)
    * promote the node and fixes the tree if needed.
    * @complexity O(log(n)).
    * @return number of rebalances.
    * @param node the node you want to promote.
    * @pre node!=null
    * @post promoted node rank, and made tree a legal WAVL tree
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
				   rebalanceCounter+=2;										
				   if(node.isLeftNode()){		//node became the parent.
					   rotateRight(node.parentNode);  				
					   fixRanksAfterRotate(node,node.rightNode);
				   }else{
					   rotateLeft(node.parentNode);  				
					   fixRanksAfterRotate(node,node.leftNode);
				   }			
				   return rebalanceCounter;		//problem solved!
				   
			   }else{		// needs double rotation.
				   rebalanceCounter+= 3;										
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
	   return rebalanceCounter + 1; 
   }

	/**
	 * private void fixRanksAfterRotate(WAVLNode newParent, WAVLNode lastParent)
	 * fix ranks after rotate.
	 * @complexity O(1)
	 * @pre none
	 * @post node rank differences are correct now.
	 */
	private void fixRanksAfterRotate(WAVLNode newParent, WAVLNode lastParent){
		   newParent.rankDiff = lastParent.rankDiff;
		   lastParent.rankDiff = 1;
		   lastParent.rightNode.rankDiff = 1;
		   lastParent.leftNode.rankDiff = 1;	   
	}
	
	/**
    * private void fixRanksAfterDoubleRotate(WAVLNode newParent, WAVLNode lastParent)
    * fix ranks after double rotate.
	* @complexity O(1)
	* @pre old and new parent nodes.
	* @post node rank differences are correct now.    
    */
   private void fixRanksAfterDoubleRotate(WAVLNode newParent, WAVLNode lastParent){
	   newParent.rankDiff = lastParent.rankDiff;
	   lastParent.rankDiff = 1;
	   newParent.leftNode.leftNode.rankDiff = 1;
	   newParent.rightNode.rightNode.rankDiff = 1;
   }
   
/**
   * public int delete(int k)
   * deletes an item with key k from the binary tree, if it is there;
   * @complexity O(log(n))
   * @dependencies searchNode(O(log(n)), deleteNode - O(log(n))
   * @return the number of rebalancing operations, 
   * returns -1 if an item with key k was not found in the tree.
   * @pre none
   * @post if the tree had a node with key k, it now doesn't, and the tree is a valid WAVL tree.
   */
   public int delete(int nodeKey){
	   WAVLNode deleteNode = searchNode(nodeKey); // finds the node we wish to delete
	   if(deleteNode == null){
		   return -1;								//return -1 if no node found
	   }else{
		   if(size == 1){
			   WAVL_root = WAVL_emptyNode;
			   size = 0;
			   return 0;
		   }else{
			   return deleteNode(deleteNode);
		   }
	   }
   }
   
   /**
    * private int deleteNode(WAVLNode deleteNode)
    * delete the node deleteNode from the WAVL tree.
    * @return the number of rebalancing operations.
    * @complexity  O(log(n))
    * @dependencies deleteLeafNode - O(log(n)), deleteUnaryNode - O(log(n)), deleteJunctionNode - O(log(n))
    * @pre - deleteNode exists in the tree
    * @post - the tree doesn't have deleteNode anymore and is a valid WAVL tree.
    */
   private int deleteNode(WAVLNode deleteNode){
	   // case 1: node is a junction(has two children).
	   if(deleteNode.isJunction()){
		   return deleteJunctionNode(deleteNode);
	   }// case 2: node is an unary node. 
	   else if(deleteNode.isUnary()){ 
		   return deleteUnaryNode(deleteNode);
	   }// case 3: node is a leaf. if(deleteNode.isLeaf()){
	   else { 
		   return deleteLeafNode(deleteNode);
	   }
   }

   /**
    * private int deleteJunctionNode(WAVLNode deleteNode)
    * delete the node deleteNode from the WAVL tree.
    * @complexity O(log(n))
    * @dependencies deleteNode - O(log(n))
    * @return the number of rebalancing operations.
    * @pre tree.size!=0
    * && node is a junction node
    * @post return number of rebalancing operations.
    */
   private int deleteJunctionNode(WAVLNode deleteNode){
	   WAVLNode deleteNodeSuccesor = deleteNode.getSuccessor();
	   swap(deleteNode,deleteNodeSuccesor);
	   return deleteNode(deleteNodeSuccesor);
   }
   
/**
    * private int deleteUnaryNode(WAVLNode deleteNode)
    * delete the node deleteNode from the WAVL tree.
    * @complexity O(log(n))
    * @dependencies deleteLeafNode - O(log(n))
    * @return the number of rebalancing operations.
    * @pre tree.size!=0 
    * && node is an unary node
    * @post return number of rebalancing operations.
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
    * delete the node deleteNode from the WAVL tree.
    * @complexity O(log(n))
    * @dependencies demote - O(log(n))
    * @return the number of rebalancing operations, or 0 if no rebalancing operations were needed.
    * @pre tree.size!=0 && node is a leaf
    * @post return number of rebalancing operations.
    */
   private int deleteLeafNode(WAVLNode deleteNode){
	   size --;
	   int rebalancingCounter=0; 
	   WAVLNode deleteNodeParent;
	   rebalancingCounter = demote(deleteNode) - 1; 	// change leaf rank from 0 to -1 and adjust WAVL tree.
	   deleteNodeParent = deleteNode.parentNode;
	   if(deleteNodeParent.isUnary() && deleteNode.rankDiff==2){
		   rebalancingCounter += demote(deleteNode) -1 ;// adjust deleteNodeParent rank to be 0 instead of 1.
	   }
	   deleteNodeLeafFromParent(deleteNode);						// delete node
	   return rebalancingCounter;
   }
   
   /**
    * private void deleteLeafFromParent(WAVLNode deleteNode)
    * deletes the node deleteNode from the WAVL tree.
    * @complexity O(1)
    * @pre deleteNode is a leaf and not root.
	* @post deleteNode was replaced as an emptyNode.
    */
   private void deleteNodeLeafFromParent(WAVLNode deleteNode){
	   if(deleteNode.isLeftNode()){ //is left child
		   deleteNode.parentNode.leftNode = WAVL_emptyNode;
	   }else{
		   deleteNode.parentNode.rightNode = WAVL_emptyNode;
	   }
   }
   
   
   /**
    * private static void swap(WAVLNode node)
    * swap the nodes.
    *
    * @complexity  O(1)
    * @pre node1 and node2 are not null.
    * @post the two nodes were swapped.
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
    * demotes the node.
    * @complexity O(log(n))
    * @return how many rebalancing actions were taken.
    * @pre deleteNode is not null
    * @post the rank differences in the tree are correct now.
    */
   private int demote(WAVLNode node)
   {
	   	int rebalancingCounter = 0;
	    WAVLNode nodePartner,topNode,nodeParent,
	    	partnerNode_externalChild,partnerNode_internalChild;
	    
	   	while(node!=WAVL_root){ // "In the end, it doesn't even matter"
			// case 1: node rank difference is 1
	   		if(node.rankDiff==1){
	   			node.rankDiff=2;
	   			return rebalancingCounter+1;
	   		}
	   		
			nodeParent = node.parentNode;
	   		nodePartner = node.getPartner();
						
			// case 2: both node and partner node has rank difference 2
			if(node.rankDiff == 2 && (nodePartner.rankDiff == 2 || nodePartner.isExternalLeaf())){
				nodePartner.rankDiff = 1;
				rebalancingCounter ++;
				node = node.parentNode;
				continue;
			}
			
			if(node.isLeftNode()){
				partnerNode_externalChild = nodePartner.rightNode;
				partnerNode_internalChild = nodePartner.leftNode;
			}else{
				partnerNode_externalChild = nodePartner.leftNode;
				partnerNode_internalChild = nodePartner.rightNode;
			}
			
			//case 3: node is with rank difference 2, and partner with 1, with both of his children has rank difference 2.
			if(partnerNode_internalChild.rankDiff == 2 && partnerNode_externalChild.rankDiff == 2){
				partnerNode_externalChild.rankDiff = 1;
				partnerNode_internalChild.rankDiff = 1;
				rebalancingCounter +=2;
				node = node.parentNode;
				continue;
			}
			

		   // case 4: node is with rank difference 2, and partner with 1, external child has rank difference of one and 
		   // the internal child has rank difference of one or two.
		   if(partnerNode_externalChild.rankDiff == 1 && !partnerNode_externalChild.isExternalLeaf()){
			   if(node.isLeftNode()){
				   topNode = rotateLeft(nodeParent);
			   }else{
				   topNode = rotateRight(nodeParent);
			   }
			   topNode.rankDiff = nodeParent.rankDiff; // nodeParent is now one of top node children
			   nodeParent.rankDiff = 1;
			   if(nodeParent.isLeaf())
			   {
				   nodeParent.rankDiff = 2;
				   rebalancingCounter++;
			   }
			   partnerNode_externalChild.rankDiff = 2; // partnerNode_externalChild is now one of topNode children
			   return rebalancingCounter + 1;    
		   }
		   
		   // case 5: node is with rank difference 2 and partner with 1,  external child has rank difference
		   // of 2 or an external leaf and the internal child has rank difference of 1.	   
		   if((partnerNode_externalChild.rankDiff == 2 || partnerNode_externalChild.isExternalLeaf())
				   && partnerNode_internalChild.rankDiff == 1){
			   if(node.isLeftNode()){
				   topNode = doubleRotateRightLeft(nodeParent);
			   }else{
				   topNode = doubleRotateLeftRight(nodeParent);
			   }
			   topNode.rankDiff = nodeParent.rankDiff;
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
    * private WAVLNode rotateLeft(WAVLNode node)
    * performs a left rotation of the tree.
    * @complexity  O(1)
    * @pre receives the node which needs to be rotated down.
    * @post left rotation was done.
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
    * private WAVLNode rotateRight(WAVLNode node)
    * performs a right rotation of the tree.
    * @complexity  O(1)
    * @pre receives the node which needs to be rotated down.
    * @post right rotation was done.
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
    * private WAVLNode doubleRotateLeftRight(WAVLNode node)
    * performs a left rotation and then right rotation of the tree.
    * @complexity  O(1)
    * @pre receives the node which needs to be rotated down.
    * @post double left right rotation was done.
    */
   private WAVLNode doubleRotateLeftRight(WAVLNode node)
   {
	   node.leftNode = rotateLeft(node.leftNode);
	   return rotateRight(node);
   }
   
   /**
    * private WAVLNode doubleRotateRightLeft(WAVLNode node)
    * performs a right rotation and then left rotation of the tree.
    * @complexity  O(1)
    * @pre receives the node which needs to be rotated down.
    * @post double right left rotation was done.
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
	  * return true if Node has no children.
	  * @complexity  O(1)
	  * @pre - none.
	  * @post - none.
	  */
	  public boolean isLeaf()
	  {
		  return leftNode==rightNode;
	  }
	  
	  /**
	  * public boolean isUnary()
	  * return true if Node has only one children
	  * @complexity  O(1)
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
	  * return true if Node has two children
	  * @complexity  O(1)
	  * @pre - none.
	  * @post - none.
	  */
	  public boolean isJunction()
	  {
		  return (!(leftNode.isExternalLeaf() || rightNode.isExternalLeaf() ));
	  }
	  
	  /** public boolean isExternalLeaf()
	   * return true if Node is an external leaf.
	  * @complexity  O(1)
	  * @pre - node is not the root.
	  * @post - none.
	  */
	  public boolean isExternalLeaf()
	  {
		  return this==WAVL_emptyNode;
	  }
	  
	  /** public boolean isLeftNode()
	   * returns whether the node is a left node. 
	  * @complexity  O(1)
	  * @pre node is not the root.
	  * @post none.
	  */
	  public boolean isLeftNode()
	  {
		return this==this.parentNode.leftNode;   
	  }
	  
	  /** public WAVLNode getPartner()
	   * return the node's brother if exist or null otherwise.
	  * @complexity  O(1)
	  * @pre none.
	  * @post none.
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
	   * private WAVLNode getSuccessor()
	   * Returns pointer of the first node with a bigger key then WAVL_Node.
	   * @complexity O(log(n)), amortize time - O(1)
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
	  * Returns pointer of the first node with a bigger key then WAVL_Node 
	  * @complexity  worst case O(log(n)), amortize time - O(1)
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
}
  

