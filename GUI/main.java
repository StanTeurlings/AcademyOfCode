package GUI;

import GUI.controllers.mainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        mainController mainController = new mainController(primaryStage);
        mainController.switchScene("homePage.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
