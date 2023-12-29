package com.kosthi.labscheduler.view;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ViewFactory {
    private static final Map<ViewType, Scene> viewMap = new HashMap<>();

    private static Stage stage;

    public static Scene createUI(ViewType type) {
        switch (type) {
            case MAIN:
                return viewMap.computeIfAbsent(ViewType.MAIN, k -> new MainView().getScene());
            default:
                throw new IllegalArgumentException("Invalid UI Type");
        }
    }

    public static void show(ViewType type) {
        stage.setScene(createUI(type));
        stage.show();
    }

    public static void close(ViewType type) {
        stage.close();
    }

    public static void setStage(Stage stage) {
        ViewFactory.stage = stage;
    }
}
