package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class homePageController {
    private mainController mainController;

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void goToModuleProgressAll(ActionEvent event) {
        mainController.switchScene("moduleProgressAll.fxml");
    }

    @FXML
    private void goToModuleProgressSelected(ActionEvent event) {
        mainController.switchScene("moduleProgressSelected.fxml");
    }

    @FXML
    private void goToTopWebcasts(ActionEvent event) {
        mainController.switchScene("topWebcasts.fxml");
    }

    @FXML
    private void goToCompletedCourse(ActionEvent event) {
        mainController.switchScene("completedCourse.fxml");
    }
}
