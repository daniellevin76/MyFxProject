
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.awt.Desktop;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiBuilder extends Application {
    static ImageView imageView;
   static Image image;
    FileChooser fileChooser;
    File file;
    Stage primaryStage;


    public GuiBuilder(){

        imageView = new ImageView();


    }


    public void start(Stage primaryStage) throws Exception {


        fileChooser = new FileChooser();


        VBox root = new VBox();
        root.getChildren().addAll(createMenu(), createImageContainer());
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Image Editor");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    private HBox createMenu() {
        // menuContainer to contain all menu buttons
        HBox menuContainer = new HBox();
        // create space between buttons in menuContainer
        menuContainer.setSpacing(10);
        // Button for loading the image
        Button loadButton = new Button("Load Image");
        // set Action on loadButton

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

                            print("test1 imageView" + imageView);

                            imageView.setPreserveRatio(true);
                            imageView.setImage(image);


                            }
                    }
                });
        //button to display use manual
        Button manualButton = new Button("User manual");
        manualButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {



                      File userManual = new File("imageEditor/userManual/userManual.pdf");
                      Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(userManual);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
        );

        // Button to magnify the image
        Button magnifyButton = new Button("Magnify");
        // set action resize on magnifying Button
        magnifyButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // magnifying image view

                     imageView = ImageManipulator.resizeImage(imageView, image, 0.05);

                    }
                }
        );
        // Button to minimize the image
        Button minimizeButton = new Button("Minimize");

        // set action resize on minimize Button
        minimizeButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // magnifying image view



                        imageView = ImageManipulator.resizeImage(imageView, image, -0.05);

                    }
                }
        );
        // Button to rotate the image
        Button rotateButton = new Button("Rotate");

        rotateButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        imageView = ImageManipulator.rotateImage(imageView);


                    }
                }
        );

        // Button to change the color of the image
        Button colorButton = new Button("Transform");

        colorButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        imageView = ImageManipulator.changeColor(imageView);


                    }
                }
        );



        menuContainer.getChildren().addAll(manualButton, loadButton, magnifyButton, minimizeButton, rotateButton, colorButton);
        return menuContainer;
 }

    //create image container
    private VBox createImageContainer() {
        VBox imageContainer = new VBox();
        print("test imageView i createImageContainer" + imageView);
        imageContainer.getChildren().add(imageView);
        return imageContainer;
    }

    private void print(Object obj) {
        System.out.println(obj);
    }


    public static void launchMethod(String[] args) {
        launch(args);
    }

}
