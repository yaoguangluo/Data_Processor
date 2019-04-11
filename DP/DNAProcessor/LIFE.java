package DNAProcessor;
import java.util.concurrent.ConcurrentLinkedDeque;
public class LIFE{
	public ConcurrentLinkedDeque<RST> getFatherDeque() {
		return lifeDeque;
	}
	
	public void setFatherDeque(ConcurrentLinkedDeque<RST> lifeDeque) {
		this.lifeDeque= lifeDeque;
	}

	private ConcurrentLinkedDeque<RST> lifeDeque;
}