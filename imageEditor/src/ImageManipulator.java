import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;


public abstract class ImageManipulator {
    static ImageView imageView;

    static public PixelWriter pixelWriter;
    static int counter = 0;
    FileChooser fileChooser;
    File file;
    Stage primaryStage;
    GuiBuilder gui = new GuiBuilder();


    public static ImageView resizeImage(ImageView imageView, Image image, double resizingFactor) {

        //  imageView.getFitWidth();
        print("counter: " + counter);
        double imageWidth = image.getWidth();

        if (resizingFactor > 0) {
            counter++;

        } else {
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

        // imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        // imageView.setImage(image);


        return imageView;
    }

    public static ImageView changeColor(ImageView imageView) {


        Image image = imageView.getImage();
        print(image);
        String oneColor;
        print(image.getWidth());
        WritableImage wImage = null;
        for (int col = 0; col < image.getWidth(); col++) {

            for (int row = 0; row < image.getHeight(); row++) {


                ArrayList<Double> colorList = getRGB_PixelValues(image, col, row);
                oneColor = returnRGB_MaxValue(colorList);
              wImage =  setPixelColor(imageView, image, oneColor, col, row);
                //    print("oneColor value: " + oneColor);
            }
        }
        imageView.setImage(wImage);

    return imageView;
    }


    // get the color values of a pixel and return an arrayList with those values
    private static ArrayList<Double> getRGB_PixelValues(Image image, int col, int row) {
        Color color = new Color(0, 0, 0, 0);

        PixelReader reader = image.getPixelReader();
        color = reader.getColor(col, row);
        ArrayList<Double> colorList = new ArrayList<Double>();
        colorList.add(color.getRed());
        colorList.add(color.getGreen());
        colorList.add(color.getBlue());
        return colorList;


    }

    //Check which value in colorList is highest and return its corresponding color (String)

    private static String returnRGB_MaxValue(ArrayList<Double> colorList) {

        // red, green, blue
        Double answer = colorList.get(0); // set answer to red
        String colorString = "red";
        // if the value of red is less than the value of green, set colorString green
        if (colorList.get(0) < colorList.get(1)) {
            answer = colorList.get(1);
            colorString = "green";
        }
        // Check the previous answer against blue, if value is less set colorString blue
        if (answer < colorList.get(2)) {
            //answer = colorList.get(2);
            colorString = "blue";
        }
        return colorString;

    }


    // set color to red, green or blue depending on the color input and create a pixel with that color
    private static WritableImage setPixelColor(ImageView imageView, Image image, String colorString, int col, int row) {
        // Create WritableImage
        //Image image;

        Color color;
        // print("image in setPixelColor: " + image);
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();

        switch (colorString) {
            case "red":
                color = Color.DARKRED;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            default:
                color = Color.DARKVIOLET;
                break;
        }

        pixelWriter.setColor(col, row, Color.YELLOW);
       // print(imageView);

       
        return wImage;
    }

}
