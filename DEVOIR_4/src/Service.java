import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import implementation.*;
import interfaces.IFilter;

public class Service extends JFrame { 
	
	private ArrayList<IFilter> filters;
	private TreeMap<String, String> dataset = new TreeMap<>();
	private String data = "dataset";
	private JLabel l;
	private Simulateur s;
	private int currentFilter;
	public Service(Simulateur s) {
		currentFilter = -1;
		this.s = s;
		setFilters(new ArrayList<>());
		this.setTitle("Service");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JButton j = new JButton("Raw Data");
		j.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentFilter= -1;
				activate();
				
			}
		});
		JButton j1 = new JButton("Filter 1");
		j1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentFilter = 0;
				activate();
				
			}
		});
		JButton j2 = new JButton("Filter 2");
		j2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentFilter = 1;
				activate();
				
			}
		});
		this.l = new JLabel("dataset");
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;
		c.weightx = 0.33;
		c.gridy = 0;
		c.gridx = 0;
		p.add(j,c);
		c.gridx = 1;
		p.add(j1,c);
		c.gridx = 2;
		p.add(j2,c);
		c.weighty = 0.9;
		c.gridy = 1;
		c.gridx = 0;
		p.add(l,c);
	this.setContentPane(p);
	this.setVisible(true);
		
	}
	
	
	public ArrayList<IFilter> getFilters() {
		return filters;
	}

	public void setFilters(ArrayList<IFilter> filters) {
		this.filters = filters;
	}
	
	public void activate() {
		dataset = s.GetStates();
		System.out.println(dataset.toString());
		applyFilter(currentFilter);
		l.setText(dataset.toString());
	}
	public void applyFilter(int i) {
		if(i>=0) {
		TreeMap<String, String> dataFilter = new TreeMap<>();
		dataFilter = filters.get(i).GetStates();
		dataFilter.forEach((sid, vehicle)->{
			dataset.put(sid, vehicle);
		});
		}
	}
	
	///WebSocket deleted
	
		
	public static void main(String[] args) {
		Simulateur s = new Simulateur("DossierTest",500);
		Service ser = new Service(s);
		ser.getFilters().add(new Filter1(s));
		ser.getFilters().add(new Filter2(s));
		s.run();
	}
}
