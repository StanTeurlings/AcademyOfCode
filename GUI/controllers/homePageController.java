package GUI.controllers;

import Database.DatabaseConnection;
import Database.StudentDAO;
import Database.WebcastDAO;
import Domain.Class.Student;
import Domain.Enummeration.Gender;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class homePageController implements Initializable {
    private mainController mainController;

    private final StudentDAO studentDAO = new StudentDAO(new DatabaseConnection());
    private final WebcastDAO webcastDAO = new WebcastDAO(new DatabaseConnection());
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    // Navigatie van views
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

    // Velden voor student toevoegen (addStudent.fxml)
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

    // Optioneel: student tabel en lijsten (voor andere schermen)
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private ListView<Student> allStudentList;
    @FXML
    private ListView<String> webcastTopThreeList;
    @FXML
    private ChoiceBox<Student> studentChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Gender keuze vullen
        if (genderBox != null) {
            genderBox.setItems(FXCollections.observableArrayList(Gender.values()));
        }

        // Tabel studentinfo (optioneel andere schermen)
        if (studentTable != null) {
            idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()).asObject());
            nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
            emailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
            studentTable.setItems(studentList);
        }

        // Lijst met alle studenten
        if (allStudentList != null) {
            try {
                ObservableList<Student> studentsFromDB = studentDAO.getAllStudents();
                allStudentList.setItems(studentsFromDB);
                allStudentList.setCellFactory(param -> new ListCell<>() {
                    @Override
                    protected void updateItem(Student student, boolean empty) {
                        super.updateItem(student, empty);
                        if (empty || student == null) {
                            setText(null);
                        } else {
                            setText(student.getName() + " (" + student.getEmail() + ") - " +
                                    student.getGender().toString().toLowerCase() + " " + student.getBirthDate());
                        }
                    }
                });
            } catch (SQLException e) {
                showAlert("Fout bij het ophalen van studenten: " + e.getMessage());
            }
        }

        // Top 3 webcasts lijst
        if (webcastTopThreeList != null) {
            try {
                ObservableList<String> topWebcasts = webcastDAO.getTop3Webcasts();
                webcastTopThreeList.setItems(topWebcasts);
            } catch (SQLException e) {
                showAlert("Fout bij ophalen top 3 webcasts: " + e.getMessage());
            }
        }

        // ChoiceBox met alle studenten
        if (studentChoiceBox != null) {
            try {
                ObservableList<Student> students = studentDAO.getAllStudents();
                studentChoiceBox.setItems(students);
                studentChoiceBox.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Student student) {
                        return student == null ? "" : student.getName();
                    }

                    @Override
                    public Student fromString(String string) {
                        return null;
                    }
                });
            } catch (SQLException e) {
                showAlert("Fout bij laden van studentlijst voor keuze: " + e.getMessage());
            }
        }
    }

    // 🔹 Toevoegen van student via formulier in addStudent.fxml
    @FXML
    private void handleAddStudentForm(ActionEvent event) {
        String name = nameField.getText();
        String email = emailField.getText();
        LocalDate birth = birthDatePicker.getValue();
        Gender gender = genderBox.getValue();
        String country = countryField.getText();
        String city = cityField.getText();
        String address = addressField.getText();
        String houseNr = houseNumberField.getText();
        String postal = postalCodeField.getText();

        if (isEmpty(name) || isEmpty(email) || birth == null || gender == null ||
                isEmpty(country) || isEmpty(city) || isEmpty(address) ||
                isEmpty(houseNr) || isEmpty(postal)) {
            showAlert("Alle velden moeten ingevuld zijn.");
            return;
        }

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

        Student student = new Student(email, name, birth, gender, address, houseNr, postal, city, country);

        try {
            studentDAO.addStudent(student);
            showConfirmation("Student succesvol toegevoegd!");
            clearForm();
        } catch (Exception e) {
            showAlert("Fout bij toevoegen student: " + e.getMessage());
        }
    }

    // 🧼 Helpers
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
        alert.setTitle("Fout");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showConfirmation(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gelukt");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
