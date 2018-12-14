package implementation;

import java.util.TreeMap;

import interfaces.IFilter;

public class Filter2 implements IFilter {
	private Simulateur sim;
	private TreeMap<String, Boolean> lastStatesSim;
	
	private boolean isBranchedFilter;

	private IFilter filter;
	private TreeMap<String, String> lastStatesFilter;
	
	Filter2(IFilter filter)
	{
		this.filter = filter;
		lastStatesFilter = new TreeMap<>();
		isBranchedFilter = true;
	}
	
	Filter2(Simulateur sim)
	{
		this.sim = sim;
		lastStatesSim = new TreeMap<>();
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
			if(data != lastStatesFilter.get(sid))
			{
				lastStatesFilter.put(sid, data);
				res.put(sid, data);
			}
		});
		return res;
	}
}
