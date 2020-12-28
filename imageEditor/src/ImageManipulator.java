import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ImageManipulator {

    static int counter = 0;
    FileChooser fileChooser;
    File file;
    Stage primaryStage;
    static Image image;
   static ImageView imageView;



    public ImageManipulator(){

    }


    public static ImageView resizeImage(ImageView imageView, Image image,double resizingFactor){
        counter++;
        imageView.getFitWidth();
        double imageWidth = image.getWidth() * (1 + resizingFactor * counter);
        imageView.setFitWidth(imageWidth);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setImage(image);

        return imageView;
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }

    public static void rotateImage(ImageView imageView, Image image) {
        imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        imageView.setImage(image);
    }

    private static ImageView getImageView() {
        return imageView;
    }

    public static ImageView rotateImage(ImageView imageView) {

                imageView.setRotate(90);


        return imageView;
    }

    public static void changeColor(Image image) {

        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();

        WritableImage outPutImage = new WritableImage(imageWidth, imageHeight);
        PixelReader reader = image.getPixelReader();
     //   PixelWriter writer = image.getPixelWriter();
        print(reader.getArgb(5,5));
       print( reader.getColor(5,5));
       print(reader.getPixelFormat());
       // int ob = (int) oldColor.getBlue()*255;
       // int or = (int) oldColor.getRed()*255;
        //int og = (int) oldColor.getBreen()*255;
    }
}
