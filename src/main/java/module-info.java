module com.kosthi.labscheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires org.apache.commons.codec;
    requires com.google.gson;
    requires okhttp3;
    requires org.slf4j;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires lombok;


    opens com.kosthi.labscheduler.controller to javafx.fxml;
    opens com.kosthi.labscheduler.mode to com.google.gson;
    exports com.kosthi.labscheduler;
    exports com.kosthi.labscheduler.controller;
}
