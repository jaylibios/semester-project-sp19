package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class MainController {

    @FXML
    private Button btnManageEmployees;
    @FXML
    private Button btnCreateNewJob;
    @FXML
    private Button btnViewJobs;
    @FXML
    Button btnViewReport;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public void setBtnManageEmployees(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employees.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        EmployeeController employeeController = fxmlLoader.getController();
        employeeController.setReturnScene(btnManageEmployees.getScene());
        parent.setTitle("Employee Management System");
        parent.setScene(scene);
    }

    public void setBtnCreateNewJob(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createJob.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        CreateJobController createJobController = fxmlLoader.getController();
        createJobController.setReturnScene(btnCreateNewJob.getScene());
        parent.setTitle("Create New Job");
        parent.setScene(scene);
    }

    @FXML
    private void setBtnViewJobs(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jobViewer.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        JobViewerController jobViewerController = fxmlLoader.getController();
        jobViewerController.setReturnScene(btnViewJobs.getScene());
        parent.setScene(scene);
    }
}
