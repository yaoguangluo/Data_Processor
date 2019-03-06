package sortProcessor;

import timeProcessor.TimeCheck;

//第二代罗瑶光小高峰平均高峰过滤快排思想设计中。高峰均值优化快速排序
public class Quick_Luoyaoguang_2D{
	static int n=150000;
	public int[] sort(int [] a) {	
		int lp = 0;
		int rp = a.length-1;
		quick(a, lp, rp);
		return a;
	}

	private void quick(int[] a, int lp, int rp) {
		if(lp > rp) {
			return;
		}
		int reglp = lp;
		int regrp = rp;
		boolean peak = true;
		while(reglp < regrp) {
			boolean oldPeck = peak;
			if(a[reglp] > a[regrp]) {
				int temp = a[regrp];
				a[regrp] = a[reglp];
				a[reglp] = temp;
				peak = !peak;
			}
			if(oldPeck==true && oldPeck == peak) {
				reglp++;
			}
			if(oldPeck==false && oldPeck == peak) {
				regrp--;
			}
		}
		if(lp < reglp-1) {
			quick(a, lp, reglp-1) ;
		}
		if(rp > reglp) {
			quick(a, reglp, rp) ;	
		}
	}

	public static void main(String []argv) {
		int[] a = new int[n];	
		for(int i = 0; i < n; i++) {
			a[i] = (int) (Math.random()*100000);
			System.out.print(a[i]);
		}
		System.out.print("");
		TimeCheck t=new TimeCheck();
		t.begin();
		new Quick_Luoyaoguang_2D().sort(a);
	//	new Quick_6D_luoyaoguang_Sort().sort(a);
		t.end();
		t.duration();
	}
}