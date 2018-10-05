package sortProcessor;
public class Quick_2D_Sort{
	public int[] sort(int [] a) {
		int v=(int) Math.log(a.length);
		for(int i=0;i<v*3;i++)
			sort(a,0,a.length-1);	
		return a;	
	}

	private void sort(int[] a, int l, int r) {
		int m=(l+r)/2;
		quick(a,l,r);
		if(m>l)
		{
			sort(a,l,m);
			sort(a,m,r);
		}
	}

	private void quick(int[] a, int l, int r) {
		while(l<r){	
			if(a[l]>a[r]){
				int temp=a[l];
				a[l]=a[r];
				a[r]=temp;  
			}
			l++;
			r--;
		}	
	}
}