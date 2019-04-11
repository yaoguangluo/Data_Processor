package DNAProcessor;
import java.util.concurrent.ConcurrentLinkedDeque;
public class LIFE{
	public ConcurrentLinkedDeque<RST> getLife() {
		return lifeDeque;
	}
	
	public void setLife(ConcurrentLinkedDeque<RST> lifeDeque) {
		this.lifeDeque= lifeDeque;
	}

	private ConcurrentLinkedDeque<RST> lifeDeque;
	public void born() {
		PDN helpFather= new PDN();
		PDN loveFather= new PDN();
		PDN workFather= new PDN();
		PDN studyFather= new PDN();
		PDN safeFather= new PDN();
		PDN creativeFather= new PDN();
		helpFather.init(true);
		loveFather.init(true);
		workFather.init(true);
		studyFather.init(true);
		safeFather.init(true);
		creativeFather.init(true);	
		ConcurrentLinkedDeque<PDN> PdnsFather= new ConcurrentLinkedDeque<>();
		PdnsFather.add(loveFather);
		PdnsFather.add(safeFather);
		PdnsFather.add(studyFather);
		PdnsFather.add(creativeFather);
		PdnsFather.add(workFather);
		PdnsFather.add(helpFather);
		DNA dnaFather= new DNA();
		dnaFather.setMaskLineDeque(PdnsFather);
		dnaFather.setFunctionLineDeque(new CopyLaw().copy(PdnsFather));
		dnaFather.setMaskLineMap(new CopyLaw().copyMap(dnaFather.getFunctionLineDeque()));
		dnaFather.setFunctionLineMap(new CopyLaw().copyMap(dnaFather.getMaskLineDeque()));
		
		PDN helpMother= new PDN();
		PDN loveMother= new PDN();
		PDN workMother= new PDN();
		PDN studyMother= new PDN();
		PDN safeMother= new PDN();
		PDN creativeMother= new PDN();
		helpMother.init(false);
		loveMother.init(false);
		workMother.init(false);
		studyMother.init(false);
		safeMother.init(false);
		creativeMother.init(false);	
		ConcurrentLinkedDeque<PDN> PdnsMother= new ConcurrentLinkedDeque<>();
		PdnsMother.add(loveMother);
		PdnsMother.add(safeMother);
		PdnsMother.add(studyMother);
		PdnsMother.add(creativeMother);
		PdnsMother.add(workMother);
		PdnsMother.add(helpMother);
		DNA dnaMother= new DNA();
		dnaMother.setMaskLineDeque(PdnsMother);
		dnaMother.setFunctionLineDeque(new CopyLaw().copy(PdnsMother));
		dnaMother.setMaskLineMap(new CopyLaw().copyMap(dnaMother.getFunctionLineDeque()));
		dnaMother.setFunctionLineMap(new CopyLaw().copyMap(dnaMother.getMaskLineDeque()));
		
		RST rst= new RST();
		rst.setFatherDeque(dnaFather);
		rst.setMotherDeque(dnaMother);
		ConcurrentLinkedDeque<RST> born= new ConcurrentLinkedDeque<>();
		born.add(rst);
		this.setLife(born);
	}

	public void evolve() {

	}

	public void getInformation() {
		
	}
}