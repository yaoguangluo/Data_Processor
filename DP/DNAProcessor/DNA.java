package DNAProcessor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
public class DNA{
	public ConcurrentLinkedDeque<PDN> functionLineDeque;
	public ConcurrentLinkedDeque<PDN> maskLineDeque;
	public ConcurrentHashMap<Integer, PDN> functionLineMap;
	public ConcurrentHashMap<Integer, PDN> maskLineMap;
}