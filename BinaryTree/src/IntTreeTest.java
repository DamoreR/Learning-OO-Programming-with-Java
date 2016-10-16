
public class IntTreeTest {

	public static void main(String[] args) {
		IntTree t1 = new IntTree();
		IntTree t2 = new IntTree(1);
		t1.printOut();
		t1 = t1.evenLevels(t1);
		t1.printOut();
	}

}
