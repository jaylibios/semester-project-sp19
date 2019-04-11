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

    @Autowired
    private ComponentRepository componentRepository;

    private ObservableList<Component> componentList = FXCollections.observableArrayList();

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Component page");
        componentRepository.findAll().forEach(componentList::add);
        componentListView.setItems(componentList);

    }
}
