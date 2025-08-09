// Imports for working with files, lists, and UI
import java.awt.Color;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import ecs100.UI;

public class UserInterface {
    // List to store all team objects
    private List<Team> teams = new ArrayList<>();//这个为什么不写在team类里??

    // Constructor - initializes UI and reads the data file
    public UserInterface() {
        UI.initialise();
        UI.addButton("List teams", this::listTeams);
        UI.addButton("List coaches", this::listTeamsWithCoaches);
        UI.addButton("List team players", this::listTeamPlayers);
        UI.addButton("List players by position", this::listPositionPlayersOnTeam);
        UI.addButton("Search by height", this::listHeights);
        UI.addButton("Check team lineup", this::checkSelection);
        UI.addButton("Graph player heights", this::graphHeights);

        // Load data from file
        String fileName = "teams.txt"; // change to teams.txt for full data
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String teamName = scanner.nextLine().trim();
                String coachName = scanner.nextLine().trim();
                int numPlayers = Integer.parseInt(scanner.nextLine().trim());
                
                Team team = new Team(teamName, coachName);
                teams.add(team); // add this team to the list//这么多team,只添加一个team?

                for (int i = 0; i < numPlayers; i++) {
                    String posAndName = scanner.nextLine().trim();
                    int height = Integer.parseInt(scanner.nextLine().trim());
                    String birthplace = scanner.nextLine().trim();

                    // Split the first line into positions and name
                    int spaceIndex = posAndName.indexOf(' ');
                    String positionsPart = posAndName.substring(0, spaceIndex);
                    String name = posAndName.substring(spaceIndex + 1);

                    String[] positionsArray = positionsPart.split(",");
                    List<String> positions = new ArrayList<>();
                    for (String pos : positionsArray) {
                        positions.add(pos.trim());
                    }

                    Player player = new Player(name, positions, height, birthplace);
                    team.addPlayer(player);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            UI.printf("Error loading file: %s\n", e);
        }
    }

    // Lists all teams
    public void listTeams() {
        if (teams.isEmpty()) {
            UI.println("No teams to display.");
            return;
        }

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);//为什么不写=new team?
            UI.println("Team: " + team.getName());//不能写成team.name因为name是私有的
        }
    }

    // Lists all teams with their coaches
    public void listTeamsWithCoaches() {
        if (teams.isEmpty()) {
            UI.println("No teams to display.");
            return;
        }

        for (Team team : teams) {
            UI.println("Team: " + team.getName() + ", Coach: " + team.getCoach());
        }
    }

    // Lists all players of a selected team
    public void listTeamPlayers() {
        String teamName = UI.askString("Which team?");
        Team team = getTeamByName(teamName);
        if (team == null) {
            UI.println("No such team.");
            return;
        }
        for (Player player : team.getPlayers()) {
            UI.println(player);
        }
    }

    // Helper: Find team by name
    public Team getTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

    // Lists all players of a team who play a specific position
    public void listPositionPlayersOnTeam() {
        String teamName = UI.askString("Which team?");
        String position = UI.askString("Which position?");
        Team team = getTeamByName(teamName);
        if (team == null) {
            UI.println("No such team.");
            return;
        }
        for (Player player : team.getPlayers()) {
            if (player.getPositions().contains(position)) {
                UI.println(player);
            }
        }
    }

    // Lists players whose height is between a range
    public void listHeights() {
        int min = UI.askInt("Taller than?");
        int max = UI.askInt("Shorter than?");

        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                int h = player.getHeight();
                if (h > min && h < max) {
                    UI.println(player);
                }
            }
        }
    }

    // Check if a selection of players can form a valid lineup
    public void checkSelection() {
        List<Player> selected = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            String name = UI.askString("Enter player " + i + "'s name:");
            Player found = null;
            outer: for (Team team : teams) {
                for (Player p : team.getPlayers()) {
                    if (p.getName().equalsIgnoreCase(name)) {
                        found = p;
                        break outer;
                    }
                }
            }
            if (found != null) {
                selected.add(found);
            } else {
                UI.println("Player not found: " + name);
                return;
            }
        }

        // Now validate positions: no overlap, all 7 covered
        List<String> usedPositions = new ArrayList<>();
        for (Player p : selected) {
            for (String pos : p.getPositions()) {
                if (!usedPositions.contains(pos)) {
                    usedPositions.add(pos);
                    break;
                }
            }
        }

        if (usedPositions.size() >= 7) {
            UI.println("Valid lineup.");
        } else {
            UI.println("Invalid lineup: not enough unique positions.");
        }
    }

    // Graph player heights visually
    public void graphHeights() {
        int x = 10;
        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                int h = player.getHeight();
                UI.setColor(Color.blue);
                UI.fillRect(x, 400 - h, 10, h);
                x += 15;
            }
        }
        //UI.drawImage("TeamPhotos/Amy Steel.jpg", 100, 100);
    }

    public static void main(String[] args) {
        new UserInterface();
    }
}
