package com.kosthi.labscheduler.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainView extends BasicView {
    public MainView() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1440, 900);
            controller = fxmlLoader.getController();
            setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
