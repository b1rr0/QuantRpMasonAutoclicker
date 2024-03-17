import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenMaker {
    private final int screenWidth, screenHeight;

    public ScreenMaker() {
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public BufferedImage createScreen(Robot robot) {
        BufferedImage screenCapture = robot.createScreenCapture(new java.awt.Rectangle(screenWidth, screenHeight));
        BufferedImage blackAndWhiteImage = new BufferedImage(screenCapture.getWidth(), screenCapture.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = blackAndWhiteImage.createGraphics();
        g2d.drawImage(screenCapture, 0, 0, null);
        g2d.dispose();
        return blackAndWhiteImage;
    }
}
