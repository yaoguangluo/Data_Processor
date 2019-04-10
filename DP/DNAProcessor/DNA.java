package DNAProcessor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
public class DNA{
	public ConcurrentLinkedDeque<PDN> getFunctionLineDeque() {
		return functionLineDeque;
	}
	
	public void setFunctionLineDeque(ConcurrentLinkedDeque<PDN> functionLineDeque) {
		this.functionLineDeque = functionLineDeque;
	}
	
	public ConcurrentLinkedDeque<PDN> getMaskLineDeque() {
		return maskLineDeque;
	}
	
	public void setMaskLineDeque(ConcurrentLinkedDeque<PDN> maskLineDeque) {
		this.maskLineDeque = maskLineDeque;
	}
	
	public ConcurrentHashMap<Integer, PDN> getFunctionLineMap() {
		return functionLineMap;
	}
	
	public void setFunctionLineMap(ConcurrentHashMap<Integer, PDN> functionLineMap) {
		this.functionLineMap = functionLineMap;
	}
	
	public ConcurrentHashMap<Integer, PDN> getMaskLineMap() {
		return maskLineMap;
	}
	
	public void setMaskLineMap(ConcurrentHashMap<Integer, PDN> maskLineMap) {
		this.maskLineMap = maskLineMap;
	}
	
	public ConcurrentLinkedDeque<PDN> functionLineDeque;
	public ConcurrentLinkedDeque<PDN> maskLineDeque;
	public ConcurrentHashMap<Integer, PDN> functionLineMap;
	public ConcurrentHashMap<Integer, PDN> maskLineMap;
}