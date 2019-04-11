package DNAProcessor;
public class MutationLaw{
	public RST mutation(RST father, RST mother) {
		RST son= new RST();
		//random
		if(Math.random()>0.5) {
			son.setFatherDeque(father.getFatherDeque());
		}else {
			son.setFatherDeque(mother.getFatherDeque());
		}
		
		if(Math.random()<0.5) {
			son.setMotherDeque(mother.getMotherDeque());
		}else {
			son.setMotherDeque(father.getMotherDeque());
		}	
		return son;
	}
}