import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;                // 球队名称
    private String coach;              // 教练名称
    private List<Player> players;      // 球队中的球员列表

    public Team(String name, String coach) {
        this.name = name;
        this.coach = coach;
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCoach() {
        return coach;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * 返回某一位置的所有球员
     */
    public List<Player> getPlayersByPosition(String position) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            if (player.canPlayPosition(position)) {
                result.add(player);
            }
        }
        return result;
    }

    /**
     * 返回某一身高范围内的球员
     */
    public List<Player> getPlayersByHeightRange(int minHeight, int maxHeight) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            int h = player.getHeight();
            if (h >= minHeight && h <= maxHeight) {
                result.add(player);
            }
        }
        return result;
    }
}
