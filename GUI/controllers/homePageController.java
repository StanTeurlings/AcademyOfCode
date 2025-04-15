package GUI.controllers;

import Domain.Class.Student;
import Domain.Enummeration.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class homePageController {
    private mainController mainController;

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    // navigatie van views
    @FXML
    private void goToHomePage(ActionEvent event) {
        mainController.switchScene("homepage.fxml");
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
    private void goToWebcastProgressAll(ActionEvent event) {
        mainController.switchScene("webcastProgressAll.fxml");
    }

    @FXML
    private void goToWebcastProgressSelected(ActionEvent event) {
        mainController.switchScene("webcastProgressSelected.fxml");
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
    private void goToAddStudent(ActionEvent event) {
        mainController.switchScene("addStudent.fxml");
    }

    @FXML
    private void goToAddEnrollment(ActionEvent event) {
        mainController.switchScene("addEnrollment.fxml");
    }

    // ids van student velden
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ChoiceBox<Gender> genderBox;
    @FXML
    private TextField countryField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField houseNumberField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private int studentIdCounter = 1;

    @FXML
    public void initialize() {
        if (genderBox != null)
            genderBox.setItems(FXCollections.observableArrayList(Gender.values()));

        if (studentTable != null) {
            idColumn.setCellValueFactory(
                    cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
            nameColumn.setCellValueFactory(
                    cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
            emailColumn.setCellValueFactory(
                    cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmail()));

            studentTable.setItems(studentList);
        }
    }

    @FXML
    private void handleAddStudent(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate birth = birthDatePicker.getValue();
        Gender gender = genderBox.getValue();
        String country = countryField.getText();
        String city = cityField.getText();
        String address = addressField.getText();
        String houseNr = houseNumberField.getText();
        String postal = postalCodeField.getText();

        // isempty check op te kijken of alles ingevuld is
        if (isEmpty(name) || isEmpty(email) || birth == null || gender == null ||
                isEmpty(country) || isEmpty(city) || isEmpty(address) ||
                isEmpty(houseNr) || isEmpty(postal)) {
            showAlert("Alle velden moeten ingevuld zijn.");
            return;
        }

        // validaties op email, birthdate en postcode
        if (!isValidEmail(email)) {
            showAlert("Ongeldig e-mailadres");
            return;
        }
        if (!isValidBirthDate(birth)) {
            showAlert("Geboortedatum mag niet leeg zijn of in de toekomst liggen.");
            return;
        }
        if (!isValidPostalCode(postal)) {
            showAlert("Postcode moet 4 cijfers + 2 letters zijn (bijv. 1234AB)");
            return;
        }

        Student student = new Student(studentIdCounter++, email, name, birth, gender, address, city, country, houseNr,
                postal);
        studentList.add(student);
        clearForm();
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        birthDatePicker.setValue(null);
        genderBox.setValue(null);
        countryField.clear();
        cityField.clear();
        addressField.clear();
        houseNumberField.clear();
        postalCodeField.clear();
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPostalCode(String code) {
        return code != null && code.matches("^\\d{4}[A-Za-z]{2}$");
    }

    private boolean isValidBirthDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input fout");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
