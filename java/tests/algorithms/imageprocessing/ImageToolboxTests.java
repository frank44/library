package algorithms.imageprocessing;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by frankrod on 2/18/17.
 */
public class ImageToolboxTests {

    // TODO - weak test, make it better
    @Test
    public void example() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
    File inputFile = new File(classLoader.getResource("monalisa.jpg").getFile());
        BufferedImage originalImage = ImageIO.read(inputFile);

        BufferedImage simplified = ImageToolbox.boxBlur(originalImage, 10);
        int rgb = simplified.getRGB(0,0);
        System.err.print("result(0,0) = " + getRed(rgb) + " " + getGreen(rgb) + " " + getBlue(rgb));
        assertNotEquals(getRed(rgb), 0);
        assertNotEquals(getGreen(rgb), 0);
        assertNotEquals(getBlue(rgb), 0);
        ImageIO.write(simplified, "png", new File("outputImg.jpg"));
    }

    private int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int getBlue(int rgb) {
        return (rgb) & 0xFF;
    }
}
