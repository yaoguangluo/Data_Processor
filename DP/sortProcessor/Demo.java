package sortProcessor;
import java.io.IOException;
//import parserProcessor.timeCheck;
public class Demo extends Thread{
	public static void main(String args[]) throws IOException, InterruptedException{
        //init array
        int []array=arrayInit(); 	    
        //timeCheck check=new timeCheck();  
	    /*
	    check.begin();
	   
        //sort
	    array=new BBSort().sort(array);
	    check.end();
	    print(array);
	    //*/
	    /*
	    check.begin();
	    array=new ShellSort().sort(array);
	    check.end();
	    print(array);
	    
	     
	     //*/
	    /*
	    check.begin();
	    array=new SelectionSort().sort(array);
	    check.end();
	    print(array);
	    //*/
	    
	    /*
	    check.begin();
	    array=new InsertionSort().sort(array,array.length);
	    check.end();
	    print(array);
	    //*/
	    
	   /*
	    check.begin();
	    array=new MergeSort().sort(array);
	    check.end();
	    print(array);
	   
	   //*/
	    /*
	    check.begin();
	    array=new Quick_1D_Sort().sort(array);
	    check.end();
	   // print(array);
	    
	    //*/
	    /*
	    check.begin();
	    array=new LinkSort().sort(array);
	    check.end();
	    
	    //print(array);
	    //*/
	    /*
	    check.begin();
	    array=new BTreeSort().sort(array);
	    check.end();
	    print(array);
	    //*/
	    
	    /*
	    check.begin();
	    array=new OTreeSort().sort(array);
	    check.end();
	    print(array);
	    //*/
	    /*
	    check.begin();
	    array=new Heap_2D_Sort().sort(array);
	    check.end();
	    
	   print(array);
	   // */
	   ///*
	    //check.begin();
	    array=new Quick_6D_luoyaoguang_Sort().sort(array);
	   //array=new Quick_Luoyaoguang_3D().sort(array);
	   //check.end();
	   print(array);
	    //*/
	    /*
	    check.begin();
	    //array=new Heap_2D_Sort().sort(array);
	    array=new Heap_2D_Sort().sort(array);
	    check.end();
	    //print(array);
	     */
	    /*
	    check.begin();
	    array=new OrderEvenSort().sort(array);
	    check.end();
	   print(array);
	   //*/
	    
	    /*
	    check.begin();
	    //array=new BinarySort().sort(array);
	  array=new Quick_6D_luoyaoguang_Sort().sort(array);
	   //array=new Quick_7D_Sort().sort(array);
	    check.end();
	   print(array);
	    //*/
	}
	
	private static void print(int[] a) {
		int count=0;
		for(int i=0;i<a.length-1;i++){
			//System.out.println(a[i]);
			if(a[i]>a[i+1]){
				count++;
				//System.out.println(a[i]);
			}
		}
		System.out.println(count);
	}

	private static int[] arrayInit() {
		int[] array=new int[9999999];
		java.util.Random r=new java.util.Random(); 
		for(int i=9999999,j=0;i>0;i--,j++){
			array[j]=r.nextInt();
			//System.out.println(array[j]);
		}
		// TODO Auto-generated method stub
		return array;
	}
}