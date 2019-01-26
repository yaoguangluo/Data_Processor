package sortProcessor;
public class BinarySort{
	public int[] sort(int [] a) {
		int ps = 0;
		int pb = 0;
		int fs=0;
		int fb=0;
		int e=a.length;
		for(int i=0;i<e;i++,e--){
			int s=a[i];
			int b=a[i];
			for(int j=i;j<e;j++){
				if(s>a[j]){
					s=a[j];
					ps=j;
					fs=1;
				}
				if(b<a[j]){
					b=a[j];
					pb=j;
					fb=1;
				}
			}
			if(fs==1){
				fs=0;
				int temp=a[i];
				a[i]=s;
				a[ps]=temp;
			}
			if(fb==1){
				int temp=a[e-1];
				a[e-1]=b;
				a[pb]=temp;
			}
		}
		return a;
	}
}