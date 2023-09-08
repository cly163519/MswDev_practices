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
		
		List<RealTeam> realTeams = new ArrayList<RealTeam>();
		
		realTeams.add(new RealTeam("Sweden", 1, false));
		realTeams.add(new RealTeam("Spain", 2, false));
		realTeams.add(new RealTeam("USA", 3, false));
		realTeams.add(new RealTeam("England", 4, false));
		realTeams.add(new RealTeam("France", 5, false));
		realTeams.add(new RealTeam("Germany", 6, false));
		realTeams.add(new RealTeam("Netherlands", 7, false));
		realTeams.add(new RealTeam("Japan", 8, false));
		realTeams.add(new RealTeam("Brazil", 9, false));
		realTeams.add(new RealTeam("Canada", 10, false));
		realTeams.add(new RealTeam("Switzerland", 21, false));
		realTeams.add(new RealTeam("Colombia", 22, false));
		realTeams.add(new RealTeam("Scotland", 23, false));
		realTeams.add(new RealTeam("Republic of Ireland", 24, false));
		realTeams.add(new RealTeam("Russia", 25, false));
		realTeams.add(new RealTeam("Aotearoa New Zealand", 26, true));
		realTeams.add(new RealTeam("Czechia", 27, false));
		realTeams.add(new RealTeam("Finland", 28, false));
		realTeams.add(new RealTeam("Wales", 29, false));
		realTeams.add(new RealTeam("Poland", 30, false));
		realTeams.add(new RealTeam("Argentina", 31, false));
		realTeams.add(new RealTeam("Nigeria", 32, false));
		realTeams.add(new RealTeam("Australia", 11, true));
		realTeams.add(new RealTeam("Denmark", 12, false));
		realTeams.add(new RealTeam("Norway", 13, false));
		realTeams.add(new RealTeam("Iceland", 14, false));
		realTeams.add(new RealTeam("China PR", 15, false));
		realTeams.add(new RealTeam("Austria", 16, false));
		realTeams.add(new RealTeam("Italy", 17, false));
		realTeams.add(new RealTeam("Belgium", 18, false));
		realTeams.add(new RealTeam("Portugal", 19, false));
		realTeams.add(new RealTeam("Korea Republic", 20, false));

		Tournament worldCup = new Tournament(realTeams);
		worldCup.setup();
		
	}

}
