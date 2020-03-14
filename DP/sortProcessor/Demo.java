package sortProcessor;
import java.io.IOException;

import timeProcessor.TimeCheck;
//import parserProcessor.timeCheck;
public class Demo extends Thread{
	public static void main(String args[]) throws IOException, InterruptedException{
        //init array
        int []array=arrayInit(); 
        int []array1= array.clone();
        int []array2= new int[8];
        array2[0]=array[0];
        array2[1]=array[1];
        array2[2]=array[2];
        array2[3]=array[3];
        array2[4]=array[4];
        array2[5]=array[5];
        array2[6]=array[6];
        array2[7]=array[7];
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
        System.out.println(" ");
        System.out.println("罗瑶光小高峰过滤快速排序4代：");
        TimeCheck imeCheck2= new TimeCheck();
        imeCheck2.begin();
      //  array2= new StringSequency().stringArrayToSequencyArrayDecrement(array, 16);
        imeCheck2.end();
        imeCheck2.duration();
//        for(int i=0;i<100000;i++) {
//        	for(int j=0;j<100000;j++) {
//        		for(int k=0;k<100000;k++) {
//        			int prepare=0;
//        		}  
//        	}  
//        }    
//        System.out.println(" ");
//        System.out.println("贝尔实验室最新分配集合快速排序法：");
//        TimeCheck imeCheck1= new TimeCheck();
//        imeCheck1.begin();
//        array1=new Compare().quick(array1);
//        imeCheck1.end();
//        imeCheck1.duration();
//        for(int i=0;i<100000;i++) {
//        	for(int j=0;j<100000;j++) {
//        		for(int k=0;k<100000;k++) {
//        			int prepare=0;
//        		}  
//        	}  
//        }
      
        System.out.println(" ");
        System.out.println("罗瑶光小高峰过滤快速排序5代：");
        TimeCheck imeCheck1= new TimeCheck();
        imeCheck1.begin();
        array1=new Quick_Luoyaoguang_5D().sort(array1);
        imeCheck1.end();
        imeCheck1.duration();
        
        System.out.println(" ");
        System.out.println("罗瑶光小高峰过滤快速排序4代：");
        TimeCheck imeCheck= new TimeCheck();
        imeCheck.begin();
        array=new Quick_Luoyaoguang_4D().sort(array);
        imeCheck.end();
        imeCheck.duration();
        
      
       

    

        
	   //array=new Quick_Luoyaoguang_3D().sort(array);
	   //check.end();
//	   print(array);
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