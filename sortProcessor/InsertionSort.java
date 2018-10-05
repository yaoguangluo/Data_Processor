package sortProcessor;
public class InsertionSort{
	public int[] sort(int [] array) {
		int j;
		for(int i=1;i<array.length;i++){
			j=i;
			while(j>=1){
				if(array[j]<array[j-1]){
					int temp=array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
				}
				j-=1;
			}	
		}
		return array;
	}

	public int[] sort(int [] array, int n) {
		int j;
		for(int i=1;i<n;i++){
			j=i;
			while(j>=1){
				if(array[j]<array[j-1]){
					int temp=array[j];
					array[j]=array[j-1];
					array[j-1]=temp;
				}
				j-=1;
			}	
		}
		return array;
	}
}