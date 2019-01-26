package sortProcessor;
public class TTreeSort{
	public Leaf root;
	public Leaf heap;
	int c;
	int a1[];
	public int[] sort(int [] a) {
		//make tree
		c=0;
		a1=new int[a.length];
		if(root == null){
			root=new Leaf();
			root.value[0]=a[0];
			root.hasO1=1;
		}
		for(int i=1;i<a.length;i++){
			heap=root;
			addleaf(a[i]);
		}
		check(root);
		return a1;
	}

	public Leaf root(int[] a) {
		if(root == null){
			root = new Leaf();
			root.value[0] = a[0];
			root.hasO1 = 1;
		}
		for(int i=1;i<a.length;i++)
		{
			heap=root;
			addleaf(a[i]);
		}

		// TODO Auto-generated method stub
		return root;
	}
	
	private void check(Leaf temp) {
		// TODO Auto-generated method stub
		if(temp!=null){
			check(temp.O1);
			if(temp.hasO1==1){
				a1[c]=temp.value[0];
				c+=1;
			}
			check(temp.O2);
			if(temp.hasO2==1)
			{
				a1[c]=temp.value[1];
				c+=1;
			}
			check(temp.O3);
		}	
	}
	
	private void addleaf(int i) {
		if(heap.hasO1==1&&heap.hasO2==0){
			if(heap.value[0]<=i){
				heap.value[1]=i;
				heap.hasO2=1;
			}else{
				heap.value[1]=heap.value[0];
				heap.value[0]=i;
				heap.hasO2=1;
			}
			return;		
		}else{
			if(i<=heap.value[0]){
				if(heap.O1==null){
					heap.O1=new Leaf();
					heap=heap.O1;
					heap.value[0]=i;
					heap.hasO1=1;
					return;
				}else{
					heap=heap.O1;
					addleaf(i);
				}
			}
			else if(i>heap.value[0] && i<=heap.value[1]){
				if(heap.O2==null){
					heap.O2=new Leaf();
					heap=heap.O2;
					heap.value[0]=i;
					heap.hasO1=1;
					return;
				}else{
					heap=heap.O2;
					addleaf(i);
				}	
			} else{
				if(heap.O3==null){
					heap.O3=new Leaf();
					heap=heap.O3;
					heap.value[0]=i;
					heap.hasO1=1;
					return;
				}else{
					heap=heap.O3;
					addleaf(i);
				}	
			}	 
		}
	}
}