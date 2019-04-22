package edu.uh.tech.cis3368.semesterproject;


import javafx.fxml.Initializable;
import javafx.scene.Scene;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class ReportController implements Initializable {

    private Scene returnScene;

    public void setReturnScene(Scene scene) {
        this.returnScene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
