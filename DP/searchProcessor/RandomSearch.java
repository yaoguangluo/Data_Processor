package searchProcessor;
public class RandomSearch{
	public boolean search(int [] array,int n) {
		int ran[] = new int[array.length];	
		java.util.Random r = new java.util.Random(); 
		int i=0;
		while(i<array.length){
			int j = r.nextInt(array.length);
			if(ran[j] == 0) {
				if(array[j] == n){
					return true;
				}else{
					ran[j]=1;
					i+=1;
				}
			}
		}
		return false;
	}
}