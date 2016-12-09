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
   * Returns the info of the item with the smallest key in the tree,
   * or null if the tree is empty
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
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
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
  
  /**
   * private  WAVLNode searchNode(int k)
   *
   * returns the node of an key k if it exists in the tree
   * otherwise, returns null
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
   * returns the node of an key k or where k should be added.
   * otherwise, returns null
   */
  private WAVLNode searchInsertionPlace(int k)
  {
	  if(empty()){
		  return null;
	  }else{
		  WAVLNode WAVL_tempNode = WAVL_root;
		  while (WAVL_tempNode.key != k)
		  {
			  if(WAVL_tempNode.key>k)
			  {
				  if(WAVL_tempNode.leftNode.isExternalLeaf())
				  {
					  return WAVL_tempNode;
				  }else{
					  WAVL_tempNode=WAVL_tempNode.leftNode;
				  }
			  }else{
				  if(WAVL_tempNode.rightNode.isExternalLeaf())
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
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
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
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
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
   * inserts an item with key k and info i to the WAVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   */
   public int insert(int k, String i) {
	   if(empty()){											//if empty: Start a new tree.
		   startNewRoot(k, i);return 0;}					
	   WAVLNode parent = searchInsertionPlace(k); 			//finds desired insertion place.
	   if (parent.key == k){								//if already exist: return -1.
		   return -1;}										
	   return addNewLeaf(parent, k, i);						//add new leaf.
   }

/**
    * private void private WAVLNode addNewLeaf(WAVLNode parent, int k, String i)
    *
    * create a new leaf node with key k and info i under parent.
    * 
    * return # of rebalances.
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
		  return 0;}
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
	   WAVLNode partnerNode, innerNode;
	   while(node != WAVL_root){ 										// "because in the end it doesn't even matter"
		   if(node.rankDiff == 2){										
			   node.rankDiff = 1;
			   return rebalanceCounter + 1;}							//fixed problem!
		   
		   partnerNode=node.getPartner();
		   
		   if((partnerNode.rankDiff == 1) 
				   && ( ! partnerNode.isExternalLeaf())){				
			   partnerNode.rankDiff = 2;
			   node = node.parentNode; 									// need to promote parent node.	
			   rebalanceCounter++;
		   }else{														// need to be rotated
			   if(node.isLeftNode()){					
				   innerNode=node.rightNode;							//get inner node.
			   }else{			
				   innerNode=node.leftNode;
			   }
			   if((innerNode.rankDiff == 2) 
					   || (innerNode.isExternalLeaf())){		// need single rotation
				   rebalanceCounter++;										
				   if(node.isLeftNode()){							//node became the parent.
					   rotateRight(node.parentNode);  				
					   fixRanksAfterRotate(node,node.rightNode);
				   }else{
					   rotateLeft(node.parentNode);  				
					   fixRanksAfterRotate(node,node.leftNode);
				   }			
				   return rebalanceCounter;							//problem solved!
				   
			   }else{											// need double rotation
				   rebalanceCounter+= 2;										
				   if(node.isLeftNode()){
					   node = doubleRotateLeftRight(node.parentNode);
					   fixRanksAfterDoubleRotate(node,node.rightNode);
				   }else{
					   node = doubleRotateRightLeft(node.parentNode);
					   fixRanksAfterDoubleRotate(node,node.leftNode);
				   }			
				   return rebalanceCounter;							//problem solved!
			   }
		   }
	   }
	   return rebalanceCounter; 
   }

	/**
	 * private void fixRanksAfterRotate(WAVLNode newParent, WAVLNode lastParent)
	 *
	 * fix ranks after rotate.
	 * 
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
    * fix ranks after double rotate.
    * 
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
    * create a new root node with key k and info i
    * 
    */
   
   private void startNewRoot(int k, String i){
	   WAVL_root = new WAVLNode(null, WAVL_emptyNode, WAVL_emptyNode, 1, k ,i);
	   size = 1;
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
			   if(deleteNode.isLeaf() && deleteNode.rankDiff == 1){
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
			   if(deleteNode.isLeaf()){
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
			   if(deleteNodeParent.leftNode.isLeaf() && deleteNodeParent.rightNode.isLeaf()
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
			   if(deleteNode.isLeaf()){
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
			   if(deleteNode.isLeaf()){
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
			   
			   if(deleteNode.isLeaf()){
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
			   
			   if(!deleteNode.isLeaf() && deleteNode.rankDiff == 1){
				   if((deleteNode.leftNode.isLeaf() && deleteNode.rightNode == WAVL_emptyNode)){
					   if(deleteNode.key < deleteNodeParent.key){ //is left child
						   deleteNodeParent.leftNode = deleteNode.leftNode;
						   size --;
						   return rebalancingCounter;
					   }else{ //is right child
						   deleteNodeParent.rightNode = deleteNode.leftNode;
						   size --;
						   return rebalancingCounter;
					   }
				   }else if ((deleteNode.rightNode.isLeaf() && deleteNode.leftNode == WAVL_emptyNode)){
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
			   
			   if(!deleteNode.isLeaf() && deleteNode.rankDiff == 2){
				   if((deleteNode.leftNode.isLeaf() && deleteNode.rightNode == WAVL_emptyNode)){
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
				   }else if ((deleteNode.rightNode.isLeaf() && deleteNode.leftNode == WAVL_emptyNode)){
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
			   if(!deleteNode.isLeaf() && deleteNode.rankDiff == 2){
				   if((deleteNode.leftNode.isLeaf() && deleteNode.rightNode == WAVL_emptyNode)){
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
				   }else if ((deleteNode.rightNode.isLeaf() && deleteNode.leftNode == WAVL_emptyNode)){
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
			   if(!deleteNode.isLeaf() && deleteNode.rankDiff == 2){
				   if((deleteNode.leftNode.isLeaf() && deleteNode.rightNode == WAVL_emptyNode)){
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
				   }else if ((deleteNode.rightNode.isLeaf() && deleteNode.leftNode == WAVL_emptyNode)){
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
			   
			   if(!deleteNode.isLeaf() && deleteNode.rankDiff == 2){
				   if((deleteNode.leftNode.isLeaf() && deleteNode.rightNode == WAVL_emptyNode)){
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
				   }else if ((deleteNode.rightNode.isLeaf() && deleteNode.leftNode == WAVL_emptyNode)){
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
				   if(deleteNode.isLeaf() && (deleteNodeParent.rightNode == WAVL_emptyNode)){
					   deleteNodeParent.leftNode = WAVL_emptyNode;
					   size --;
					   rebalancingCounter += demote(deleteNodeParent, rebalancingCounter);
				   }
			   }else{ // deleteNode is a right son.
				   // deleteNode is a leaf and only child
				   if(deleteNode.isLeaf() && (deleteNodeParent.leftNode == WAVL_emptyNode)){
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
	   if (node.isLeaf()){
		   node.rankDiff += 1;
		   rebalancingCounter += 1;
		   if(node.rankDiff == 2){
			   return rebalancingCounter;
		   }else{ // need to further demote
			   
		   }
	   }
	   if(node.rankDiff == 2){
		   // parent has no sons
		   //if(node.leftNode)
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
    * performs a right rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
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
    * performs a left rotation and then right rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private WAVLNode doubleRotateLeftRight(WAVLNode node)
   {
	   node.leftNode = rotateLeft(node.leftNode);
	   return rotateRight(node);
   }
   
   /**
    * private static WAVLNode doubleRotateRightLeft(WAVLNode node)
    *
    * performs a right rotation and then left rotation of the tree.
    *
    * precondition: receives the node which needs to be rotated down.
    * postcondition: none
    */
   private WAVLNode doubleRotateRightLeft(WAVLNode node)
   {
	   rotateRight(node.rightNode);
	   return rotateLeft(node);
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
	  int rankDiff; 
	  int key;
	  String info;  
	  
	  public WAVLNode(){
		  parentNode = null;
		  leftNode = WAVL_emptyNode;
		  rightNode = WAVL_emptyNode;
		  rankDiff = 1;
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
	  
	  /**
	  * public boolean isLeaf()
	  * 
	  * return true if Node has no children.
	  */
	  public boolean isLeaf()
	  {
		  return leftNode==rightNode;
	  }
	  
	  /** public boolean isExternalLeaf()
	  * 
	  * return true if Node is an external leaf.
	  */
	  public boolean isExternalLeaf()
	  {
		  return this==WAVL_emptyNode;
	  }
	  
	  /** public boolean isLeftNode()
	  * 
	  * return partner node if exist.
	  * 
	  * precondition: is not the root.
	  */
	  public boolean isLeftNode()
	  {
			  if(this==this.parentNode.leftNode){ 			//Node is a left node.
				  return true;
			  }else{										//Node is a right node.
				  return false;}
	  }
	  
	  /** public WAVLNode getPartner()
	  * 
	  * return partner node if exist.
	  */
	  public WAVLNode getPartner()
	  {
		  if(this.parentNode!=null){
			  if(this.isLeftNode()){ 						//Node is a left node.
				  return this.parentNode.rightNode;
			  }else{										//Node is a right node.
				  return this.parentNode.leftNode;}
		  }
		  return null;
	  }
	  
	  /**
	   * private WAVLNode getSuccessor(WAVLNode WAVL_Node)
	   * 
	   * Returns pointer of the first node with a bigger key then WAVL_Node 
	   */
	  public WAVLNode getSuccessor(){
		  WAVLNode WAVL_Node = this;
		  if(!WAVL_Node.rightNode.isExternalLeaf())
	      {
			WAVL_Node=WAVL_Node.rightNode;
	      	while(!WAVL_Node.leftNode.isExternalLeaf()){
	      		WAVL_Node=WAVL_Node.leftNode;}
	      }else{
	    	  while(WAVL_Node.parentNode!=null 
	    			  && WAVL_Node == WAVL_Node.parentNode.rightNode){
	    		  WAVL_Node=WAVL_Node.parentNode;}
			  if(WAVL_Node.parentNode!=null){
				  WAVL_Node=WAVL_Node.parentNode;
			  }else{
				  return null;}
	      }
		  return WAVL_Node;
	  }
	  
	  /**
	  * private WAVLNode getPredecessor(WAVLNode WAVL_Node)
	  * 
	  * Returns pointer of the first node with a bigger key then WAVL_Node 
	  */
	  public WAVLNode getPredecessor(){
		  WAVLNode WAVL_Node = this;
		  if(!WAVL_Node.leftNode.isExternalLeaf()){
			  WAVL_Node=WAVL_Node.leftNode;
	      	  while(!WAVL_Node.rightNode.isExternalLeaf()){
	      		WAVL_Node=WAVL_Node.rightNode;}
	      }else{
	    	  while(WAVL_Node.parentNode!=null 
	    			  && WAVL_Node == WAVL_Node.parentNode.leftNode){
	    		  WAVL_Node=WAVL_Node.parentNode;}
	    	  
			  if(WAVL_Node.parentNode!=null){
				  WAVL_Node=WAVL_Node.parentNode;
			  }else{
				  return null;}
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
			 boolean passedThrouthALeaf = false;
			 int rank = 0;
			 do{
				 if (WAVL_tempNode.isLeaf())
				 {
					 if(passedThrouthALeaf)
					 {
						 if(rank!=0){
							 return false;
						 }
					 }else{
						 passedThrouthALeaf=true;
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
  

