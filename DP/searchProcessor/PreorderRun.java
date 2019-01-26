package searchProcessor;
import sortProcessor.Leaf;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageDecoder;
public class PreorderRun extends Thread{
	public boolean result;
	Leaf temp1;
	int n;
	public boolean end=false;
	public PreorderRun(Leaf root, int n1) {
    temp1=root;
    n=n1;
	}
	
	@Override
	public void run(){
		result=false;
		check(temp1);			
		end=true;	
	}

	private void check(Leaf temp) {
		if(temp!=null){
			check(temp.O1);	
			if(temp.hasO1==1){
				if(n == temp.value[0]){
					result=true;
					end=true;
				}	
			}
			check(temp.O2);
			if(temp.hasO2==1){
				if(n == temp.value[1]){
					result=true;
					end=true;
				}
			}
			check(temp.O3);
			if(temp.hasO3==1){
				if(n == temp.value[2]){
					result=true;
					end=true;
				}
			}
			check(temp.O4);
			if(temp.hasO4==1){
				if(n == temp.value[3]){
					result=true;
					end=true;
				}
			}
			check(temp.O5);
			if(temp.hasO5==1){
				if(n == temp.value[4]){
					result=true;
					end=true;
				}
			}
			check(temp.O6);
			if(temp.hasO6==1){
				if(n == temp.value[5]){
					result=true;
					end=true;
				}
			}
			check(temp.O7);
			if(temp.hasO7==1){
				if(n == temp.value[6]){
					result=true;
					end=true;
				}
			}
			check(temp.O8);
		}	
	}
}