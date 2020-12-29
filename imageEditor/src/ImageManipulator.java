import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;


public abstract class ImageManipulator {
   static public Color color;

    static public PixelWriter pixelWriter;
    static int counter = 0;
    FileChooser fileChooser;
    File file;
    Stage primaryStage;
    // static Image image;
    //  static ImageView imageView;



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


        //  imageView.getTransforms().add(new Rotate(30, 50, 30));

        // imageView.setTranslateZ(imageView.getBoundsInLocal().getWidth() / 2.0);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        // imageView.setImage(image);


        return imageView;
    }

    public static void changeColor(Image image, int i, int j) {
        String oneColor ;

      ArrayList<Double> colorList = getRGB_PixelValues(image);
      colorList.add(0.50);
        colorList.add(0.40);
        colorList.add(0.60);

      oneColor = returnRGB_MaxValue(colorList);
       changePixelColor(oneColor, i, j);
       print("oneColor value: " + oneColor);
    }

    private static void changePixelColor(String colorString, int i, int j) {
        switch(colorString){
        case "red": setPixelColor("red", i, j);
        case "blue": setPixelColor("blue", i, j);
        case "green": setPixelColor("green", i, j);
        default: setPixelColor("black", i, j);
    }
    }

    private static void setPixelColor(String RGB_color, int i, int j) {
        switch(RGB_color){
            case "red":
                color = Color.RED;
                pixelWriter.setColor(i,j,color);;
            case "blue":
                color = Color.BLUE;
                pixelWriter.setColor(i,j,color);;
            case "green":
                color = Color.GREEN;
                pixelWriter.setColor(i,j,color);;
            default:  color = Color.BLACK;
                pixelWriter.setColor(i,j,color);;
        }
    }


    private static String returnRGB_MaxValue(ArrayList<Double> colorList) {
        // red, green, blue
        Double answer = colorList.get(0); // set answer to red
        String colorString = "red";
        // if the value of red is less than the value for green, set answer green
        if(colorList.get(0)<colorList.get(1)){
            answer = colorList.get(1);
            colorString = "green";
        }
        if(answer < colorList.get(2)){
            //answer = colorList.get(2);
            colorString = "blue";
        }
        return colorString;

    }

    private static ArrayList<Double> getRGB_PixelValues(Image image) {
        Color color = new Color(0, 0, 0, 0);
        PixelReader reader = image.getPixelReader();
        color = reader.getColor(125,125);
        ArrayList<Double> colorList = new ArrayList<Double>();
        colorList.add(color.getRed());
        colorList.add(color.getGreen());
        colorList.add(color.getBlue());
        return colorList;


    }


}
