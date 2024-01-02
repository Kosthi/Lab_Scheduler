package com.kosthi.labscheduler.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ScheduleView extends BasicView {
    public ScheduleView() {
        FXMLLoader fxmlLoader = new FXMLLoader(ScheduleView.class.getResource("schedule.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();
            setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
