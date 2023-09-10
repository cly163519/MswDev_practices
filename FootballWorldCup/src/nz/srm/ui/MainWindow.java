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
		
		teams.add(new Team("Sweden", 1, 0));
		teams.add(new Team("Spain", 2, 0));
		teams.add(new Team("USA", 3, 0));
		teams.add(new Team("England", 4, 0));
		teams.add(new Team("France", 5, 0));
		teams.add(new Team("Germany", 6, 0));
		teams.add(new Team("Netherlands", 7, 0));
		teams.add(new Team("Japan", 8, 0));
		teams.add(new Team("Brazil", 9, 0));
		teams.add(new Team("Canada", 10, 0));
		teams.add(new Team("Switzerland", 21, 0));
		teams.add(new Team("Colombia", 22, 0));
		teams.add(new Team("Vietnam", 41, 0));
		teams.add(new Team("Republic of Ireland", 24, 0));
		teams.add(new Team("Panama", 25, 0));
		teams.add(new Team("Aotearoa New Zealand", 26, 0));
		teams.add(new Team("Czechia", 27, 0));
		teams.add(new Team("Jamaica", 28, 0));
		teams.add(new Team("Philippines", 48, 0));
		teams.add(new Team("Haiti", 50, 0));
		teams.add(new Team("Argentina", 31, 0));
		teams.add(new Team("Nigeria", 32, 0));
		teams.add(new Team("Australia", 11, 0));
		teams.add(new Team("Denmark", 12, 0));
		teams.add(new Team("Norway", 13, 0));
		teams.add(new Team("Morocco", 34, 0));
		teams.add(new Team("China PR", 15, 0));
		teams.add(new Team("Costa Rica", 31, 0));
		teams.add(new Team("Italy", 17, 0));
		teams.add(new Team("Zambia", 25, 0));
		teams.add(new Team("Portugal", 19, 0));
		teams.add(new Team("Korea Republic", 20, 0));

		char firstgroup = 'A';
		int numGroups = teams.size() / 4;
		TournamentStructure structure = new WorldCupStructure(numGroups, firstgroup);
		TournamentScheduler scheduler = new WorldCupScheduler(teams.size(), numGroups, firstgroup);
		FootballTournament worldCup = new FootballTournament("Women's World Cup", 2023, structure, scheduler);
		
		worldCup.setup(teams);
		Team winner = worldCup.simulate();
		
		System.out.println("\nCONGRATULATIONS TO " + winner.getName() + ", WINNERS OF THE " + worldCup.getFullName());
		
	}

}
