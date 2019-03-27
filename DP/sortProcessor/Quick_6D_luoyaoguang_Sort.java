package sortProcessor;
//第二代罗瑶光小高峰平均高峰过滤快排思想设计中。小高峰高峰过滤快速排序
public class Quick_6D_luoyaoguang_Sort{
	public int[] sort(int[] a) {
		quick2ds(a, 0, a.length-1);
		return a;
	}

	private void quick2ds(int[] a, int lp, int rp) {
		if(lp < rp){
			int c = rp - lp; if(c < 7){ int j;
			for(int i = 1 + lp; i <= lp + c; i++){
				j = i;while(j>=1+lp){
					if(a[j]<a[j-1]){
						int temp=a[j];a[j]=a[j-1];a[j-1]=temp;
					}
					j--;
				}
			}	
			return;
			}
			int pos = partition(a, lp, rp);
			quick2ds(a,lp,pos-1);
			quick2ds(a,pos+1,rp);
		}
	}

	private int partition(int[] a, int lp, int rp) {
		int x=a[lp];int rp1=rp;int lp1=lp;
		if(x>=a[rp]){
			x=a[rp];
		}
		while(lp1<rp1){
			while((a[lp1]<=x)&&(lp1<rp1)){
				lp1++;
			} 
			while(a[rp1]>x){
				rp1--;
			}
			if(lp1<rp1){
				int temp=a[rp1];
				a[rp1]=a[lp1];a[lp1]=temp;
			}
		}
		a[lp]=a[rp1];a[rp1]=x;
		return rp1;
	}
	
	public String[][] sort(String[][] a) {
		quick2dsString(a, 0, a.length-1);
		return a;
	}

	private void quick2dsString(String[][] a, int lp, int rp) {
		if(lp < rp){
			int c = rp - lp; if(c < 7){ int j;
			for(int i = 1 + lp; i <= lp + c; i++){
				j = i;while(j>=1+lp){
					if(Double.valueOf(a[j][1])<Double.valueOf(a[j-1][1])){
						String []temp=a[j];
						a[j]=a[j-1];
						a[j-1]=temp;
					}
					j--;
				}
			}	
			return;
			}
			int pos = partitionString(a, lp, rp);
			quick2dsString(a,lp,pos-1);
			quick2dsString(a,pos+1,rp);
		}
	}

	private int partitionString(String[][] a, int lp, int rp) {
		String[] x=a[lp];int rp1=rp;int lp1=lp;
		if(Double.valueOf(x[1])>=Double.valueOf(a[rp][1])){
			x=a[rp];
		}
		while(lp1<rp1){
			while((Double.valueOf(a[lp1][1])<=Double.valueOf(x[1]))&&(lp1<rp1)){
				lp1++;
			} 
			while(Double.valueOf(a[rp1][1])>Double.valueOf(x[1])){
				rp1--;
			}
			if(lp1<rp1){
				String[] temp=a[rp1];
				a[rp1]=a[lp1];a[lp1]=temp;
			}
		}
		a[lp]=a[rp1];a[rp1]=x;
		return rp1;
	}
}
