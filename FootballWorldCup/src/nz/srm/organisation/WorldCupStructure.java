package nz.srm.organisation;

import java.util.*;

import nz.srm.teams.*;

public class WorldCupStructure extends TournamentStructure {

	private int numGroups;
	private char group1;
	
	public WorldCupStructure(int numGroups, char groupID) {
		super();
		this.group1 = groupID;
		this.numGroups = numGroups;
	}

	@Override
	public void addTeams(List<Team> newTeams) {
		
		int numTeams = newTeams.size();
		int teamsPerGroup = numTeams / numGroups;
		boolean[] assigned = new boolean[numTeams];
		newTeams.sort(new TeamRankComparator());
		
		for (int gNum = 0; gNum < numGroups; gNum++) {
			String groupName = Character.toString(group1 + gNum);
			Team t = newTeams.get(gNum);
			this.teams.put(groupName + "1", t);
			for (int seed = 2; seed <= teamsPerGroup; seed++) {
				while (true) {
					int pick = (int) (Math.random() * numGroups);
					int selection = pick + ((seed - 1) * numGroups);
					if (!assigned[selection]) {
						t = newTeams.get(selection);
						this.teams.put(groupName + seed, t);
						assigned[selection] = true;
						break;
					}
				}
			}
		}
		
	}
	
	@Override
	public List<String> getTags() {
		List<String> tags = new ArrayList<String>();
		
		for (int loop = 0; loop < this.numGroups; loop++) {
			tags.add(Character.toString(group1 + loop));
		}
		
		return tags;
	}
		
}
