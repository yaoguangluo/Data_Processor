package DNAProcessor;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SeparaterLaw{
	public ConcurrentLinkedDeque<DNA> separater(ConcurrentLinkedDeque<DNA> original) {
		ConcurrentLinkedDeque<DNA> output= new ConcurrentLinkedDeque<>();
		//random
		if(Math.random()> 0.999997) {
			Iterator<DNA> iterator= original.iterator();
			while(iterator.hasNext()) {
				output.add(new CopyLaw().mutationCopy(iterator.next()));
			}
		}else {
			Iterator<DNA> iterator= original.iterator();
			while(iterator.hasNext()) {
				output.add(new CopyLaw().copy(iterator.next()));
			}
		}
		return output;
	}
}