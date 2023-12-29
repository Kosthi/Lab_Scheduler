package com.kosthi.labscheduler.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainView {

    private Scene scene;

    public MainView() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main.fxml"));
        try {
            scene = new Scene(fxmlLoader.load(), 1440, 900);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//
//        stage.show();
    }

    public Object $(String id) {
        return scene.lookup("#" + id);
    }

    public Scene getScene() {
        return scene;
    }
}
