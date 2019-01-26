package sortProcessor;
class node {
	public node next;
	public int value;
}
public class LinkSort{
	public node heap; 
	public node first;
	public int[] sort(int [] a) {
		for(int i=0;i<a.length;i++){
			lyg(a[i]);
		}
		for(int i=0;i<a.length;i++){
			a[i]=first.value;
			if(first.next!=null)
				first=first.next;
		}
		return a;
	}

	private void lyg(int i) {
		// TODO Auto-generated method stub
		if(heap==null){
			heap=new node();
			heap.value=i;
			first=heap;
			return;
		}
		heap=first;
		if(i<=heap.value){
			node temp=new node();
			temp.value=i;
			temp.next=heap;
			first=temp;
			return;
		}
		while(heap.next!=null){
			if(i>=heap.value&&i<=heap.next.value){
				node temp=new node();
				temp.value=i;
				temp.next=heap.next;
				heap.next=temp;
				return;
			}
			heap=heap.next;
		}
		node temp=new node();
		heap.next=temp;
		temp.value=i;
		return;
	}
}