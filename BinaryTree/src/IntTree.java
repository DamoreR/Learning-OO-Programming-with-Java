public class IntTree{
	private IntTreeNode overallRoot;
	
	
	public IntTree(){
		overallRoot = new IntTreeNode(3, null, null);
		overallRoot.left = new IntTreeNode(2, new IntTreeNode(1, null, null), null);
		overallRoot.right = new IntTreeNode(4, null, new IntTreeNode(7, new IntTreeNode(5, null, new IntTreeNode(6, null, null)), null));

	}
	public IntTree(int num){
		overallRoot = new IntTreeNode(3, null, null);
		overallRoot.left = new IntTreeNode(2, new IntTreeNode(1, null, null), null);
		overallRoot.right = new IntTreeNode(4, null, new IntTreeNode(6, new IntTreeNode(5, null, null), null));
	}
	
	public void printOut(){
		if(this.overallRoot!=null)
			printHelp(this.overallRoot);
	}
	private void printHelp(IntTreeNode node){
		if(node.left!=null)
			printHelp(node.left);
		System.out.println(node.data);
		if(node.right!=null)
			printHelp(node.right);
	}
	
	public int matches(IntTree t2){
		if(this.overallRoot!=null&&t2.overallRoot!=null)
			return countMatches(this.overallRoot, t2.overallRoot);
		return 0;
	}
	
	private int countMatches(IntTreeNode n1, IntTreeNode n2){
		int count = 0;
		if(n1.data==n2.data)
			count += 1;
		if(n1.left!=null&&n2.left!=null)
			count += countMatches(n1.left, n2.left);
		if(n1.right!=null&&n2.right!=null)
			count += countMatches(n1.right, n2.right);
		return count;
	}
	
	public int multiples(int num) throws IllegalArgumentException{
		if(num<=0)
			throw new IllegalArgumentException();
		if(overallRoot!=null)
			return countMultiples(overallRoot, num);
		return 0;
	}
	private int countMultiples(IntTreeNode node, int num){
		int count = 0;
		if(node.data%num==0)
			count += 1;
		if(node.left!=null)
			count += countMultiples(node.left, num);
		if(node.right!=null)
			count += countMultiples(node.right, num);
		return count;
	}
	
	public IntTree evenLevels(IntTree t){
		overallRoot = removeOddLeaves(overallRoot, 1);
		return t;
	}
	private IntTreeNode removeOddLeaves(IntTreeNode node, int level){
		if(node==null){
			return null;
		}else{
			if(level%2==1){
				if(node.left==null&&node.right==null)
					return null;
			}
			if(node.left!=null)
				node.left = removeOddLeaves(node.left, level+1);
			if(node.right!=null)
				node.right = removeOddLeaves(node.right, level+1);
			return node;
		}
	}
	public void construct(int[] a){
		overallRoot = constructHelp(a, 0, a.length-1);
	}
	private IntTreeNode constructHelp(int[] a, int start, int stop){
		if(start>stop)
			return null;
		else{
			int mid = (start+stop+1)/2;
			return new IntTreeNode(a[mid], constructHelp(a, start, mid-1), constructHelp(a, mid+1, stop));
		}
	}
	private class IntTreeNode{
		public int data;
		public IntTreeNode left, right;
		public IntTreeNode(int data, IntTreeNode left, IntTreeNode right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}