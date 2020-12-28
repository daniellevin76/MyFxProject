
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class ImageObject extends Application {
    ImageView imageView;
    Image image;
    File file;
    int magnifyingFactor = 0;



    public ImageObject(){

    }


    @Override
    public void start(Stage primaryStage) {
        // file choser to access file on computer
        FileChooser fileChooser = new FileChooser();
        // create a VBox layout as root node
        VBox root = new VBox();
        // HBox container for menu buttons
        HBox menuContainer = new HBox();
        //VBox container to display the image
        VBox imageContainer = new VBox();

        // Button for loading the image
        Button loadButton = new Button("Load Image");
        // Button to magnify the image
        Button magnifyButton = new Button("Magnify");
        Button shrinkButton = new Button("Shrink");
        Button mirrorButton = new Button("Mirror");
        menuContainer.getChildren().addAll(loadButton, magnifyButton, shrinkButton, mirrorButton);
        root.getChildren().addAll(menuContainer, imageContainer);
        // imageContainer.getChildren().add(imageView);

        Scene scene = new Scene(root, 1400, 800);

        primaryStage.setTitle("Image Editor");
        primaryStage.setScene(scene);

        magnifyButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        //double rand = Math.random() * (500 - 400 + 1) + 400;
                        //print(rand);
                        magnifyingFactor++;
                        double imageWidth = image.getWidth() * (1 + 0.05 * magnifyingFactor);

                        print("test1 image" + image);
                        ImageView tempImageView = getImageView();
                        tempImageView.setImage(image);
                        tempImageView.setFitWidth(imageWidth);
                        tempImageView.setPreserveRatio(true);
                        tempImageView.setSmooth(true);
                        tempImageView.setCache(true);


                        imageContainer.getChildren().remove(imageView);
                        imageContainer.getChildren().add(tempImageView);

                    }
                }
        );


        loadButton.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override

                    public void handle(final ActionEvent e) {

                        file = fileChooser.showOpenDialog(primaryStage);
                          if (file != null) {
                            // print(file);

                            image = new Image(file.toURI().toString());

                            double imageHeight = image.getHeight() * 0.5;
                            double imageWidth = image.getWidth() * 0.5;

                            image = new Image(file.toURI().toString(), imageWidth, imageHeight, false, false);

                            print("test2 image" + image);
                            imageView = new ImageView(image);
                            imageView.setPreserveRatio(true);
                            imageContainer.getChildren().add(imageView);


                        }
                    }
                });

        primaryStage.show();
    }


    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }


    // print method to test my code
    private void print(Object obj) {
        System.out.println(obj);
    }

    private ImageView getImageView() {
        return imageView;
    }

}