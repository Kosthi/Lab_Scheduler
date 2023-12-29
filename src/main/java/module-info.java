module com.kosthi.labscheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.kosthi.labscheduler.controller to javafx.fxml;
    exports com.kosthi.labscheduler;
}
