package sortProcessor;

public class Quick_4D_Sort{
	public int[] sort(int [] a) {
		quick2d(a,0,a.length-1);
		return a;
	}
	
	public int[] sort(int [] a,int n) {
		int pos[]=new int[1];
		int lp=0;
		int rp=n-1;
		if(lp<rp)
		{partition(a,lp,rp,pos);
		quick2d(a,lp,pos[0]-1);
		quick2d(a,pos[0]+1,rp);}
		return a;
	}
	
	private void quick2d(int[] a, int lp, int rp) {
		// TODO Auto-generated method stub
		int pos[]=new int[1];
		if(lp<rp)
		{partition(a,lp,rp,pos);
		quick2d(a,lp,pos[0]-1);
		quick2d(a,pos[0]+1,rp);}
	}
	
	private void partition(int[] a, int lp, int rp, int[]pos) {
		// TODO Auto-generated method stub
		int x,lp1,rp1,temp;
		x=a[lp];rp1=rp;lp1=lp;
		while(lp1<rp1){
			while((a[lp1]<=x)&&(lp1<rp1)) lp1++;
			while(a[rp1]>x)rp1--;
			if(lp1<rp1){
				temp=a[rp1];
				a[rp1]=a[lp1];
				a[lp1]=temp;
			}
		}
		a[lp]=a[rp1];
		a[rp1]=x;
		pos[0]=rp1;
	}
}