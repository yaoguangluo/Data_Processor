package sortProcessor;

import timeProcessor.TimeCheck;

//第二代罗瑶光小高峰平均高峰过滤快排思想设计中。耦合边缘路径快速排序
public class Quick_Luoyaoguang_1D{
	public int[] sort(int [] a) {	
		int lp = 0;
		int rp = a.length-1;
		quick(a, lp, rp);
		return a;
	}

	private void quick(int[] a, int lp, int rp) {
		if(lp >= rp) {
			return;
		}
		int reglp=lp;
		int regrp=rp;
		while(reglp < rp) {
			PeakFilter(a, reglp++, regrp);
		}
		while(regrp > reglp) {
			PeakFilter(a, reglp, regrp--);
		}
		int pos = reglp;
		quick(a, lp, pos-1);
		quick(a, pos, rp);	
	}

	private void PeakFilter(int[] a, int lp, int rp){
		if(a[lp]<=a[rp]) {
			return;
		}
		int temp = a[rp];
		a[rp] = a[lp];
		a[lp] = temp;
	}

	public static void main(String []argv) {
		int[] a = new  int[10000];
		for(int i = 0; i < 10000; i++) {
			a[i] = (int) (Math.random()*10);
		}
		TimeCheck t=new TimeCheck();
		t.begin();
		new Quick_Luoyaoguang_1D().sort(a);
		//new Quick_6D_luoyaoguang_Sort().sort(a);
		t.end();
		t.duration();
//		for(int i:a) {
//			System.out.println(i);
//		}
	}
}