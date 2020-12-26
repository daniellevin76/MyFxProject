import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageObject {

    public ImageObject() {

    }

    public BufferedImage readImage() {
        // File inputFile = new File("kol.jpg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource("kol.jpg"));
            // BufferedImage image = ImageIO.read(inputFile);
            if (image == null) {
                System.out.println("NULL!!!");
            } else {
                System.out.println("YES: " + image);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return image;

    }


    }


