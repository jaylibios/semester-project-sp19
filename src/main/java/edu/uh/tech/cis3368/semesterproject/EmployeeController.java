package edu.uh.tech.cis3368.semesterproject;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.TableView;
import java.awt.*;

@Component
public class EmployeeController {
    //hold return scene
    private Scene scene;

    private TableView employeeTableView;

    @FXML
    public TextField lastName;
    @FXML
    public TextField firstName;
    @FXML
    public TextField phone;
    @FXML
    public TextField email;

    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnAddEmployee;
    @FXML
    private Button btnDeleteEmployee;
    @FXML
    private Button btnEditEmployee;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setReturnScene() {
        this.scene = scene;
    }

    public void goBack() {
        //get stage since same stage, set scene to stored scene
        //if(scene == null)
            //btnGoBack.getScene().getWindow().hide();
        //else
            //((Stage)btnGoBack.getScene().getWindow().setScene(scene));
    }

    private void setBtnAddEmployee() {
        //Employee employee = new Employee();
        //employee.setLastName(lastName.getText());
        //employee.setFirstName(firstName.getText());
        //employee.setPhone(phone.getText());
        //employee.setEmail(email.getText());
    }

}
