package DNAProcessor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
public class DNA{
	public int positive= 1;
	public int normal= 0;
	public int negativetive= -1;
	public ConcurrentLinkedDeque<String> functionLineDeque;
	public ConcurrentLinkedDeque<String> maskLineDeque;
	public ConcurrentHashMap<Integer, String> functionLineMap;
	public ConcurrentHashMap<Integer, String> maskLineMap;
}