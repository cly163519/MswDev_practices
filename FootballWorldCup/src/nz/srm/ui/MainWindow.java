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
		
		teams.add(new Team("Sweden", 1, false));
		teams.add(new Team("Spain", 2, false));
		teams.add(new Team("USA", 3, false));
		teams.add(new Team("England", 4, false));
		teams.add(new Team("France", 5, false));
		teams.add(new Team("Germany", 6, false));
		teams.add(new Team("Netherlands", 7, false));
		teams.add(new Team("Japan", 8, false));
		teams.add(new Team("Brazil", 9, false));
		teams.add(new Team("Canada", 10, false));
		teams.add(new Team("Switzerland", 21, false));
		teams.add(new Team("Colombia", 22, false));
		teams.add(new Team("Scotland", 23, false));
		teams.add(new Team("Republic of Ireland", 24, false));
		teams.add(new Team("Russia", 25, false));
		teams.add(new Team("Aotearoa New Zealand", 26, true));
		teams.add(new Team("Czechia", 27, false));
		teams.add(new Team("Finland", 28, false));
		teams.add(new Team("Wales", 29, false));
		teams.add(new Team("Poland", 30, false));
		teams.add(new Team("Argentina", 31, false));
		teams.add(new Team("Nigeria", 32, false));
		teams.add(new Team("Australia", 11, true));
		teams.add(new Team("Denmark", 12, false));
		teams.add(new Team("Norway", 13, false));
		teams.add(new Team("Iceland", 14, false));
		teams.add(new Team("China PR", 15, false));
		teams.add(new Team("Austria", 16, false));
		teams.add(new Team("Italy", 17, false));
		teams.add(new Team("Belgium", 18, false));
		teams.add(new Team("Portugal", 19, false));
		teams.add(new Team("Korea Republic", 20, false));

		Tournament worldCup = new Tournament(teams);
		worldCup.setup();
		
	}

}
