package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class JobController implements Initializable {
    @FXML
    private TextField job_name;
    @FXML
    private TextArea job_description;

    @FXML
    private TextField name;
    @FXML
    private TextField address_ln1;
    @FXML
    private TextField address_ln2;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField phone;

    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnCancel;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobStageRepository jobStageRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void setBtnSubmit(ActionEvent actionEvent) {
        saveJobWithCustomer();
    }

    private void saveJobWithCustomer() {
        System.out.println("Submit");
        Customer customer = new Customer();

        customer.setName(name.getText());
        customer.setStreetAddressLn1(address_ln1.getText());
        customer.setStreetAddressLn2(address_ln2.getText());
        customer.setCity(city.getText());
        customer.setState(state.getText());
        customer.setZipCode(zip.getText());
        customer.setPhone(phone.getText());

        Job job = new Job();
        job.setJobName(job_name.getText());
        job.setDescription(job_description.getText());

        customerRepository.save(customer);

        clearFields();
    }

    private void clearFields() {
        name.clear();
        address_ln1.clear();
        address_ln2.clear();
        city.clear();
        state.clear();
        zip.clear();
        phone.clear();
    }
}
