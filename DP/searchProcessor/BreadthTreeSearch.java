package searchProcessor;
import sortProcessor.Leaf;
public class BreadthTreeSearch{
	public boolean search(Leaf root,int n1) {
		BreadthRun pr=new BreadthRun(root,n1);
		Thread t=new Thread(pr);
	    t.run();
	    while(true)   {
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