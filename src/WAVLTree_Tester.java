
import java.util.Random;

public class WAVLTree_Tester {
	
	public static void main(String[] args) {
		WAVLTree tree = new WAVLTree();
		Random random = new Random();
		int i = 0;
		int i1,i2;
		while(i<20)
		{
			i++;
			i1=tree.insert(i2=random.nextInt(20000), "lol");
			if(!tree.TESTisThisTreeOk())
			{
				System.out.println("l12o21l");
				break;
			}
		}
		WAVLTree.WAVLNode node;
		node = tree.getSmallestNode();
		i=0;
		while(i<19){
			i++;
			System.out.print(node.key + " ");
			node = node.getSuccessor(node);

		}
		i=0;
		System.out.print("\n");
		while(node!=null){
			i++;
			System.out.print(node.key + " ");
			node = node.getPredecessor(node);

		}
		System.out.print("\n");
		System.out.println("l1o1l" + tree.size());
	}
}