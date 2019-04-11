package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class JobController implements Initializable {

    @Autowired
    private JobService jobService;

    @FXML
    private TextField jobName;
    @FXML
    private TextArea jobDescription;
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerAddress;
    @FXML
    private TextField customerPhone;

    @FXML
    private Button btnCancel;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobStageRepository jobStageRepository;
    @Autowired
    ProductRepository productRepository;

    private Scene returnScene;

    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void setBtnSubmit(ActionEvent actionEvent) {
        jobService.createJobWithCustomer(jobName.getText(), jobDescription.getText(),
                customerName.getText(), customerAddress.getText(), customerPhone.getText());
        clearFields();
    }

    @FXML
    private void setBtnCancel(ActionEvent actionEvent) {
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        jobName.clear();
        jobDescription.clear();
        customerName.clear();
        customerAddress.clear();
        customerPhone.clear();
    }
}
