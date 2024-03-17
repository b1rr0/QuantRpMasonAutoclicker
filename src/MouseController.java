import java.awt.*;
import java.awt.event.InputEvent;

public class MouseController {

    public void move(Robot robot, int targetX, int targetY) {
        int currentX = MouseInfo.getPointerInfo().getLocation().x;
        int currentY = MouseInfo.getPointerInfo().getLocation().y;

        double distance = Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - currentY, 2));

        int steps = (int) Math.max(distance, 200);

        for (int i = 0; i < steps; i++) {
            long delayBetweenSteps = 2;
            var deltaX = (targetX - currentX) / (double) steps;
            var deltaY = (targetY - currentY) / (double) steps;
            currentX += deltaX;
            currentY += deltaY;
            robot.mouseMove(currentX, currentY);

            try {
                Thread.sleep(delayBetweenSteps);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        robot.mouseMove(targetX, targetY);
    }

    public void click(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
