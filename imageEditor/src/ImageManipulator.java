import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageManipulator {

    static int counter = 0;
    FileChooser fileChooser;
    File file;
    Stage primaryStage;
   // static Image image;
  //  static ImageView imageView;


    public ImageManipulator() {

    }


    public static ImageView resizeImage(ImageView imageView, Image image, double resizingFactor) {

      //  imageView.getFitWidth();
        print("counter: " + counter);
        double imageWidth = image.getWidth() ;

        if (resizingFactor > 0){
            counter++;

        } else{
            counter--;

        }

        imageView.setFitWidth(imageWidth * (1 + Math.abs(resizingFactor) * counter));

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    //
        //    imageView.setCache(true);


        return imageView;
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }


    public static ImageView rotateImage(ImageView imageView) {


        imageView.getTransforms().add(new Rotate(30, 50, 30));
                /*
                * imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        imageView.setImage(image);*/


        return imageView;
    }

    public static void changeColor(Image image) {

        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();

        WritableImage outPutImage = new WritableImage(imageWidth, imageHeight);
        PixelReader reader = image.getPixelReader();
        //   PixelWriter writer = image.getPixelWriter();
        print(reader.getArgb(5, 5));
        print(reader.getColor(5, 5));
        print(reader.getPixelFormat());
        // int ob = (int) oldColor.getBlue()*255;
        // int or = (int) oldColor.getRed()*255;
        //int og = (int) oldColor.getBreen()*255;
    }
}
