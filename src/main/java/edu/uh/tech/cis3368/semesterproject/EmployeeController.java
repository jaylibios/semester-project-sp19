package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class EmployeeController implements Initializable {
    public TableView<Employee> employeeTableView;
    public TableColumn<Employee, String> colLastName;
    public TableColumn<Employee, String> colFirstName;
    public TableColumn<Employee, String> colPhone;
    public TableColumn<Employee, String> colEmail;

    public TextField lastName;
    public TextField firstName;
    public TextField phone;
    public TextField email;

    public Button btnAddEmployee;
    public Button btnDeleteEmployee;
    public Button btnEditEmployee;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method that is called as soon as view loads
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Button methods
    public void setBtnAddEmployee(ActionEvent actionEvent) {
        addEmployee();
    }

    // Method to add new employee
    private void addEmployee() {
        Employee employee = new Employee();

        // User setters from Employee class to set from TextFields
        employee.setLastName(lastName.getText());
        employee.setFirstName(firstName.getText());
        employee.setPhone(phone.getText());
        employee.setEmail(email.getText());

        // Save and flush the information to repository
        //employeeRepository.save(employee);

        System.out.println("New Employee added.");
    }
}
