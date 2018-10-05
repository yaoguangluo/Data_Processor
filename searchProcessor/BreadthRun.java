package searchProcessor;
import sortProcessor.Leaf;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageDecoder;
public class BreadthRun extends Thread{
	public boolean result;
	Leaf temp1;
	int n;
	public boolean end=false;
	public Node head,back;
	public BreadthRun(Leaf root, int n1) {
		temp1=root;
		n=n1;
	}
	@Override
	public void run(){
		result=false;
		check(temp1);
		while(head!=null){
			pop();
		}
		end=true;	
	}
	@SuppressWarnings("unused")
	private void pop() {
		if(head!=null){
			if(head.value.sort==0){
				if(head.value.hasO1==1){
					if(n == head.value.value[0]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO2==1){
					if(n == head.value.value[1]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO3==1){
					if(n == head.value.value[2]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO4==1){
					if(n == head.value.value[3]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO5==1){
					if(n == head.value.value[4]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO6==1){
					if(n == head.value.value[5]){
						result=true;
						end=true;
					}
				}
				if(head.value.hasO7==1){
					if(n == head.value.value[6]){
						result=true;
						end=true;
					}
				}
				head.value.sort=1;
				if(head.value.O1!=null&&head.value.O1.sort==0)
					push(head.value.O1);	
				if(head.value.O2!=null&&head.value.O2.sort==0)
					push(head.value.O2);
				if(head.value.O3!=null&&head.value.O3.sort==0)
					push(head.value.O3);
				if(head.value.O4!=null&&head.value.O4.sort==0)
					push(head.value.O4);
				if(head.value.O5!=null&&head.value.O5.sort==0)
					push(head.value.O5);
				if(head.value.O6!=null&&head.value.O6.sort==0)
					push(head.value.O6);	
				if(head.value.O7!=null&&head.value.O7.sort==0)
					push(head.value.O7);
				if(head.value.O8!=null&&head.value.O8.sort==0)
					push(head.value.O8);	
			}
		}
		Node vv=head;
		head=head.next;
		vv=null;
	}
	private void check(Leaf temp) {
		// TODO Auto-generated method stub
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
				if(temp.hasO4==1)
				{
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
					push(temp.O1);	
				if(temp.O2!=null&&temp.O2.sort==0)
					push(temp.O2);
				if(temp.O3!=null&&temp.O3.sort==0)
					push(temp.O3);
				if(temp.O4!=null&&temp.O4.sort==0)
					push(temp.O4);
				if(temp.O5!=null&&temp.O5.sort==0)
					push(temp.O5);
				if(temp.O6!=null&&temp.O6.sort==0)
					push(temp.O6);	
				if(temp.O7!=null&&temp.O7.sort==0)
					push(temp.O7);
				if(temp.O8!=null&&temp.O8.sort==0)
					push(temp.O8);
			}
		}
	}

	private void push(Leaf cur) {
		// TODO Auto-generated method stub
		if(head==null){
			head=new Node();
			head.value=cur;
			back=head;
		}else{
			Node curr=new Node();
			curr.value=cur;
			back.next=curr;
			back=curr;
		}
	}
}