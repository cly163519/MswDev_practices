import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;              // 球员姓名
    private List<String> positions;  // 球员能打的位置列表，例如 ["AB", "CD"]
    private int height;              // 身高（单位：cm）
    private String birthplace;       // 出生地

    /**
     * 构造方法，初始化球员各项信息。
     */
    public Player(String name, List<String> positions, int height, String birthplace) {
        this.name = name;
        this.positions = positions;
        this.height = height;
        this.birthplace = birthplace;
    }

    public String getName() {
        return name;
    }

    public List<String> getPositions() {
        return positions;
    }

    public int getHeight() {
        return height;
    }

    public String getBirthplace() {
        return birthplace;
    }

    /**
     * 判断球员是否能打指定位置。
     */
    public boolean canPlayPosition(String position) {
        return positions.contains(position);
    }

    @Override
    public String toString() {
        return name + " (" + height + "cm, from " + birthplace + ")";
    }
}