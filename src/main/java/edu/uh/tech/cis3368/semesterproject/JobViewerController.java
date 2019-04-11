package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class JobViewerController implements Initializable {
    @FXML
    private Button btnCreateProduct;
    @FXML
    private Button btnViewProducts;

    @FXML
    private TableView<Job> jobTableView;
    @FXML
    private TableColumn<Job, String> colJobName;
    @FXML
    private TableColumn<Job, String> colJobDescription;
    private ObservableList<Job> jobList = FXCollections.observableArrayList();

    @Autowired
    private JobRepository jobRepository;

    @FXML
    private void setBtnCreateProduct(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnProperties();
        btnCreateProduct.setDisable(true);
        btnViewProducts.setDisable(true);

        jobRepository.findAll().forEach(jobList::add);
        jobTableView.setItems(jobList);

        jobTableView.getSelectionModel().selectedItemProperty().addListener(((obs, oldSelection, newSelection) -> {
            Job job = newSelection;
            if(job != null) {
                btnCreateProduct.setDisable(false);
                btnViewProducts.setDisable(false);
            }
        }));

    }

    private void setColumnProperties() {
        colJobName.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        colJobDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
}
