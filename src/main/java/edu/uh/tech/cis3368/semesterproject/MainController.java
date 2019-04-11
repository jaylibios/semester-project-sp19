package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public void setBtnManageEmployees(ActionEvent actionEvent) throws IOException {
        /** Open in new window
         *  Load FXML file
         *  Set controller factory **/
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employees.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Employee Management System");
        stage.setScene(new Scene(root));
        stage.show();

        /** Changing scenes
         *  Get stage from action event
         *  Cast as a node -> get scene, get window
         ** Don't have to load scene again, already in memory from main
         *  Call FXML loader, get controller after setting scene
         *  Set scene, store scene and then set scene in parent
         ** DON'T KNOW WHERE '.getScene()' comes from **/
        //Stage parent = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employees.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        //EmployeeController employeeController = fxmlLoader.getController();
        //employeeController.setReturnScene(btnManageEmployees.getScene());
        //parent.setScene(scene);
    }

    public void setBtnCreateNewJob(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("job.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        JobController jobController = fxmlLoader.getController();
        jobController.setReturnScene(btnCreateNewJob.getScene());
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
