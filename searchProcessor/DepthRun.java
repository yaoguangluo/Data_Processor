package searchProcessor;
import sortProcessor.Leaf;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageDecoder;
public class DepthRun extends Thread{
	public boolean result;
	Leaf temp1;
	int n;
	public boolean end=false;
	public DepthRun(Leaf root, int n1) {
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
			if(temp.sort==0){
				if(temp.hasO1==1){
					if(n == temp.value[0]){
						result=true;
						end=true;
					}

				}
				if(temp.hasO2==1){
					if(n == temp.value[1]){
						result=true;
						end=true;
					}
				}
				if(temp.hasO3==1){
					if(n == temp.value[2]){
						result=true;
						end=true;
					}
				}
				if(temp.hasO4==1){
					if(n == temp.value[3]){
						result=true;
						end=true;
					}
				}
				if(temp.hasO5==1){
					if(n == temp.value[4]){
						result=true;
						end=true;
					}
				}
				if(temp.hasO6==1){
					if(n == temp.value[5]){
						result=true;
						end=true;
					}
				}
				if(temp.hasO7==1){
					if(n == temp.value[6]){
						result=true;
						end=true;
					}
				}
				temp.sort=1;
				if(temp.O1!=null&&temp.O1.sort==0)
					check(temp.O1);	
				if(temp.O2!=null&&temp.O2.sort==0)
					check(temp.O2);
				if(temp.O3!=null&&temp.O3.sort==0)
					check(temp.O3);
				if(temp.O4!=null&&temp.O4.sort==0)
					check(temp.O4);
				if(temp.O5!=null&&temp.O5.sort==0)
					check(temp.O5);
				if(temp.O6!=null&&temp.O6.sort==0)
					check(temp.O6);	
				if(temp.O7!=null&&temp.O7.sort==0)
					check(temp.O7);
				if(temp.O8!=null&&temp.O8.sort==0)
					check(temp.O8);
			}
		}
	}
}