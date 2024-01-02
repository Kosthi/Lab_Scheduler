package com.kosthi.labscheduler;

import com.kosthi.labscheduler.view.ViewFactory;
import com.kosthi.labscheduler.view.ViewType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//
//        stage.show();

        ViewFactory.show(ViewType.MAIN);

//        GridPane gridPane = new GridPane();
//
//        // 设置列宽
//        ColumnConstraints column1 = new ColumnConstraints(110); // 列宽 100 像素
//        ColumnConstraints column2 = new ColumnConstraints(110); // 列宽 200 像素
//        // gridPane.getColumnConstraints().addAll(column1, column2); // 添加到 GridPane
//
//        // 设置行高
//        RowConstraints row1 = new RowConstraints(53); // 行高 50 像素
//        RowConstraints row2 = new RowConstraints(53); // 行高 100 像素
//        // gridPane.getRowConstraints().addAll(row1, row2); // 添加到 GridPane
//
//        gridPane.setStyle("-fx-pref-width: 770px; -fx-pref-height: 800px; -fx-alignment: center;");
//
//        Label label1 = new Label("星期一 [12月25日]");
//        label1.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
//
//        Label label2 = new Label("星期二 [12月25日]");
//        label2.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        Label label3 = new Label("星期三 [12月25日]");
//        label3.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        Label label4 = new Label("星期四 [12月25日]");
//        label4.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        Label label5 = new Label("星期五 [12月25日]");
//        label5.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        Label label6 = new Label("星期六 [12月25日]");
//        label6.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        Label label7 = new Label("星期日 [12月25日]");
//        label7.setStyle("-fx-pref-width: 110px; -fx-pref-height: 53px; -fx-border-color: black; -fx-border-width: 1 1 1 0; -fx-border-style: solid;");
//
//        gridPane.add(label1, 0, 0);
//        gridPane.add(label2, 1, 0);
//        gridPane.add(label3, 2, 0);
//        gridPane.add(label4, 3, 0);
//        gridPane.add(label5, 4, 0);
//        gridPane.add(label6, 5, 0);
//        gridPane.add(label7, 6, 0);
//
//        Label label8 = new Label("1-2节");
//        label8.setStyle("-fx-alignment: center; -fx-pref-width: 110px; -fx-pref-height: 100px; -fx-border-color: black; -fx-border-width: 0 1 1 1; -fx-border-style: solid;");
//        gridPane.add(label8, 0, 1);
//
////        gridPane.add(new Label("星期一 [12月25日]"), 0, 2);
////        gridPane.add(new Label("星期一 [12月25日]"), 0, 3);
////        gridPane.add(new Label("星期一 [12月25日]"), 0, 4);
////        gridPane.add(new Label("星期一 [12月25日]"), 0, 5);
//
//        Scene scene = new Scene(gridPane, 1440, 720);
//        stage.setTitle("GridPane Example");
//        stage.setScene(scene);
//        stage.show();
    }
}
