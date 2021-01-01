import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.*;


public abstract class ImageManipulator {

    static int counter = 0;


    // resize image to either enlarge or minimize depending on the resizing factor argument and return
    // the resized imageView
    public static ImageView resizeImage(ImageView imageView, Image image, double resizingFactor) {


        double imageWidth = image.getWidth();

        if (resizingFactor > 0) {
            counter++;

        } else {
            counter--;

        }

        imageView.setFitWidth(imageWidth * (1 + Math.abs(resizingFactor) * counter));

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);



        return imageView;
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }


    // Takes in imageView as a parameter, makes a rotation of 5 degrees
    // to imageView and returns the transformed imageView
    public static ImageView rotateImage(ImageView imageView) {




        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        // instantiating the Rotate class.
        Rotate rotate = new Rotate();

        //setting properties for the rotate object.
        rotate.setAngle(5);
        rotate.setPivotX(50);
        rotate.setPivotY(50);

        //rotating the imageView
        imageView.getTransforms().add(rotate);



       // imageView.setRotate(value + 5);


        return imageView;
    }

    public static ImageView changeColor(ImageView imageView) {


        Image image = imageView.getImage();

        Color oneColor;
        WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        for (int row = 0; row < image.getHeight(); row++) {

            for (int col = 0; col < image.getWidth(); col++) {


                ArrayList<Double> colorList = getRGB_PixelValues(image, col, row);

                oneColor = returnRGB_MaxValue(colorList);

                pixelWriter.setColor(col,row,oneColor);
              //  wImage =  setPixelColor(imageView, image, oneColor, col, row);


            }
        }

        imageView.setImage(wImage);

    return imageView;
    }


    // get the color values of a pixel and return an arrayList with those values
    private static ArrayList<Double> getRGB_PixelValues(Image image, int col, int row) {
        Color color;

        PixelReader reader = image.getPixelReader();
        color = reader.getColor(col, row);
        ArrayList<Double> colorList = new ArrayList<Double>();
        colorList.add(color.getRed());
        colorList.add(color.getGreen());
        colorList.add(color.getBlue());
        return colorList;


    }

    //Check which value in colorList is highest and return its corresponding color (String)

    private static Color returnRGB_MaxValue(ArrayList<Double> colorList) {

        // red, green, blue
      Color color = Color.DARKRED;
        Double answer = colorList.get(0); // set answer to red
        if (colorList.get(0).equals(colorList.get(1)) & colorList.get(1).equals(colorList.get(2))){
            color = Color.CORAL;
        }

        // if the value of red is less than the value of green, set colorString green
        if (colorList.get(0) < colorList.get(1)) {
            answer = colorList.get(1);
            color = Color.GREEN;
        }
        // Check the previous answer against blue, if value is less set colorString blue
        if (answer < colorList.get(2)) {
         color = Color.BLUE;
        }
        return color;

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
            case "violet":
                color = Color.DARKVIOLET;
                break;
            default: color = Color.VIOLET;
        }

        pixelWriter.setColor(col, row, color);
       // print(imageView);

       
        return wImage;
    }

}
