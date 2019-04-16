package DNAProcessor;
import java.util.concurrent.ConcurrentHashMap;
public class Race{
	public ConcurrentHashMap<String, Family> getRace() {
		return race;
	}

	public void setRace(ConcurrentHashMap<String, Family> race) {
		this.race = race;
	}

	ConcurrentHashMap<String, Family> race= new ConcurrentHashMap<>();
}