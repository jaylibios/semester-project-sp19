package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class EmployeeController implements Initializable {
    // TableView variables
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> colLastName;
    public TableColumn<Employee, String> colFirstName;
    public TableColumn<Employee, String> colPhone;
    public TableColumn<Employee, String> colEmail;

    // TextFields
    public TextField lastName;
    public TextField firstName;
    public TextField phone;
    public TextField email;

    // Buttons
    public Button btnAddEmployee;
    public Button btnDeleteEmployee;
    public Button btnEditEmployee;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method that is called as soon as view loads
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnProperties();

        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        employeeRepository.findAll().forEach(employeeList::add);
        employeeTableView.setItems(employeeList);
    }

    // Method to create column properties
    private void setColumnProperties() {
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    // Button methods
    public void setBtnAddEmployee(ActionEvent actionEvent) {
        addEmployee();
    }

    // Method to add new employee
    private void addEmployee() {
        // Create new employee object
        Employee employee = new Employee();

        // Use setters to set fields in Employee class
        employee.setLastName(lastName.getText());
        employee.setFirstName(firstName.getText());
        employee.setPhone(phone.getText());
        employee.setEmail(email.getText());

        // Save information to repository
        employeeRepository.save(employee);

        System.out.println("New Employee added.");
    }
}
