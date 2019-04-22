package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ComponentController implements Initializable {
    @FXML private ListView<Component> componentListView;
    @FXML private ListView<ProductComponent> pcListView;
    @FXML private TextField quantityTextField;
    @FXML private TextField productNameTextField;
    @FXML private TextArea productDescriptionTextArea;
    @FXML private Label productTotalAmt;

    @Autowired private ComponentRepository componentRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductComponentRepository pcRepository;

    @Autowired private JobService jobService;

    private Scene returnScene;
    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProductTextFields();

        BigInteger totalPrice = totalPrice();
        productTotalAmt.setText("$" + totalPrice);

        ObservableList<Component> componentObservableList = FXCollections.observableArrayList();
        componentRepository.findAll().forEach(componentObservableList::add);
        componentListView.setItems(componentObservableList);

        Product product = jobService.getJobProduct();

        ObservableList<ProductComponent> pcObservableList = FXCollections.observableArrayList();
        pcRepository.findByproductByProductId(product).forEach(pcObservableList::add);
        pcListView.setItems(pcObservableList);
    }

    @FXML private void handleAddToProduct(ActionEvent actionEvent) {
        Component component = componentListView.getSelectionModel().getSelectedItem();
        Product product = jobService.getJobProduct();
        int quantity = Integer.parseInt(quantityTextField.getText());


        addComponentToProduct(quantity, component, product);
        BigInteger totalPrice = totalPrice();
        productTotalAmt.setText("$" + totalPrice);

    }

    @FXML private void handleDone(ActionEvent actionEvent) {
        var stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(returnScene);
    }

    @FXML private void handleUpdateProduct(ActionEvent actionEvent) {
        Product product = jobService.getJobProduct();
        product.setName(productNameTextField.getText());
        product.setDescription(productDescriptionTextArea.getText());
        productRepository.save(product);

        Alert alert = new Alert(Alert.AlertType.NONE, "Product information updated.", ButtonType.OK);
        alert.show();
    }

    @FXML private void handleRemoveComponent(ActionEvent actionEvent) {
        ProductComponent productComponent = pcListView.getSelectionModel().getSelectedItem();
        pcRepository.delete(productComponent);
        pcListView.getItems().remove(productComponent);

        BigInteger totalPrice = totalPrice();
        productTotalAmt.setText("$" + totalPrice);

        Alert alert = new Alert(Alert.AlertType.NONE, "Component removed.", ButtonType.OK);
        alert.show();
    }

    private void setProductTextFields() {
        Product product = jobService.getJobProduct();
        productNameTextField.setText(product.getName());
        productDescriptionTextArea.setText(product.getDescription());
    }

    private void addComponentToProduct(int quantity, Component component, Product product) {
        ProductComponent productComponent = new ProductComponent(quantity, component, product);
        pcRepository.save(productComponent);
        pcListView.getItems().add(productComponent);

        Alert alert = new Alert(Alert.AlertType.NONE, "Component added.", ButtonType.OK);
        alert.show();
    }

    private BigInteger totalPrice() {
        Product product = jobService.getJobProduct();

        var prices = pcRepository.findByproductByProductId(product);
        BigInteger sum = prices.stream().map(p -> p.getComponentByComponentId().getWholesalePrice())
                .reduce(BigInteger.ZERO, (b1, b2) -> b1.add(b2));

        return sum;
    }
}
