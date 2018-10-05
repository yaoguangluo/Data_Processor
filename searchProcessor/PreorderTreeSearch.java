package searchProcessor;
import sortProcessor.Leaf;
public class PreorderTreeSearch{
	public boolean search(Leaf root,int n1) {
		PreorderRun pr=new PreorderRun(root,n1);
		Thread t=new Thread(pr);
		t.run();
		while(true){
			if(pr.end){
				break;
			}
		}
		boolean ans=pr.result;
		t=null;
		pr=null;
		return ans;
	}	
}