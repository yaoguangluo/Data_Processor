package DNAProcessor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CopyLaw{
	public DNA copy(DNA dna) {
		DNA newDna= new DNA();
		newDna.functionLineDeque= copy(dna.getMaskLineDeque());
		newDna.maskLineDeque= mask(newDna.functionLineDeque, dna.getFunctionLineDeque(), dna.getMaskLineDeque());
		newDna.functionLineMap= copyMap(newDna.maskLineDeque);
		newDna.maskLineMap= copyMap(newDna.functionLineDeque);
		return newDna;	
	}

	private ConcurrentHashMap<Integer, PDN> copyMap(ConcurrentLinkedDeque<PDN> functionLineDeque) {
		ConcurrentHashMap<Integer, PDN> output= new ConcurrentHashMap<>();
		Iterator<PDN> iterator= functionLineDeque.iterator();
		int count= 0;
		while(iterator.hasNext()) {
			PDN pdn= iterator.next();
			boolean[] booleanPdn= pdn.getPdn();
			boolean[] booleanPdnNew= new boolean[booleanPdn.length];
			for(int i= 0; i< booleanPdn.length; i++) {
				booleanPdnNew[i]= !booleanPdn[i];
			}
			PDN pdnNew= new PDN();
			pdnNew.setPdn(booleanPdnNew);
			output.put(count++, pdnNew);
		}
		return output;
	}
	
	private ConcurrentLinkedDeque<PDN> mask(ConcurrentLinkedDeque<PDN> originDeque
			, ConcurrentLinkedDeque<PDN> newDeque, ConcurrentLinkedDeque<PDN> maskOutput) {
		Iterator<PDN> iteratorOriginDeque= originDeque.iterator();
		Iterator<PDN> iteratorNewDeque= newDeque.iterator();
		while(iteratorOriginDeque.hasNext()) {
			PDN pdnOriginDeque= iteratorOriginDeque.next();
			PDN pdnNewDeque= iteratorNewDeque.next();
			boolean[] booleanPdnOriginDeque= pdnOriginDeque.getPdn();
			boolean[] booleanPdnNewDeque= pdnNewDeque.getPdn();
			for(int i= 0; i< booleanPdnOriginDeque.length; i++) {
				if(booleanPdnOriginDeque[i]!= booleanPdnNewDeque[i]) {
					return null;
				}
			}
		}
		return maskOutput;
	}

	private ConcurrentLinkedDeque<PDN> copy(ConcurrentLinkedDeque<PDN> maskLineDeque) {
		ConcurrentLinkedDeque<PDN> output= new ConcurrentLinkedDeque<>();
		Iterator<PDN> iterator= maskLineDeque.iterator();
		while(iterator.hasNext()) {
			PDN pdn=iterator.next();
			boolean[] booleanPdn= pdn.getPdn();
			boolean[] booleanPdnNew= new boolean[booleanPdn.length];
			for(int i= 0; i< booleanPdn.length; i++) {
				booleanPdnNew[i]= !booleanPdn[i];
			}
			PDN pdnNew= new PDN();
			pdnNew.setPdn(booleanPdnNew);
			output.add(pdnNew);
		}
		return output;
	}

	public DNA mutationCopy(DNA dna) {
		DNA newDna= new DNA();
		newDna.maskLineDeque= copy(dna.getMaskLineDeque());
		newDna.functionLineDeque= mask(newDna.maskLineDeque, dna.getFunctionLineDeque(), dna.getMaskLineDeque());
		newDna.functionLineMap= copyMap(newDna.maskLineDeque);
		newDna.maskLineMap= copyMap(newDna.functionLineDeque);
		return newDna;	
	}
}