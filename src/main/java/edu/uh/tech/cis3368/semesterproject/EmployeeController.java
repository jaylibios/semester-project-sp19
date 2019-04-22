package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class EmployeeController implements Initializable {
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> colLastName;
    public TableColumn<Employee, String> colFirstName;
    public TableColumn<Employee, String> colPhone;
    public TableColumn<Employee, String> colEmail;

    private ObservableList<Employee> employeeList;

    public TextField lastName;
    public TextField firstName;
    public TextField phone;
    public TextField email;

    public Button btnAddEmployee;
    public Button btnDeleteEmployee;
    public Button btnUpdateEmployee;
    public Button btnBack;

    private static final String NEW_EMPLOYEE = "New Employee";
    private static final String UPDATE_EMPLOYEE = "Update Employee";
    private static final String DUP_EMAIL_MESSAGE = "The email provided has already been used.";
    private static final String DUP_EMAIL_HEADER = "Duplicate Email Found";
    private static final String VALUES_MISSING_MESSAGE = "Please provide values for all fields";
    private static final String VALUES_MISSING_HEADER = "Required Fields Missing";
    private static final String DELETE_MESSAGE = "Are you sure you want to delete this employee?";

    @Autowired
    private EmployeeRepository employeeRepository;

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetButtons();
        setColumnProperties();

        employeeList = FXCollections.observableArrayList();
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
        Employee employee = new Employee();

        employee.setLastName(lastName.getText());
        employee.setFirstName(firstName.getText());
        employee.setPhone(phone.getText());
        employee.setEmail(email.getText());

        try {
            employeeRepository.saveAndFlush(employee);
            clearTextFields();
            employeeTableView.getSelectionModel().clearSelection();
            refreshTable();
        } catch (DataIntegrityViolationException e) {
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
        } catch (DataIntegrityViolationException e) {
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
    private void setBtnBack(ActionEvent actionEvent) {
        var stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(returnScene);
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

    private void showFieldError(DataIntegrityViolationException e) {
        // this is ONE way to handle this - there are other approaches that
        // use translation that might be cleaner
        var message = e.getMessage();
        String instructions = VALUES_MISSING_MESSAGE;
        String headerText = VALUES_MISSING_HEADER;
        if(message.contains("EMPLOYEE_EMAIL_UINDEX")){
            instructions = DUP_EMAIL_MESSAGE;
            headerText = DUP_EMAIL_HEADER;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, instructions);
        alert.setHeaderText(headerText);
        alert.show();
    }

}
