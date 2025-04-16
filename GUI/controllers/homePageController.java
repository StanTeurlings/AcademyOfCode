package GUI.controllers;

import Database.CourseDAO;
import Database.DatabaseConnection;
import Database.StudentDAO;
import Database.WebcastDAO;
import Domain.Class.Course;
import Domain.Class.Student;
import Domain.Class.Webcast;
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
import Database.EnrollmentDAO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class homePageController implements Initializable {
    private mainController mainController;

    private final StudentDAO studentDAO = new StudentDAO(new DatabaseConnection());
    private final WebcastDAO webcastDAO = new WebcastDAO(new DatabaseConnection());
    private final CourseDAO courseDAO = new CourseDAO(new DatabaseConnection());
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO(new DatabaseConnection());

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public void setMainController(mainController mainController) {
        this.mainController = mainController;
    }

    // Navigatie
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

    // Velden uit addStudent.fxml
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

    // Tabellen en lijsten
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
    private ListView<String> courseProgressList; // voor 'per student' view
    @FXML
    private ListView<String> completedCourseList;
    @FXML
    private ListView<String> webcastProgressList; // ListView voor voortgang

    // ChoiceBoxes
    @FXML
    private ChoiceBox<Student> studentChoiceBox;
    @FXML
    private ChoiceBox<Webcast> webcastChoiceBox;
    @FXML
    private ChoiceBox<Course> courseChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Gender vullen
        if (genderBox != null) {
            genderBox.setItems(FXCollections.observableArrayList(Gender.values()));
        }

        // Studententabel
        if (studentTable != null) {
            idColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getId()).asObject());
            nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
            emailColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
            studentTable.setItems(studentList);
        }

        // Alle studentenlijst
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
                            setText(student.getName() + " (" + student.getEmail() + ")");
                        }
                    }
                });
            } catch (SQLException e) {
                showAlert("Fout bij ophalen studenten: " + e.getMessage());
            }
        }

        // Top webcasts
        if (webcastTopThreeList != null) {
            try {
                ObservableList<String> topWebcasts = webcastDAO.getTop3Webcasts();
                webcastTopThreeList.setItems(topWebcasts);
            } catch (SQLException e) {
                showAlert("Fout bij ophalen webcasts: " + e.getMessage());
            }
        }

        // StudentChoiceBox
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
                showAlert("Fout bij laden studenten: " + e.getMessage());
            }
        }

        // WebcastChoiceBox
        if (webcastChoiceBox != null) {
            try {
                ObservableList<Webcast> webcasts = webcastDAO.getAllWebcasts();
                webcastChoiceBox.setItems(webcasts);
                webcastChoiceBox.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Webcast webcast) {
                        return webcast == null ? "" : webcast.getTitle();
                    }

                    @Override
                    public Webcast fromString(String string) {
                        return null;
                    }
                });
            } catch (SQLException e) {
                showAlert("Fout bij laden webcasts: " + e.getMessage());
            }
        }

        // CourseChoiceBox
        if (courseChoiceBox != null) {
            try {
                ObservableList<Course> courses = courseDAO.getAllCourses();
                courseChoiceBox.setItems(courses);
                courseChoiceBox.setConverter(new StringConverter<>() {
                    @Override
                    public String toString(Course course) {
                        return course == null ? "" : course.getName();
                    }

                    @Override
                    public Course fromString(String string) {
                        return null;
                    }
                });
            } catch (SQLException e) {
                showAlert("Fout bij laden courses: " + e.getMessage());
            }
        }
    }

    // Student toevoegen
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
                isEmpty(country) || isEmpty(city) || isEmpty(address) || isEmpty(houseNr) || isEmpty(postal)) {
            showAlert("Alle velden moeten ingevuld zijn.");
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Ongeldig e-mailadres");
            return;
        }

        if (!isValidBirthDate(birth)) {
            showAlert("Geboortedatum mag niet in de toekomst liggen.");
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

    // Webcast voortgang zoeken
    @FXML
    private void handleSearchWebcastProgress(ActionEvent event) {
        Student selectedStudent = studentChoiceBox.getValue();
        Webcast selectedWebcast = webcastChoiceBox.getValue();

        if (selectedStudent == null || selectedWebcast == null) {
            showAlert("Selecteer een student en een webcast.");
            return;
        }

        try {
            ObservableList<String> progress = studentDAO.getWebcastProgress(
                    selectedStudent.getId(),
                    selectedWebcast.getId());

            if (progress.isEmpty()) {
                webcastProgressList.setItems(FXCollections.observableArrayList("Geen voortgang gevonden."));
            } else {
                webcastProgressList.setItems(progress);
            }
        } catch (SQLException e) {
            showAlert("Fout bij ophalen voortgang: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchCompletedCourse(ActionEvent event) {
        Course selectedCourse = courseChoiceBox.getValue();

        if (selectedCourse == null) {
            showAlert("Selecteer een course.");
            return;
        }

        try {
            int completedCount = courseDAO.countStudentsInCourse(selectedCourse.getId());

            ObservableList<String> result = FXCollections.observableArrayList(
                    "Aantal studenten die de course volledig voltooid hebben: " + completedCount);
            completedCourseList.setItems(result);
        } catch (SQLException e) {
            showAlert("Fout bij ophalen aantal voltooide studenten: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchCourseProgressAll(ActionEvent event) {
        Course selectedCourse = courseChoiceBox.getValue();

        if (selectedCourse == null) {
            showAlert("Selecteer een course.");
            return;
        }

        try {
            ObservableList<String> progress = courseDAO.getModuleProgressForCourse(selectedCourse.getId());
            if (progress.isEmpty()) {
                webcastProgressList.setItems(FXCollections.observableArrayList("Geen voortgang gevonden."));
            } else {
                webcastProgressList.setItems(progress);
            }
        } catch (SQLException e) {
            showAlert("Fout bij ophalen course voortgang: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchCourseProgressForStudent(ActionEvent event) {
        Student selectedStudent = studentChoiceBox.getValue();
        Course selectedCourse = courseChoiceBox.getValue();

        if (selectedStudent == null || selectedCourse == null) {
            showAlert("Selecteer zowel een student als een course.");
            return;
        }

        try {
            ObservableList<String> progress = courseDAO.getModuleProgressForStudent(
                    selectedCourse.getId(),
                    selectedStudent.getId());

            if (progress.isEmpty()) {
                courseProgressList.setItems(FXCollections.observableArrayList("Geen voortgang gevonden."));
            } else {
                courseProgressList.setItems(progress);
            }
        } catch (SQLException e) {
            showAlert("Fout bij ophalen course voortgang: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddEnrollment(ActionEvent event) {
        Student selectedStudent = studentChoiceBox.getValue();
        Course selectedCourse = courseChoiceBox.getValue();

        if (selectedStudent == null || selectedCourse == null) {
            showAlert("Selecteer zowel een student als een course.");
            return;
        }

        try {
            enrollmentDAO.addEnrollment(selectedStudent.getId(), selectedCourse.getId());
            showConfirmation("Inschrijving succesvol toegevoegd!");

            studentChoiceBox.setValue(null);
            courseChoiceBox.setValue(null);
        } catch (Exception e) {
            showAlert("Fout bij toevoegen inschrijving: " + e.getMessage());
        }
    }

    // Validatie en UI helpers
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPostalCode(String code) {
        return code.matches("^\\d{4}[A-Za-z]{2}$");
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
}
