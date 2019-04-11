package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
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
    @FXML
    private TableColumn<Job, Integer> colJobStage;

    @FXML
    private Button getBtnCreateProduct;

    private ObservableList<Job> jobList = FXCollections.observableArrayList();

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    public JobStageRepository jobStageRepository;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private Scene returnScene;

    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
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

    @FXML
    private void setBtnCreateProduct(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("component.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        ComponentController componentController = fxmlLoader.getController();
        componentController.setReturnScene(btnCreateProduct.getScene());
        parent.setScene(scene);
    }

    private void setColumnProperties() {
        colJobName.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        colJobDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colJobStage.setCellValueFactory(new PropertyValueFactory<>("jobStage"));
    }
}
