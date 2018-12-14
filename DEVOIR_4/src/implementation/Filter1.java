package implementation;

import java.util.TreeMap;

import interfaces.IFilter;

public class Filter1 implements IFilter {
	private Simulateur sim;

	public Filter1(Simulateur sim)
	{
		this.sim = sim;
	}

	@Override
	public TreeMap<String, String> GetStates() {
		TreeMap<String, String> res = new TreeMap<>();
		
		TreeMap<String, String> filterResults = sim.GetStates();
		filterResults.forEach((sid, vehicle) -> {
			res.put(sid, (vehicle == "1") ? "D" : "N");
		});
		return res;
	}

}
