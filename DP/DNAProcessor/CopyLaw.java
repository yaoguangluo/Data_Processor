package DNAProcessor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CopyLaw{
	public DNA copy(DNA dna) {
		DNA newDna= new DNA();
		newDna.functionLineDeque= copy(dna.getMaskLineDeque());
		newDna.maskLineDeque= mask(newDna.functionLineDeque);
		newDna.functionLineMap= functionLineMap(newDna.functionLineDeque);
		newDna.maskLineMap= maskLineMap(newDna.maskLineDeque);
		return newDna;	
	}

	private ConcurrentHashMap<Integer, PDN> maskLineMap(ConcurrentLinkedDeque<PDN> maskLineDeque) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConcurrentHashMap<Integer, PDN> functionLineMap(ConcurrentLinkedDeque<PDN> functionLineDeque) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConcurrentLinkedDeque<PDN> mask(ConcurrentLinkedDeque<PDN> functionLineDeque) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConcurrentLinkedDeque<PDN> copy(ConcurrentLinkedDeque<PDN> maskLineDeque) {
		// TODO Auto-generated method stub
		return null;
	}
}