package sortProcessor;
public class BTreeSort
{
	public Leaf root;
	public Leaf heap;
	int c;
	int a1[];
	public int[] sort(int [] a) {
		//make tree
		c=0;
		a1=new int[a.length];
		if(root==null){
			root=new Leaf();
			root.value[0]=a[0];
			root.hasO1=1;
		}
		for(int i=1;i<a.length;i++){
			heap=root;
			addleaf(a[i]);
		}
		heap=root;
		check(heap);
		return a1;
	}
	private void check(Leaf temp) {
		// TODO Auto-generated method stub
		if(temp!=null){
			check(temp.O1);
			a1[c]=temp.value[0];
			c+=1;
			check(temp.O2);
		}	
	}
	private void addleaf(int i) {
		if(i<=heap.value[0]){
			if(heap.O1==null){
				heap.O1=new Leaf();
				heap=heap.O1;
				heap.value[0]=i;
				root.hasO1=1;
				return;
			}
			else{
				heap=heap.O1;
				addleaf(i);
			}
		}
		else{
			if(heap.O2==null){
				heap.O2=new Leaf();
				heap=heap.O2;
				heap.value[0]=i;
				root.hasO1=1;
				return;
			}else{
				heap=heap.O2;
				addleaf(i);
			}	
		}	 
	}
	public Leaf root(int[] a) {
		if(root==null){
			root=new Leaf();
			root.value[0]=a[0];
			root.hasO1=1;
		}for(int i=1;i<a.length;i++){
			heap=root;
			addleaf(a[i]);
		}
		// TODO Auto-generated method stub
		return root;
	}
}