package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ComponentController implements Initializable {
    @FXML private ListView<Component> componentListView;
    @FXML private TextField quantityTextField;
    @FXML private Button btnAddToProduct;
    @FXML private Button btnDone;

    @Autowired private ComponentRepository componentRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductComponentRepository pcRepository;

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Component>observableList = FXCollections.observableArrayList();
        componentRepository.findAll().forEach(observableList::add);
        componentListView.setItems(observableList);



    }

    @FXML private void setBtnAddToProduct(ActionEvent actionEvent) {
        int quantity = Integer.parseInt(quantityTextField.getText());
        Component component = componentListView.getSelectionModel().getSelectedItem();

        Product product = new Product("name", "description");
        productRepository.save(product);

        ProductComponent productComponent = new ProductComponent(quantity, component, product);
        pcRepository.save(productComponent);

        System.out.println(component.getName());
        System.out.println(quantity);
    }

    @FXML private void handleDone(ActionEvent actionEvent) {
        var stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(returnScene);
    }
}
