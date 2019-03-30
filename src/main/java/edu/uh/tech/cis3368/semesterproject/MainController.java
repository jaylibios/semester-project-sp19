package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;


@Component
public class MainController {

    @FXML
    private Button btnManageEmployees;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public void doManageEmployees(ActionEvent actionEvent) throws IOException {



    }

}
