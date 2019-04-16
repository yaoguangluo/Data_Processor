package DNAProcessor;
import java.util.concurrent.ConcurrentHashMap;
public class Family{
	public ConcurrentHashMap<String, Life> getFamily() {
		return family;
	}

	public void setFamily(ConcurrentHashMap<String, Life> family) {
		this.family = family;
	}

	ConcurrentHashMap<String, Life> family= new ConcurrentHashMap<>();
}