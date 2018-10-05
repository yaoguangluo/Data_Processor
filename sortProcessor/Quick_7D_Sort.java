package sortProcessor;
public class Quick_7D_Sort{
	public int[] sort(int [] a) {
		quick2d(a,0,a.length-1);
		return a;
	}
	
	private void quick2d(int[] x, int lp, int rp) {
		if(lp<rp){
			int len=rp-lp+1;	 
			if(len<7){
				int j;
				for(int i=1+lp;i<lp+len;i++){
					j=i;
					while(j>=1+lp){
						if(x[j]<x[j-1]){
							int temp=x[j];
							x[j]=x[j-1];
							x[j-1]=temp;
						}
						j--;
					}	
				}	
			} else{
				int p=lp;
				int m=p+(len>>1);
				if(len>7){
					int l=p;
					int n=p+len-1;
					if(len>40){
						int s = len/8;
						l=med3(x,l,l+s,l+2*s);
						m=med3(x,m-s,m,m+s);
						n=med3(x,n-2*s,n-s,n);
					}
					m=med3(x,l,m,n);
				}
				int v=x[m];
				int a=p,b=a,c=p+len-1,d=c;
				while(true){
					while(b<=c&&x[b]<=v){
						if(x[b]==v)
							swap(x,a++,b);
						b++;
					}
					while(c>=b&&x[c]>=v){
						if(x[c]==v)
							swap(x,c,d--);
						c--;
					}
					if(b>c)
						break;
					swap(x,b++,c--);
				}
				int s,n=p+len;
				s=Math.min(a-p, b-a);
				vecswap(x,p,b-s,s);
				s=Math.min(d-c,n-d-1);
				vecswap(x,b,n-s,s);
				if((s=b-a)>1)
					quick2d(x,p,s+p-1);
				if((s=d-c)>1)
					quick2d(x,n-s,n-1);
			}	}
	}
	
	private void vecswap(int[] x, int a, int b, int n) {
		for(int i=0;i<n;i++,a++,b++)
			swap(x,a,b);

	}
	
	private void swap(int[] x, int i, int b) {
		if(x[i]<x[b])
		{
			int temp=x[i];
			x[i]=x[b];
			x[b]=temp;
		}

	}
	
	private int med3(int x[],int a,int b,int c) {
		// TODO Auto-generated method stub
		return x[a]<x[b]?(x[b]<x[c]?b:x[a]<x[c]?c:a)
				:x[b]>x[c]?b:x[a]>x[c]?c:a;
	}
}