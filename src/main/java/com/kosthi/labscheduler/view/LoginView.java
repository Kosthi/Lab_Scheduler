package com.kosthi.labscheduler.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginView extends BasicView {
    public LoginView() {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("login.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();
            setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
