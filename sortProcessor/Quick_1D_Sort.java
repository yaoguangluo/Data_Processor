package sortProcessor;
public class Quick_1D_Sort{
	public int[] sort(int [] a) {	
		int lp=0;
		int rp=a.length-1;
		while(lp<rp)
			quick(a,lp++,rp);
		return a;
	}

	private void quick(int[] a, int lp, int rp) {
		// TODO Auto-generated method st
		while(lp<rp){
			if(a[lp]>a[rp]){
				int temp=a[lp];
				a[lp]=a[rp];
				a[rp]=temp;
			}
			rp-=1;
		}
	}
}