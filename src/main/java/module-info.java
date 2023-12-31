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


    opens com.kosthi.labscheduler.controller to javafx.fxml;
    exports com.kosthi.labscheduler;
}
