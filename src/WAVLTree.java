/**
 *
 * WAVLTree
 *
 * An implementation of a WAVL Tree with
 * distinct integer keys and info
 *
 */

public class WAVLTree {
	final WAVLNode WAVL_emptyNode = new WAVLTree().WAVLNode();
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
	   if(empty()){
		   WAVL_root = new WAVLNode(null, WAVL_emptyNode, WAVL_emptyNode, 1, k ,i);
		   size++;
		   return 0;
	   }else{
		   //finds desired insertion place
		   WAVLNode WAVL_tempNode = searchInsertionNode(k);
		   if (WAVL_tempNode.key == k)
		   {
			   return -1; // already exist.
		   }else{
			   size++;
			   // tempNode would be left child
			   if(WAVL_tempNode.key<k)
			   {
				   	WAVL_tempNode.leftNode = new WAVLNode(WAVL_tempNode,WAVL_emptyNode,
				   			WAVL_emptyNode,1,k,i);	
				   	if (WAVL_tempNode.rightNode == WAVL_emptyNode) // if tempNode was a leaf
				   	{
				   		return promote(WAVL_tempNode);
				   	}
			   }else{ // tempNode would be right child
				   WAVL_tempNode.rightNode = new WAVLNode(WAVL_tempNode,WAVL_emptyNode,
				   			WAVL_emptyNode,1,k,i);
				   if (WAVL_tempNode.leftNode == WAVL_emptyNode) // if tempNode was a leaf
				   	{
					   return promote(WAVL_tempNode);
				   	}
			   }
			   return 0;
		   }
	   }
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
	   int rebalancingCounter = 0;
	   WAVLNode deleteNode = searchNode(k);
	   if(deleteNode.equals(null)){
		   return -1;
	   }else{
		   if(size == 1){
			   WAVL_root = WAVL_emptyNode;
			   size --;
		   }else{
			   //checking if the node has a father.
			   WAVLNode deleteNodeParent = deleteNode.parentNode;
			   
			   // TODO case 0: deleteNode parent is null.
			   
			   // TODO case x: deleteNode has two children. wait for answer in forum.
			   
			   // case 1: deleteNode is a leaf and we don't need to demote its father.
			   if(isLeaf(deleteNode) && deleteNode.rankDiff == 1){
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   if(deleteNodeParent.rightNode.rankDiff == 1 && deleteNodeParent.rightNode != WAVL_emptyNode){
						   deleteNodeParent.leftNode = WAVL_emptyNode;
						   size --;
						   return rebalancingCounter;
					   }
				   }else{ // deleteNode is right child
					   if(deleteNodeParent.leftNode.rankDiff == 1 && deleteNodeParent.leftNode != WAVL_emptyNode){
						   deleteNodeParent.rightNode = WAVL_emptyNode;
						   size --;
						   return rebalancingCounter;
					   }
				   }
			   }
			   
			   // case2 : parent's other child is external leaf and deleteNode is leaf
			   if(isLeaf(deleteNode)){
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   if(deleteNodeParent.rightNode == WAVL_emptyNode){
						   deleteNodeParent.leftNode = WAVL_emptyNode;
						   size --;
						   return demote(deleteNodeParent,rebalancingCounter);
					   }
				   }else{ // deleteNode is right child
					   if(deleteNodeParent.leftNode == WAVL_emptyNode){
						   deleteNodeParent.rightNode = WAVL_emptyNode;
						   size --;
						   return demote(deleteNodeParent,rebalancingCounter);
					   }
				   }
			   }
			   
			   // case 3: two leaves with rank difference two.
			   if(isLeaf(deleteNodeParent.leftNode) && isLeaf(deleteNodeParent.rightNode) 
					   && deleteNodeParent.rightNode.rankDiff == 2 && deleteNodeParent.leftNode.rankDiff == 2){
				   
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   deleteNodeParent.leftNode = WAVL_emptyNode;
					   size --;
					   deleteNodeParent.rightNode.rankDiff = 1;
					   return demote(deleteNodeParent,rebalancingCounter);
				   }else{ // deleteNode is right child
					   deleteNodeParent.rightNode = WAVL_emptyNode;
					   size --;
					   deleteNodeParent.leftNode.rankDiff = 1;
					   return demote(deleteNodeParent,rebalancingCounter);
				   }
			   }
				   
			   // case 4: one is a leaf and the other child's children both has rank differences of two.
			   if(isLeaf(deleteNode)){
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
						   deleteNodeParent.leftNode = WAVL_emptyNode;
						   size --;
						   // demoting right node's rank
						   deleteNodeParent.rightNode.leftNode.rankDiff = 1;
						   deleteNodeParent.rightNode.rightNode.rankDiff = 1;
						   rebalancingCounter ++;
						   return demote(deleteNodeParent, rebalancingCounter);
					   }
				   }else{ // deleteNode is right child
					   if(deleteNodeParent.leftNode.leftNode.rankDiff == 2 && deleteNodeParent.leftNode.rightNode.rankDiff ==2){
						   deleteNodeParent.rightNode = WAVL_emptyNode;
						   size --;
						   // demoting right node's rank
						   deleteNodeParent.leftNode.leftNode.rankDiff = 1;
						   deleteNodeParent.leftNode.rightNode.rankDiff = 1;
						   rebalancingCounter ++;
						   return demote(deleteNodeParent, rebalancingCounter);
					   }
				   }
			   }

			   // case 5: one is a leaf and the other child's external child has rank difference of one and the internal child has rank difference of one or two.
			   if(isLeaf(deleteNode)){
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   if(deleteNodeParent.rightNode.rightNode.rankDiff == 1 && deleteNodeParent.rightNode.rightNode != WAVL_emptyNode){
						   deleteNodeParent.leftNode = WAVL_emptyNode;
						   WAVLNode topNode = rotateLeft(deleteNodeParent);
						   topNode.rankDiff = topNode.leftNode.rankDiff;
						   topNode.leftNode.rankDiff = 1;
						   topNode.rightNode.rankDiff = 2;
						   size --;
						   return rebalancingCounter + 1; 
					   }
				   }else{ // deleteNode is right child
					   if(deleteNodeParent.leftNode.leftNode.rankDiff == 1 && deleteNodeParent.leftNode.leftNode != WAVL_emptyNode){
						   deleteNodeParent.rightNode = WAVL_emptyNode;
						   WAVLNode topNode = rotateRight(deleteNodeParent);
						   topNode.rankDiff = topNode.rightNode.rankDiff;
						   topNode.rightNode.rankDiff = 1;
						   topNode.leftNode.rankDiff = 2;
						   size --;
						   return rebalancingCounter + 1; 
					   }
				   }
			   }
			   
			   // case 6: one is a leaf and the other child's external child has rank difference of two or an external leaf and the internal child has rank difference of one.
			   
			   if(isLeaf(deleteNode)){
				   if(deleteNode.key < deleteNodeParent.key){ //is left child
					   if((deleteNodeParent.rightNode.rightNode.rankDiff == 2 || deleteNodeParent.rightNode.rightNode == WAVL_emptyNode)
							   && deleteNodeParent.rightNode.leftNode.rankDiff == 1){
						   
						   deleteNodeParent.leftNode = WAVL_emptyNode;
						   WAVLNode topNode = doubleRotateRightLeft(deleteNodeParent);
						   topNode.rankDiff = topNode.leftNode.rankDiff;
						   topNode.leftNode.rankDiff = 2;
						   topNode.rightNode.rankDiff = 2;
						   size --;
						   return rebalancingCounter + 2; 
					   }
				   }else{ // deleteNode is right child
					   if((deleteNodeParent.leftNode.leftNode.rankDiff == 2 || deleteNodeParent.leftNode.leftNode == WAVL_emptyNode)
							   && deleteNodeParent.leftNode.rightNode.rankDiff == 1){
						   
						   deleteNodeParent.rightNode = WAVL_emptyNode;
						   WAVLNode topNode = doubleRotateLeftRight(deleteNodeParent);
						   topNode.rankDiff = topNode.rightNode.rankDiff;
						   topNode.rightNode.rankDiff = 2;
						   topNode.leftNode.rankDiff = 2;
						   size --;
						   return rebalancingCounter + 2; 
					   }
				   }
			   }
			   
			   // case 7: deleteNode isn't a leaf and has one leaf child and one external leaf child. deleteNode has rank difference of one.
			   
			   if(!isLeaf(deleteNode) && deleteNode.rankDiff == 1){
				   if((isLeaf(deleteNode.leftNode) && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   deleteNodeParent.leftNode = deleteNode.leftNode;
						   size --;
						   return rebalancingCounter;
					   }else{ //is right child
						   deleteNodeParent.rightNode = deleteNode.leftNode;
						   size --;
						   return rebalancingCounter;
					   }
				   }else if ((isLeaf(deleteNode.rightNode) && deleteNode.leftNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   deleteNodeParent.leftNode = deleteNode.rightNode;
						   size --;
						   return rebalancingCounter;
					   }else{ //is right child
						   deleteNodeParent.rightNode = deleteNode.rightNode;
						   size --;
						   return rebalancingCounter;
					   }
				   }
			   }
			   
			   // case 8: deleteNode isn't a leaf and has one leaf child and one external leaf child. deleteNode has rank difference of two.
			   
			   if(!isLeaf(deleteNode) && deleteNode.rankDiff == 2){
				   if((isLeaf(deleteNode.leftNode) && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 2){
							   deleteNodeParent.leftNode = deleteNode.leftNode;
							   size --;
							   return demote(deleteNodeParent, rebalancingCounter);
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 2){
							   deleteNodeParent.rightNode = deleteNode.leftNode;
							   size --;
							   return demote(deleteNodeParent, rebalancingCounter);
						   }
					   }
				   }else if ((isLeaf(deleteNode.rightNode) && deleteNode.leftNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 2){
							   deleteNodeParent.leftNode = deleteNode.rightNode;
							   size --;
							   return demote(deleteNodeParent, rebalancingCounter);
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 2){
							   deleteNodeParent.rightNode = deleteNode.rightNode;
							   size --;
							   return demote(deleteNodeParent, rebalancingCounter);
						   }
					   }
				   }
			   }
			   
			   // case 9: one is a leaf and the other child's children both has rank differences of two.
			   if(!isLeaf(deleteNode) && deleteNode.rankDiff == 2){
				   if((isLeaf(deleteNode.leftNode) && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.leftNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   deleteNodeParent.rightNode.leftNode.rankDiff = 1;
								   deleteNodeParent.rightNode.rightNode.rankDiff = 1;
								   rebalancingCounter ++;
								   return demote(deleteNodeParent, rebalancingCounter);
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.rightNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   deleteNodeParent.leftNode.leftNode.rankDiff = 1;
								   deleteNodeParent.leftNode.rightNode.rankDiff = 1;
								   rebalancingCounter ++;
								   return demote(deleteNodeParent, rebalancingCounter);
							   }
						   }
					   }
				   }else if ((isLeaf(deleteNode.rightNode) && deleteNode.leftNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.leftNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   deleteNodeParent.rightNode.leftNode.rankDiff = 1;
								   deleteNodeParent.rightNode.rightNode.rankDiff = 1;
								   rebalancingCounter ++;
								   return demote(deleteNodeParent, rebalancingCounter);
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.rightNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   deleteNodeParent.leftNode.leftNode.rankDiff = 1;
								   deleteNodeParent.leftNode.rightNode.rankDiff = 1;
								   rebalancingCounter ++;
								   return demote(deleteNodeParent, rebalancingCounter);
							   }
						   }
					   }
				   }
			   }

			   // case 10: one is a leaf and the other child's external child has rank difference of one and the internal child has rank difference of one or two.
			   if(!isLeaf(deleteNode) && deleteNode.rankDiff == 2){
				   if((isLeaf(deleteNode.leftNode) && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.rightNode != WAVL_emptyNode && deleteNodeParent.rightNode.rightNode.rankDiff ==1){
								   deleteNodeParent.leftNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = rotateLeft(deleteNodeParent);
								   topNode.rankDiff = topNode.leftNode.rankDiff;
								   topNode.leftNode.rankDiff = 1;
								   topNode.rightNode.rankDiff = 2;
								   topNode.leftNode.leftNode.rankDiff = 1;
								   return rebalancingCounter + 1; 
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.rightNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = rotateRight(deleteNodeParent);
								   topNode.rankDiff = topNode.rightNode.rankDiff;
								   topNode.rightNode.rankDiff = 1;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rightNode.rankDiff = 1;
								   return rebalancingCounter + 1; 
							   }
						   }
					   }
				   }else if ((isLeaf(deleteNode.rightNode) && deleteNode.leftNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.leftNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = rotateLeft(deleteNodeParent);
								   topNode.rankDiff = topNode.leftNode.rankDiff;
								   topNode.leftNode.rankDiff = 1;
								   topNode.rightNode.rankDiff = 2;
								   topNode.leftNode.leftNode.rankDiff = 1;
								   return rebalancingCounter + 1; 
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.leftNode.rankDiff == 2 && deleteNodeParent.rightNode.rightNode.rankDiff ==2){
								   deleteNodeParent.rightNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = rotateRight(deleteNodeParent);
								   topNode.rankDiff = topNode.rightNode.rankDiff;
								   topNode.rightNode.rankDiff = 1;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rightNode.rankDiff = 1;
								   return rebalancingCounter + 1; 
							   }
						   }
					   }
				   }
			   }
			  
			   
			   // case 11: one is a leaf and the other child's external child has rank difference of two or an external leaf and the internal child has rank difference of one.
			   
			   if(!isLeaf(deleteNode) && deleteNode.rankDiff == 2){
				   if((isLeaf(deleteNode.leftNode) && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.rightNode.rankDiff == 2 && deleteNodeParent.rightNode.leftNode.rankDiff == 1){
								   deleteNodeParent.leftNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = doubleRotateRightLeft(deleteNodeParent);
								   topNode.rankDiff = topNode.leftNode.rankDiff;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rankDiff = 2;
								   return rebalancingCounter + 2;  
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.leftNode.rightNode.rankDiff == 2 && deleteNodeParent.leftNode.leftNode.rankDiff == 1){
								   deleteNodeParent.rightNode = deleteNode.leftNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = doubleRotateLeftRight(deleteNodeParent);
								   topNode.rankDiff = topNode.rightNode.rankDiff;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rankDiff = 2;
								   return rebalancingCounter + 2;  
							   }
						   }
					   }
				   }else if ((isLeaf(deleteNode.rightNode) && deleteNode.leftNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   if(deleteNodeParent.rightNode.rankDiff == 1){
							   if(deleteNodeParent.rightNode.rightNode.rankDiff == 2 && deleteNodeParent.rightNode.leftNode.rankDiff == 1){
								   deleteNodeParent.leftNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = doubleRotateRightLeft(deleteNodeParent);
								   topNode.rankDiff = topNode.leftNode.rankDiff;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rankDiff = 2;
								   return rebalancingCounter + 2; 
							   }
						   }
					   }else{ //is right child
						   if(deleteNodeParent.leftNode.rankDiff == 1){
							   if(deleteNodeParent.leftNode.rightNode.rankDiff == 2 && deleteNodeParent.leftNode.leftNode.rankDiff == 1){
								   deleteNodeParent.rightNode = deleteNode.rightNode;
								   size --;
								   // demoting right node's rank
								   WAVLNode topNode = doubleRotateLeftRight(deleteNodeParent);
								   topNode.rankDiff = topNode.rightNode.rankDiff;
								   topNode.leftNode.rankDiff = 2;
								   topNode.rightNode.rankDiff = 2;
								   return rebalancingCounter + 2;   
							   }
						   }
					   }
				   }
			   }
			   
			 			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   // deleteNode is a left son.
			   if(deleteNode.key < deleteNodeParent.key){
				   // deleteNode is a leaf and only child
				   if(isLeaf(deleteNode) && (deleteNodeParent.rightNode == WAVL_emptyNode)){
					   deleteNodeParent.leftNode = WAVL_emptyNode;
					   size --;
					   rebalancingCounter += demote(deleteNodeParent, rebalancingCounter);
				   }
			   }else{ // deleteNode is a right son.
				   // deleteNode is a leaf and only child
				   if(isLeaf(deleteNode) && (deleteNodeParent.leftNode == WAVL_emptyNode)){
					   deleteNodeParent.rightNode = WAVL_emptyNode;
					   size --;
					   rebalancingCounter += demote(deleteNodeParent, rebalancingCounter);
				   }
			   }
		   }
	   }
	   
	   return rebalancingCounter;
   
   }
   /** 
    * 
    * @param node
    * @return if the node is a leaf.
    */
   public boolean isLeaf(WAVLNode node){
	   return node.rightNode == node.leftNode;
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
      int counter = 0;
      while(counter < size)
      {
	        arr[counter]=WAVL_tempNode.key;
	        counter++;
	        
	        WAVL_tempNode = getSuccessor(WAVL_tempNode);
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
        int counter = 0; 
        while(counter < size)
        {
	        arr[counter]=WAVL_tempNode.info;
	        counter++;
	        
	        WAVL_tempNode = getSuccessor(WAVL_tempNode);
  		}
        return arr;                    
  }
  
  /**
   * private WAVLNode getSmallestNode
   * 
   * Returns pointer of the node with the smallest key in the tree
   * 
   * or null if the tree is empty.
   */
  
  private WAVLNode getSmallestNode()
  {
	  if(empty())
	  {
		 return null; 
	  }else{
		 WAVLNode WAVL_tempNode = WAVL_root;
		 while(WAVL_tempNode.leftNode!=WAVL_emptyNode)
		 {
			 WAVL_tempNode=WAVL_tempNode.leftNode;
		 }
		 return WAVL_tempNode;
	  }
  }
  
  /**
   * private WAVLNode getSuccessor(WAVLNode WAVL_Node)
   * 
   * Returns pointer of the first node with a bigger key then WAVL_Node 
   */
  
  private WAVLNode getSuccessor(WAVLNode WAVL_Node)
  {
	  if(WAVL_Node.rightNode!=WAVL_emptyNode)
      {
		  WAVL_Node=WAVL_Node.rightNode;
      	while(WAVL_Node.leftNode!=WAVL_emptyNode)
      	{
      		WAVL_Node=WAVL_Node.leftNode;
      	}
      }else if(WAVL_Node.parentNode!=null){
    	  WAVL_Node=WAVL_Node.parentNode;
      }
	  return WAVL_Node;
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
    * private int promote(WAVLNode WAVL_Node)
    *
    * promote the node.
    * 
    * return number of rebalance.
    * 
    * precondition: none
    * postcondition: none
    */
   private int promote(WAVLNode node)
   {
	   int rebalanceCounter = 0;
	   while(node != WAVL_root){ // "because in the end it doesn't even matter"
		   if(node.rankDiff == 2)
		   {
			   node.rankDiff = 1;
			   return rebalanceCounter + 1;
		   }else{
			   if(node==node.parentNode.leftNode) // is the left node
			   {
				   if((node.parentNode.rightNode.rankDiff == 1) && (node.parentNode.rightNode != WAVL_emptyNode))
				   {
					   node.parentNode.rightNode.rankDiff = 2;
					   node = node.parentNode;
					   rebalanceCounter++;
				   }else{//need to be rotated
					   if((node.rightNode.rankDiff == 2) || (node.rightNode == WAVL_emptyNode))//need single rotation
					   {
						   rotateRight(node.parentNode); // WAVL_Node became the parent now
						   rebalanceCounter++;
						   
						   //fix ranks:
						   node.rankDiff = node.rightNode.rankDiff;
						   node.rightNode.rankDiff = 1;
						   node.rightNode.rightNode.rankDiff = 1;
						   node.leftNode.rightNode.rankDiff = 1;
						   
						   return rebalanceCounter;
					   }else{//need double rotation
						   node = doubleRotateLeftRight(node.parentNode); // WAVL_Node.rightNode became the parent now
						   rebalanceCounter++;
						   
						   //fix ranks:
						   node.rankDiff = node.rightNode.rankDiff;
						   node.rightNode.rankDiff = 1;
						   node.rightNode.rightNode.rankDiff = 1;
						   node.leftNode.leftNode.rankDiff = 1;
						   
						   return rebalanceCounter;
					   }
				   }
			   }else{ // is the right node
				   if((node.parentNode.leftNode.rankDiff == 1) && (node.parentNode.leftNode != WAVL_emptyNode))
				   {
					   node.parentNode.leftNode.rankDiff = 2;
					   node = node.parentNode;
					   rebalanceCounter++;
				   }else{//need to be rotated
					   if((node.leftNode.rankDiff == 2) || (node.leftNode == WAVL_emptyNode))//need single rotation
					   {
						   rotateLeft(node.parentNode); // WAVL_Node became the parent now
						   rebalanceCounter++;
						   
						   //fix ranks:
						   node.rankDiff = node.leftNode.rankDiff;
						   node.leftNode.rankDiff = 1;
						   node.leftNode.leftNode.rankDiff = 1;
						   node.rightNode.leftNode.rankDiff = 1;
						   
						   return rebalanceCounter;
					   }else{//need double rotation
						   node = doubleRotateRightLeft(node.parentNode); // WAVL_Node.leftNode became the parent now
						   rebalanceCounter++;
						   
						   //fix ranks:
						   node.rankDiff = node.leftNode.rankDiff;
						   node.leftNode.rankDiff = 1;
						   node.leftNode.leftNode.rankDiff = 1;
						   node.rightNode.rightNode.rankDiff = 1;
						   
						   return rebalanceCounter;
					   }
				   }
			   }
			   return rebalanceCounter; 
		   }
	   }
   }
   
   /**
    * private static void demote(WAVLNode node)
    *
    * demotes the node.
    * returns how many rebalancing actions were taken.
    * precondition: none
    * postcondition: none
    */
   private int demote(WAVLNode node , int rebalancingCounter)
   {
	   // checking if there's a need for further demotes or for rotations.
	   // if node is a leaf
	   // TODO 
	   if (isLeaf(node)){
		   node.rankDiff += 1;
		   rebalancingCounter += 1;
		   if(node.rankDiff == 2){
			   return rebalancingCounter;
		   }else{ // need to further demote
			   
		   }
	   }
	   if(node.rankDiff == 2){
		   // parent has no sons
		   if(node.leftNode)
		   return rebalancingCounter;
	   }else if(node.rankDiff ==3){
		   
		   
	   }
	   
	   
	   
	   return 0;
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
	  WAVLNode parentNode = null;
	  WAVLNode leftNode = null;
	  WAVLNode rightNode = null;
	  int rankDiff; 
	  int key;
	  String info;  
	  
	  public WAVLNode()
	  {
		  parentNode = null;
		  rightNode = null;
		  leftNode = null;
	  }
	  
	  public WAVLNode(WAVLNode parentnode, WAVLNode leftnode,
			  WAVLNode rightnode, int rankdiff, int node_key, String node_info  )
	  {
		  parentNode = parentnode;
		  rightNode = rightnode;
		  leftNode = leftnode;
		  rankDiff = rankdiff;
		  key=node_key;
		  info=node_info;
	  }
  }

  
  // for testing:
  
	public boolean TESTisThisTreeOk()
	{
		if(!empty())
		{
			 WAVLNode WAVL_tempNode = getSmallestNode();
			 WAVLNode WAVL_tempNodeSuccessor = getSuccessor(WAVL_tempNode);
			 
		}else{
			return true;
		}
	}
  
  
  
  
}
  

