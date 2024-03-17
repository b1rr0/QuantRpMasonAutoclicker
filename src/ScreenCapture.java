import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Set;

class ScreenCapture {
    private final ScreenMaker screenMaker;
    private final ScreenProcessor screenProcessor;
    private final Random random;
    private final MaxIslandCenter island;
    private final MouseController mouseController;

    public ScreenCapture() {
        screenMaker = new ScreenMaker();
        screenProcessor = new ScreenProcessor();
        random = new Random();
        island = new MaxIslandCenter();
        mouseController = new MouseController();
    }

    public void run() {
        try {
            Robot robot = new Robot();
            while (true) {
                BufferedImage fImage = screenMaker.createScreen(robot);
                sleep();

                BufferedImage sImage = screenMaker.createScreen(robot);

                int[][] pixels = screenProcessor.findIdenticalPixels(fImage, sImage);

                Set<Point> maxIsland = island.maxIsland(pixels);
                int[] center = island.calculateCenter(maxIsland);

                if (screenProcessor.isMiner(fImage)) {
                    int X = center[0] + random.nextInt() % 6 + 3;
                    int Y = center[1] + random.nextInt() % 6 + 3;
                    mouseController.move(robot, X, Y);
                    mouseController.click(robot);
                }

                sleep();
            }
        } catch (
                InterruptedException | AWTException e) {
            e.printStackTrace();
        }
    }

    private void sleep() throws InterruptedException {
        int ttw = 333 + random.nextInt() % 100;
        Thread.sleep(ttw);
    }
}
