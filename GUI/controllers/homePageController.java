package GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class homePageController {
    private mainController mainController;

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void goToHomePage(ActionEvent event) {
        mainController.switchScene("HomePage.fxml");
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
    private void goToAllModules(ActionEvent event) {
        mainController.switchScene("allModules.fxml");
    }
    
    @FXML
    private void goToWebcastProgressAll(ActionEvent event) {
        mainController.switchScene("webcastProgressAll.fxml");
    }

    @FXML
    private void goToWebcastProgressSelected(ActionEvent event) {
        mainController.switchScene("webcastProgressSelected.fxml");
    }

    @FXML
    private void goToAllWebcasts(ActionEvent event) {
        mainController.switchScene("allWebcasts.fxml");
    }

    @FXML
    private void goToTopWebcasts(ActionEvent event) {
        mainController.switchScene("topWebcasts.fxml");
    }

    @FXML
    private void goToCompletedCourse(ActionEvent event) {
        mainController.switchScene("completedCourse.fxml");
    }

    @FXML
    private void goToStudents(ActionEvent event) {
        mainController.switchScene("manageStudents.fxml");
    }

    @FXML
    private void goToEnrollments(ActionEvent event) {
        mainController.switchScene("manageEnrollments.fxml");
    }

    @FXML
    private void goToAddEnrollment(ActionEvent event) {
        mainController.switchScene("addEnrollment.fxml");
    }

    @FXML
    private void goToAddStudent(ActionEvent event) {
        mainController.switchScene("addStudent.fxml");
    }
}
