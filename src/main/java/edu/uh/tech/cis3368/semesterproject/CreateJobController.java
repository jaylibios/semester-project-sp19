package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class CreateJobController implements Initializable {

    @FXML private TextField jobName;
    @FXML private TextArea jobDescription;
    @FXML private TextField customerName;
    @FXML private TextField customerAddress;
    @FXML private TextField customerPhone;

    @FXML private Button btnCancel;

    @Autowired private JobService jobService;

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML private void setBtnSubmit(ActionEvent actionEvent) {
        jobService.createJobWithCustomer(
                jobName.getText(),
                jobDescription.getText(),
                customerName.getText(),
                customerAddress.getText(),
                customerPhone.getText());

        Alert alert = new Alert(Alert.AlertType.NONE, "Job added.", ButtonType.OK);
        alert.show();

        clearFields();
    }

    @FXML private void setBtnCancel(ActionEvent actionEvent) {
        var stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(returnScene);
    }

    private void clearFields() {
        jobName.clear();
        jobDescription.clear();
        customerName.clear();
        customerAddress.clear();
        customerPhone.clear();
    }
}
