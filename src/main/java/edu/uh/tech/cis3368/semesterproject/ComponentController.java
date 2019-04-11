package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ComponentController implements Initializable {
    @FXML
    private ListView<Component> componentListView;
    @FXML
    private ListView<Product> productListView;

    @Autowired
    private ComponentRepository componentRepository;
    @Autowired
    private ProductRepository productRepository;

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Component> componentObservableList = FXCollections.observableArrayList();
        componentRepository.findAll().forEach(componentObservableList::add);
        componentListView.setItems(componentObservableList);

        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        productRepository.findAll().forEach(productObservableList::add);
        productListView.setItems(productObservableList);


    }
}
