package sortProcessor;
public class OrderEvenSort{
	public int[] sort(int [] array) {
		boolean sorted = false;
		while(!sorted){
			sorted=true;
			for(int i = 1; i < array.length-1; i += 2) {
				if(array[i] > array[i+1]) {
					swap(array,i, i+1);
					sorted = false;
				}
			}

			for(int i = 0; i < array.length-1; i += 2){
				if(array[i] > array[i+1]) {
					swap(array, i, i+1);
					sorted = false;
				}
			}
		}



		return array;
	}

	private void swap(int[] array, int i, int j) {
		// TODO Auto-generated method stub
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
	}
}