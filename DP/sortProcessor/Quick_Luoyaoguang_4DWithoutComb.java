package sortProcessor;
import timeProcessor.TimeCheck;
//第三代罗瑶光小高峰平均高峰过滤快排思想设计中。小高峰高峰过滤快速排序
//同频函数减少
//同频算子减少
//同频变量减少
public class Quick_Luoyaoguang_4DWithoutComb{
	public int[] sort(int[] a) {
		quick2ds(a, 0, a.length-1);
		return a;
	}

	private void quick2ds(int[] a, int lp, int rp) {
		if(lp < rp){
			int pos = partition(a, lp, rp);
			quick2ds(a, lp, pos-1);
			quick2ds(a, pos+1, rp);
		}
	}

	private int partition(int[] a, int lp, int rp) {
		int x= a[lp]< a[rp]? a[lp]: a[rp];
		int lp1=lp;
		while(lp1<rp){
			while(a[lp1]<=x &&lp1<rp ) {
				lp1++;
			}
			while(a[rp]>x){
				rp--;
			}
			if(lp1<rp){
				int temp=a[rp];
				a[rp]=a[lp1];a[lp1]=temp;
			}
		}
		a[lp]=a[rp];a[rp]=x;
		return rp;
	}
}
