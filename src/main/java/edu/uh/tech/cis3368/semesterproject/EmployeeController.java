package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class EmployeeController implements Initializable {
    // TableView variables
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> colLastName;
    public TableColumn<Employee, String> colFirstName;
    public TableColumn<Employee, String> colPhone;
    public TableColumn<Employee, String> colEmail;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    // TextFields
    public TextField lastName;
    public TextField firstName;
    public TextField phone;
    public TextField email;

    // Buttons
    public Button btnAddEmployee;
    public Button btnDeleteEmployee;
    public Button btnUpdateEmployee;

    private String VALUES_MISSING_HEADER = "Required Fields Missing";
    private String VALUES_MISSING_CONTENT = "Please provide values for all fields";

    private String DUP_EMAIL_HEADER = "Duplicate Email Found";
    private String DUP_EMAIL_CONTENT = "The email provided has already been used";
    private String EMAIL_CONSTRAINT = "EMPLOYEE_EMAIL_UINDEX";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetButtons();
        setColumnProperties();

        employeeRepository.findAll().forEach(employeeList::add);
        employeeTableView.setItems(employeeList);

        employeeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Employee employee = newSelection;
            if(employee != null) {
                btnAddEmployee.setDisable(true);
                btnUpdateEmployee.setDisable(false);
                btnDeleteEmployee.setDisable(false);

                lastName.setText(employee.getLastName());
                firstName.setText(employee.getFirstName());
                phone.setText(employee.getPhone());
                email.setText(employee.getEmail());
            }
        });

    }

    @FXML
    private void setBtnAddEmployee(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(VALUES_MISSING_HEADER);
        alert.setContentText(VALUES_MISSING_CONTENT);

        Employee employee = new Employee();

        if(lastName.getText().isEmpty() || firstName.getText().isEmpty()
            || phone.getText().isEmpty() || email.getText().isEmpty()) {
            alert.show();
        }

        employee.setLastName(lastName.getText());
        employee.setFirstName(firstName.getText());
        employee.setPhone(phone.getText());
        employee.setEmail(email.getText());

        try {
            employeeRepository.saveAndFlush(employee);
            clearTextFields();
            employeeTableView.getSelectionModel().clearSelection();
            refreshTable();
        } catch (Exception e) {
            showFieldError(e);
        }
    }

    @FXML
    private void setBtnUpdateEmployee(ActionEvent actionEvent) {
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();

        employee.setFirstName(firstName.getText());
        employee.setLastName(lastName.getText());
        employee.setPhone(phone.getText());
        employee.setEmail(email.getText());

        try {
            employeeRepository.saveAndFlush(employee);
            clearTextFields();
            employeeTableView.getSelectionModel().clearSelection();
            refreshTable();
            resetButtons();
        } catch (Exception e) {
            showFieldError(e);
        }
    }

    @FXML
    private void setBtnDeleteEmployee(ActionEvent actionEvent) {
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();
        String confirm = "Are you sure you want to delete this employee?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirm);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            employeeRepository.delete(employee);
            clearTextFields();
            refreshTable();
        }
    }

    @FXML
    private void getSelectedEmployee(MouseEvent mouseEvent) {
        System.out.println("Selected Employee.");
    }

    private void resetButtons() {
        btnAddEmployee.setDisable(false);
        btnUpdateEmployee.setDisable(true);
        btnDeleteEmployee.setDisable(true);
    }

    private void setColumnProperties() {
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void refreshTable() {
        employeeList.clear();
        employeeRepository.findAll().forEach(employeeList::add);
        employeeTableView.setItems(employeeList);
    }

    private void clearTextFields() {
        lastName.clear();
        firstName.clear();
        phone.clear();
        email.clear();
    }

    private void showFieldError(Exception e) {
        var message = e.getMessage();
        Alert alert = new Alert(Alert.AlertType.ERROR);

        if(message.contains(EMAIL_CONSTRAINT)) {
            alert.setHeaderText(DUP_EMAIL_HEADER);
            alert.setContentText(DUP_EMAIL_CONTENT);
        }

        alert.setHeaderText(VALUES_MISSING_HEADER);
        alert.setContentText(VALUES_MISSING_CONTENT);

        alert.show();
    }

}
