package algorithms.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by frankrod on 2/18/17.
 */
public class ImageToolbox {
    private ImageToolbox() {}

    // TODO - written in a rush, needs to be cleaned up
    public static BufferedImage boxBlur(BufferedImage img, int lossFactor) {
        BufferedImage result = new BufferedImage(
                img.getWidth(),
                img.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        System.err.println("original = " + img.getWidth() + " " + img.getHeight());
        int verticalBlocks = img.getHeight() / lossFactor;
        int horizontalBlocks = img.getWidth() / lossFactor;
        for (int i = 0; i < verticalBlocks; i++) {
            for (int j = 0; j < horizontalBlocks; j++) {
                int[] newRGB = new int[3];
                int ct = 0;
                for (int ii = i * lossFactor; ii < (i+1) * lossFactor && ii < img.getHeight(); ii++) {
                    for (int jj = j * lossFactor; jj < (j+1) * lossFactor && jj < img.getWidth(); jj++) {
                        int curColor = img.getRGB(jj, ii);
                        for (int x = 0; x < 3; x++) {
                            newRGB[2 - x] += curColor & 0xFF;
                            curColor >>= 8;
                        }
                        ct++;
                    }
                }

                for (int x = 0; x < 3; x++) {
                    newRGB[x] /= ct;
                }
                for (int ii = i * lossFactor; ii < (i+1) * lossFactor && ii < img.getHeight(); ii++) {
                    for (int jj = j * lossFactor; jj < (j+1) * lossFactor && jj < img.getWidth(); jj++) {
                        //System.err.println(jj + " " + ii);
                        result.setRGB(jj, ii, 0xFF000000 | (newRGB[0] << 16) | (newRGB[1] << 8) | newRGB[2]);
                    }
                }
            }
        }

        return result;
    }

}
