package searchProcessor;
public class BinarySearch{
	public boolean search(int [] array,int n) {	
		int low = 0;
		int high=array.length-1;
		while(low<high){
			int mid=(low+high)/2;
			if(array[mid]==n) {
				return true;
			}else {
				if(n<array[mid]) {
					high=mid-1;
				}else{
					low=mid+1;
				}
			}
		}	
		return false;	
	}	
}