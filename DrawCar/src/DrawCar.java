import java.awt.Color;
import ecs100.*;

public class DrawCar {

    public DrawCar() {
        UI.addButton("Draw Cars", this::drawLots);
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * 主交互方法：循环获取用户输入，并绘制多辆车
     */
    public void drawLots() {
        while (true) {
            String bodyColor = UI.askString("Body color (or type 'done' to finish):");//用户交互层
            if (bodyColor.equalsIgnoreCase("done")) {
                break;
            }

            String wheelColor = UI.askString("Wheel color:");
            double x = UI.askDouble("X position:");
            double y = UI.askDouble("Y position:");
            double length = UI.askDouble("Car length:");
            double height = UI.askDouble("Car height:");

            this.drawCar(x, y, length, height, bodyColor, wheelColor);//功能层
        }
    }

    /**
     * 绘制一辆车（主体 + 轮子）
     */
    public void drawCar(double x, double y, double length, double height, String bodyColor, String wheelColor) {
        // 设置车身颜色
        UI.setColor(this.convertColor(bodyColor));

        // 车身底部矩形
        UI.fillRect(x, y, length, height * 0.6);
        // 车身上部矩形（车顶）
        UI.fillRect(x + length * 0.25, y - height * 0.4, length * 0.5, height * 0.4);

        // 设置轮胎颜色
        UI.setColor(this.convertColor(wheelColor));
        double wheelRadius = height * 0.25;

        // 左轮
        UI.fillOval(x + length * 0.2 - wheelRadius / 2, y + height * 0.6, wheelRadius, wheelRadius);
        // 右轮
        UI.fillOval(x + length * 0.8 - wheelRadius / 2, y + height * 0.6, wheelRadius, wheelRadius);
    }

    /**
     * 将用户输入的颜色名转换为 Java Color 对象
     */
    public Color convertColor(String name) {
        name = name.toLowerCase();
        if (name.equals("black")) {
            return Color.black;
        } else if (name.equals("red")) {
            return Color.red;
        } else if (name.equals("blue")) {
            return Color.blue;
        } else if (name.equals("green")) {
            return Color.green;
        } else if (name.equals("yellow")) {
            return Color.yellow;
        } else {
            UI.println("Unknown color: " + name + ". Defaulting to black.");
            return Color.black;
        }
    }

    public static void main(String[] args) {
        UI.initialise();
        new DrawCar();
    }
}
