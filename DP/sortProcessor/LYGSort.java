package sortProcessor;
import java.util.ArrayList;

import java.util.List;
//import parserProcessor.timeCheck;
public class LYGSort{
	public List<Double> array = new ArrayList<Double>();
	public void sort(double [] a) {
		array.add(a[0]);
		if(a[1] > a[0]) {
			array.add(1, a[1]);
		}else {
			array.add(0, a[1]);
		}
		if(a[2] < a[0]) {
			array.add(0, a[2]);
		}else if(a[2] > a[1]) {
			array.add(2, a[2]);
		}else {
			array.add(1, a[2]);
		}
		for(int i = 3; i < a.length; i++) {
			bsa(a[i], 0, array.size() - 1);
		}
	}
	
	private void bsa(double a, int l, int r) {
		int m = (l + r) >> 1;
		if(m != l) {
			if(a <= array.get(m)){
				bsa(a, l, m);
			}else {
				bsa(a, m, r);
			}
		} else if(m == l){
			if(a <= array.get(m)){
				array.add(l, a);
			}else if(a > array.get(m) && a <= array.get(r) ){
				array.add(l+1, a);
			}else {
				array.add(l+2, a);
			}		
		} 
	}
	
	public static void main(String agrs[]) {
		int c=99999;
		double [] a = new double[c];
		java.util.Random r=new java.util.Random(); 
		for(int i=0;i<c;i++) {
			a[i]=r.nextDouble();
		}
		LYGSort lyg = new LYGSort();
		//timeCheck t= new timeCheck();
		//	t.begin();
		lyg.sort(a);
		//	t.end();
	}
}