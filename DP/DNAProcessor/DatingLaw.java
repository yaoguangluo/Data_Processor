package DNAProcessor;
public class DatingLaw{
	public RST dating(RST father, RST mother) {
		RST son= new RST();
		//random
		if(Math.random()>0.5) {
			son.setFatherDeque(father.getFatherDeque());
		}else {
			son.setFatherDeque(father.getMotherDeque());
		}
		
		if(Math.random()<0.5) {
			son.setMotherDeque(mother.getMotherDeque());
		}else {
			son.setMotherDeque(mother.getFatherDeque());
		}	
		return son;
	}
}