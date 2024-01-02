package com.kosthi.labscheduler.view;

import java.util.HashMap;
import java.util.Map;

public class ViewFactory {
    private static final Map<ViewType, BasicView> viewMap = new HashMap<>();

    public static BasicView createUI(ViewType type) {
        switch (type) {
            case MAIN:
                return viewMap.computeIfAbsent(ViewType.MAIN, k -> new MainView());
            case LOGIN:
                return viewMap.computeIfAbsent(ViewType.LOGIN, k -> new LoginView());
            case SCHEDULE:
                return viewMap.computeIfAbsent(ViewType.SCHEDULE, k -> new ScheduleView());
            default:
                throw new IllegalArgumentException("Invalid UI Type");
        }
    }

    public static void show(ViewType type) {
        createUI(type).show();
    }

    public static void close(ViewType type) {
        createUI(type).close();
    }

    public static Object getController(ViewType type) {
        return createUI(type).getController();
    }
}
