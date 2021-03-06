/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        System.out.println(args[0]);

        if(args.length != 3){
            //three arguments
            System.out.println("three arguments required");
        }

        BufferedImage image;
        try {
            File bmpFile = new File("src/main/resources/" + args[0]);
            image = ImageIO.read(bmpFile);
            System.out.println(image);

            if (args[2].equals("grayscale")) {
                saveFile(grayscale(image), args[1]);
            }
        }
        catch(IOException e) {
            System.out.println(e);

        }
    }

    //run color transformation
    public static BufferedImage grayscale(BufferedImage img) {

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int color = img.getRGB(x, y);
                Color c = new Color(color);
                int avg = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
                Color gray = new Color(avg, avg, avg);
                img.setRGB(x, y, gray.getRGB());
            }
        }
        return img;
    }

    //write it out to a new file
    public static void saveFile(final BufferedImage img, String output){
        try {
            File f;
            f = new File("src/main/resources/" + output);
            ImageIO.write(img, "bmp", f);
        }
        catch(IOException e) {
            System.out.println("File not found");
            System.out.println(e);
        }
    }
}
