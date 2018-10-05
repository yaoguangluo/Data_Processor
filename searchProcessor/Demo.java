package searchProcessor;
import java.io.IOException;
//import parserProcessor.timeCheck;
import sortProcessor.TTreeSort;
import sortProcessor.Leaf;
public class Demo{
	public static void main(String args[]) throws IOException{
        //init array
		boolean find=false;
		//timeCheck check=new timeCheck();
		//check.begin();
		int []array=arrayInit(); 
		//array=new Quick_2D_Sort().sort(array);
		//leaf root=new BTreeSort().root(array);
		Leaf root=new TTreeSort().root(array);
		//leaf root=new OTreeSort().root(array);

		//check.begin();
		
		      //find=new RandomSearch().search(array,2);
		     // find=new BinarySearch().search(array,2);
			 // find=new LinerSearch().search(array,2);
		//	find=new PreorderTreeSearch().search(root,2);
			//find=new DepthTreeSearch().search(root,20000);
			find=new BreadthTreeSearch().search(root,20000);
	//	check.end();
	    print(find); 
	}
	
	private static void print(boolean find) {
    if( find == true) {System.out.println("find");}
    else {System.out.println("not find");}
	}
	private static int[] arrayInit() {	
		int[] array=new int[999999];
		java.util.Random r=new java.util.Random(); 
		for(int i=999999,j=0;i>0;i--,j++){
			array[j]=r.nextInt(999999);
			//System.out.println(array[j]);
		}
		// TODO Auto-generated method stub
		return array;
	}
}