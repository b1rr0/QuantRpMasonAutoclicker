import java.awt.image.BufferedImage;

public class ScreenProcessor {

    public boolean isMiner(BufferedImage image1) {
        int[][] arr = new int[][]{
                new int[]{1217, 266},
                new int[]{1217, 267},
                new int[]{1217, 268},
                new int[]{1217, 269},
                new int[]{1217, 270},
                new int[]{1215, 270},
                new int[]{1216, 270},
                new int[]{1218, 270},
                new int[]{1219, 271},
                new int[]{1219, 272},
        };
        for (int[] ints : arr) {
            if (image1.getRGB(ints[0], ints[1]) == -144332) {
                return false;
            }
        }
        return true;
    }

    public int[][] findIdenticalPixels(BufferedImage image1, BufferedImage image2) {
        int startX = 565;
        int endX = 1360;
        int startY = 255;
        int endY = 840;
        var arr = new int[1920][1080];
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int pixel1 = image1.getRGB(x, y);
                int pixel2 = image2.getRGB(x, y);
                if (pixel1 == pixel2) {
                    arr[x][y] = 1;
                }
            }
        }
        return arr;
    }
}
