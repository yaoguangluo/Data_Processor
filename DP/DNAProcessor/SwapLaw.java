package DNAProcessor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SwapLaw{
	public ConcurrentLinkedDeque<DNA> swap(ConcurrentLinkedDeque<DNA> original) {
		ConcurrentLinkedDeque<DNA> output= new ConcurrentLinkedDeque<>();
		//random
		Iterator<DNA> iterator= original.iterator();
		while(iterator.hasNext()) {
			DNA dna= iterator.next();
			dna.setFunctionLineDeque(new CopyLaw().copy(dna.getMaskLineDeque()));	
			dna.setFunctionLineMap(new CopyLaw().copyMap(dna.maskLineDeque));	
			dna.setMaskLineMap(new CopyLaw().copyMap(dna.functionLineDeque));	
			output.add(dna);
		}
		return output;
	}
}