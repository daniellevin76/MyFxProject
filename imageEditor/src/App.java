
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

/**
 *
 * @web http://java-buddy.blogspot.com
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        FileChooser fileChooser = new FileChooser();

       // ImageView imageView = new ImageView("kol.jpg");
        final Button loadButton = new Button("Load Image");
        VBox root = new VBox();
        HBox menuContainer = new HBox();
        VBox imageContainer = new VBox();
        Button magnifyButton = new Button("Magnify");
        Button shrinkButton = new Button("Shrink");
        Button mirrorButton = new Button("Mirror");
        menuContainer.getChildren().addAll(loadButton, magnifyButton, shrinkButton, mirrorButton);
        root.getChildren().addAll(menuContainer,imageContainer);
       // imageContainer.getChildren().add(imageView);

        Scene scene = new Scene(root, 486, 564);

        primaryStage.setTitle("Image Editor");
        primaryStage.setScene(scene);


        loadButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(primaryStage);

                        if (file != null) {
                            print(file);
                            //openFile(file);
                            Image image = new Image(file.toURI().toString());
                            ImageView imageView = new ImageView(image);
                            //print("test image: " + image);
                            imageView.setImage(image);
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


/*

    private void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    App.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
*/
    private void print(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {

        launch(args);
    }


    }