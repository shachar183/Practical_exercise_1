
import java.util.Random;

public class WAVLTree_Tester {
	
	public static void main(String[] args) {
		WAVLTree tree = new WAVLTree();
		Random random = new Random();
		int i = 0;
		int i1,i2;
		while(i<10000)
		{
			i++;
			i1=tree.insert(i2=random.nextInt(200000000), "lol");
			if(!tree.TESTisThisTreeOk())
			{
				System.out.println("l12o21l");
				break;
			}
		}
		System.out.println("l1o1l" + tree.size());
	}
}
