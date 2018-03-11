package cs2410.assn4.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import cs2410.assn4.Controller.Methods;

import java.util.Optional;

/**
 * A class that creates a user interface to allow the user to view the images
 * stored in images.data, as well as add new images and remove existing ones
 *
 * @author Amitesh
 * @version 1.0
 */
public class UserInterface extends Application
{
    /**
     * A scene to display the images in
     */
    private Scene scene;
    /**
     * A pane to store the ImageViews
     */
    private Pane pane;
    /**
     * Buttons for navigating and controlling the image viewer
     */
    private Button nextButton, prevButton, addButton, deleteButton;
    /**
     * A controller with several methods used to navigate and control the image viewer
     */
    private Methods methods = new Methods();
    /**
     * An image that will be displayed
     */
    private Image image;


    /**
     * Start function that creates a graphical user interface for the image viewer.
     * The interface features 4 buttons (next, prev, add, and remove) for navigating
     * and controlling the image viewer.
     * @param primaryStage The box the images will be displayed in
     */
    @Override
    public void start(Stage primaryStage)
    {
        pane = new Pane();
        scene = new Scene(pane, 800, 650);
        primaryStage.setScene(scene);

        ImageView imageView = new ImageView();

        image = methods.nextImage();

        imageView.setPreserveRatio(true);
        imageView.setFitWidth(770);
        imageView.setFitHeight(570);
        imageView.setLayoutX(15);
        imageView.setLayoutY(15);

        imageView.setImage(image);
        primaryStage.setTitle(methods.getTitle());

        pane.getChildren().add(imageView);


        nextButton = new Button("Next");
        nextButton.setPrefWidth(75);
        nextButton.setLayoutX(212);
        nextButton.setLayoutY(600);
        nextButton.setOnAction(event -> {
            image = methods.nextImage();
            imageView.setImage(image);
            primaryStage.setTitle(methods.getTitle());
        });

        pane.getChildren().add(nextButton);

        prevButton = new Button("Prev");
        prevButton.setPrefWidth(75);
        prevButton.setLayoutX(312);
        prevButton.setLayoutY(600);
        prevButton.setOnAction(event -> {
            image = methods.prevImage();
            imageView.setImage(image);
            primaryStage.setTitle(methods.getTitle());
        });

        pane.getChildren().add(prevButton);

        addButton = new Button("Add");
        addButton.setPrefWidth(75);
        addButton.setLayoutX(412);
        addButton.setLayoutY(600);
        addButton.setOnAction(event -> {
            TextInputDialog inputDialog = new TextInputDialog();
            Optional<String> input;

            inputDialog.setTitle("Add Picture");
            inputDialog.setHeaderText(null);
            inputDialog.setContentText("Enter url");
            inputDialog.setGraphic(null);
            inputDialog.getEditor().clear();

            input = inputDialog.showAndWait();

            boolean noImages = methods.noImages();

            String url, title;
            if(input.isPresent() && input.get().compareTo("") != 0)
            {
                url = input.get();

                inputDialog = new TextInputDialog();
                inputDialog.setTitle("Add Picture");
                inputDialog.setGraphic(null);
                inputDialog.setHeaderText(null);
                inputDialog.setContentText("Enter Title");
                inputDialog.getEditor().clear();
                input = inputDialog.showAndWait();


                if(input.isPresent() && input.get().compareTo("") != 0)
                {
                    title = input.get();
                    image = methods.addImage(url, title);
                    if (noImages)
                    {
                        nextButton.setDisable(false);
                        prevButton.setDisable(false);
                        deleteButton.setDisable(false);
                    }
                    imageView.setImage(image);
                    primaryStage.setTitle(title);
                }
            }

        });


        pane.getChildren().add(addButton);

        deleteButton = new Button("Remove");
        deleteButton.setPrefWidth(75);
        deleteButton.setLayoutX(512);
        deleteButton.setLayoutY(600);
        deleteButton.setOnAction(event -> {
            image = methods.delImage();
            imageView.setImage(image);
            if(methods.noImages())
            {
                primaryStage.setTitle("No images found");
                nextButton.setDisable(true);
                prevButton.setDisable(true);
                deleteButton.setDisable(true);
            }
            else
            {
                primaryStage.setTitle(methods.getTitle());
            }
        });
        pane.getChildren().add(deleteButton);

        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> methods.quit());
    }
}