package sortProcessor;
public class ShellSort{
	public int[] sort(int [] a) {
		for(int i=(a.length-1)/2;i>0;i/=2){
			for(int ii=i;ii<a.length;ii++){
				int iii=ii-i;
				while(iii>=0){
					if(a[iii+i]<a[iii]){
						int temp=a[iii+i];
						a[iii+i]=a[iii];
						a[iii]=temp;
					}
					iii=iii-i;
				}
			}
		}
		return a;
	}
}