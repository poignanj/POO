package implementation;

import java.util.TreeMap;

import interfaces.IFilter;

public class Filter2 implements IFilter {
	private Simulateur sim;
	private IFilter filter;
	
	private boolean isBranchedFilter;

	private TreeMap<String, String> lastStates;
	
	Filter2(IFilter filter)
	{
		this.filter = filter;
		lastStates = new TreeMap<>();
		isBranchedFilter = true;
	}
	
	Filter2(Simulateur sim)
	{
		this.sim = sim;
		lastStates = new TreeMap<>();
		isBranchedFilter = false;
	}

	@Override
	public TreeMap<String, String> GetStates() {
		TreeMap<String, String> res = new TreeMap<>();
		TreeMap<String, String> InResults;
		if(isBranchedFilter)
		{
			InResults = filter.GetStates();
		}
		else
		{
			InResults = sim.GetStates();
		}

		InResults.forEach((sid, data) -> {
			if(data != lastStates.get(sid))
			{
				lastStates.put(sid, data);
				res.put(sid, data);
			}
		});
		return res;
	}
}
