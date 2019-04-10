package DNAProcessor;
import java.util.concurrent.ConcurrentLinkedDeque;
public class RST{
	public ConcurrentLinkedDeque<DNA> getFatherDeque() {
		return fatherDeque;
	}
	
	public void setFatherDeque(ConcurrentLinkedDeque<DNA> fatherDeque) {
		this.fatherDeque= fatherDeque;
	}
	
	public ConcurrentLinkedDeque<DNA> getMotherDeque() {
		return motherDeque;
	}
	
	public void setMotherDeque(ConcurrentLinkedDeque<DNA> motherDeque) {
		this.motherDeque= motherDeque;
	}
	
	private ConcurrentLinkedDeque<DNA> fatherDeque;
	private ConcurrentLinkedDeque<DNA> motherDeque;
}