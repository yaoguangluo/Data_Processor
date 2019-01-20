package sortProcessor;
public class Quick_6D_luoyaoguang_Sort{
	public int[] sort(int[] a) {
		quick2ds(a, 0, a.length-1);
		return a;
	}

	private void quick2ds(int[] a, int lp, int rp) {
		if(lp<rp){int c=rp-lp+1;if(c<7){int j;
		for(int i=1+lp;i<lp+c;i++){j=i;while(j>=1+lp){
			if(a[j]<a[j-1]){int temp=a[j];a[j]=a[j-1];a[j-1]=temp;}
			j--;}}	return;}int pos = partition(a, lp, rp);
			quick2ds(a,lp,pos-1);quick2ds(a,pos+1,rp);
		}
	}

	private int partition(int[] a, int lp, int rp) {
		int x=a[lp];int rp1=rp;int lp1=lp;if(x>a[rp]){x=a[rp];}
		while(lp1<rp1){while((a[lp1]<=x)&&(lp1<rp1)){lp1++;} 
		while(a[rp1]>x){rp1--;}if(lp1<rp1){int temp=a[rp1];
		a[rp1]=a[lp1];a[lp1]=temp;}}a[lp]=a[rp1];a[rp1]=x;
		return rp1;
	}
}
