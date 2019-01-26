package sortProcessor;
public class OTreeSort{
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
			if(temp.hasO2==1){
				a1[c]=temp.value[1];
				c+=1;
			}
			check(temp.O3);
			if(temp.hasO3==1){
				a1[c]=temp.value[2];
				c+=1;
			}
			check(temp.O4);
			if(temp.hasO4==1){
				a1[c]=temp.value[3];
				c+=1;
			}
			check(temp.O5);
			if(temp.hasO5==1){
				a1[c]=temp.value[4];
				c+=1;
			}
			check(temp.O6);
			if(temp.hasO6==1){
				a1[c]=temp.value[5];
				c+=1;
			}
			check(temp.O7);
			if(temp.hasO7==1){
				a1[c]=temp.value[6];
				c+=1;
			}
			check(temp.O8);
		}	
	}
	
	private void addleaf(int i){
		int count=heap.hasO1+heap.hasO2+heap.hasO3+heap.hasO4+heap.hasO5+heap.hasO6+heap.hasO7;
		if(count<7){
			if(count==1){
				heap.hasO2=1;
			}
			if(count==2){
				heap.hasO3=1;
			}
			if(count==3){
				heap.hasO4=1;
			}
			if(count==4){
				heap.hasO5=1;
			}
			if(count==5){
				heap.hasO6=1;
			}
			if(count==6){
				heap.hasO7=1;
			}
			heap.value[count]=i;
			heap.value=new InsertionSort().sort(heap.value,count+1);
			return;		
		}else{
			if(i<=heap.value[0]){
				if(heap.O1==null){
					heap.O1=new Leaf();
					heap.O1.value[0]=i;
					heap.O1.hasO1=1;
					return;
				}else{
					heap=heap.O1;
					addleaf(i);
				}
			}
			else if(i>heap.value[0] && i<=heap.value[1]){
				if(heap.O2==null){
					heap.O2=new Leaf();
					heap.O2.value[0]=i;
					heap.O2.hasO1=1;
					return;
				}else{
					heap=heap.O2;
					addleaf(i);
				}
			}	 
			else if(i>heap.value[1] && i<=heap.value[2]){
				if(heap.O3==null){
					heap.O3=new Leaf();
					heap.O3.value[0]=i;
					heap.O3.hasO1=1;
					return;
				}else{
					heap=heap.O3;
					addleaf(i);
				}
			}	
			else if(i>heap.value[2] && i<=heap.value[3]){
				if(heap.O4==null){
					heap.O4=new Leaf();
					heap.O4.value[0]=i;
					heap.O4.hasO1=1;
					return;
				}else{
					heap=heap.O4;
					addleaf(i);
				}
			}
			else if(i>heap.value[3] && i<=heap.value[4]){
				if(heap.O5==null){
					heap.O5=new Leaf();
					heap.O5.value[0]=i;
					heap.O5.hasO1=1;
					return;
				}else{
					heap=heap.O5;
					addleaf(i);
				}
			}
			else if(i>heap.value[4] && i<=heap.value[5]){
				if(heap.O6==null){
					heap.O6=new Leaf();
					heap.O6.value[0]=i;
					heap.O6.hasO1=1;
					return;
				}else{
					heap=heap.O6;
					addleaf(i);
				}

			}
			else if(i>heap.value[5] && i<=heap.value[6]){
				if(heap.O7==null){
					heap.O7=new Leaf();
					heap.O7.value[0]=i;
					heap.O7.hasO1=1;
					return;
				}else{
					heap=heap.O7;
					addleaf(i);
				}
			}
			else{
				if(heap.O8==null){
					heap.O8=new Leaf();
					heap.O8.value[0]=i;
					heap.O8.hasO1=1;
					return;
				}else{
					heap=heap.O8;
					addleaf(i);
				}	
			}
		}
	}
}