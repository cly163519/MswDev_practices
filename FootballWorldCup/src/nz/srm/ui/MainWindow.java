package nz.srm.ui;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.organisation.*;

public class MainWindow {

	public MainWindow() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Football World Cup Simulator 2023");
		
		List<Team> teams = new ArrayList<Team>();
		
		teams.add(new Team("USA", 1, false));
		teams.add(new Team("Japan", 2, false));
		teams.add(new Team("Canada", 3, false));
		teams.add(new Team("Sweden", 4, false));
		teams.add(new Team("Germany", 5, false));
		teams.add(new Team("England", 6, false));
		teams.add(new Team("Spain", 7, false));
		teams.add(new Team("Norway", 8, false));
		teams.add(new Team("Netherlands", 9, false));
		teams.add(new Team("Australia", 10, true));
		teams.add(new Team("Jamaica", 11, false));
		teams.add(new Team("South Africa", 12, false));
		teams.add(new Team("Denmark", 13, false));
		teams.add(new Team("Italy", 14, false));
		teams.add(new Team("China", 15, false));
		teams.add(new Team("New Zealand", 26, true));

		Tournament worldCup = new Tournament(teams);
		worldCup.setup();
		
	}

}
