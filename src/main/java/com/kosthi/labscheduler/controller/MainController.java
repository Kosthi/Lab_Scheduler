package com.kosthi.labscheduler.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    MFXTextField searchKey;

    @FXML
    MFXComboBox<String> searchWeek;

    @FXML
    MFXComboBox<String> searchSchool;

    @FXML
    MFXComboBox<String> searchLab;
    List<String> dateList = new ArrayList<>(140);
    @FXML
    private Label mon;
    @FXML
    private Label tue;
    @FXML
    private Label wed;
    @FXML
    private Label thu;
    @FXML
    private Label fri;
    @FXML
    private Label sat;
    @FXML
    private Label sun;
    // 第一行标签
    @FXML
    private Label l1_12;
    @FXML
    private Label l2_12;
    @FXML
    private Label l3_12;
    @FXML
    private Label l4_12;
    @FXML
    private Label l5_12;
    @FXML
    private Label l6_12;
    @FXML
    private Label l7_12;
    // 第二行标签
    @FXML
    private Label l1_34;
    @FXML
    private Label l2_34;
    @FXML
    private Label l3_34;
    @FXML
    private Label l4_34;
    @FXML
    private Label l5_34;
    @FXML
    private Label l6_34;
    @FXML
    private Label l7_34;
    // 第三行标签
    @FXML
    private Label l1_56;
    @FXML
    private Label l2_56;
    @FXML
    private Label l3_56;
    @FXML
    private Label l4_56;
    @FXML
    private Label l5_56;
    @FXML
    private Label l6_56;
    @FXML
    private Label l7_56;
    // 第四行标签
    @FXML
    private Label l1_78;
    @FXML
    private Label l2_78;
    @FXML
    private Label l3_78;
    @FXML
    private Label l4_78;
    @FXML
    private Label l5_78;
    @FXML
    private Label l6_78;
    @FXML
    private Label l7_78;
    // 第五行标签
    @FXML
    private Label l1_910;
    @FXML
    private Label l2_910;
    @FXML
    private Label l3_910;
    @FXML
    private Label l4_910;
    @FXML
    private Label l5_910;
    @FXML
    private Label l6_910;
    @FXML
    private Label l7_910;

    void initSearchInput() { //初始化搜索栏和多选框
        searchKey.setOnKeyPressed(e -> { //回车搜索
            if (e.getCode() == KeyCode.ENTER) {
                // onSearch();
            }
        });
        List<String> list = new ArrayList<>(20);
        for (int i = 1; i <= 20; ++i) {
            list.add(String.valueOf(i));
        }
        searchWeek.setItems(FXCollections.observableList(list));
        searchWeek.selectFirst();

        List<String> list1 = new ArrayList<>(20);
        list1.add("信息科学与工程学院");
        searchSchool.setItems(FXCollections.observableList(list1));

        int week = (int) calculateWeeksFromMonday(LocalDate.of(2023, 8, 21), LocalDate.now());
        searchWeek.selectIndex(week);
        searchWeek.setScrollOnOpen(true);

        l3_12.setText("教师:于霞\n" +
                "课程:数据库与软件工程课程设计\n" +
                "计算机科学与技术[1103]\n" +
                "备注:2101-2102");

        setWrapText();

//        searchType.setItems(FXCollections.observableList(Arrays.stream(Music.Type.values()).toList()));
//        searchPlatform.selectFirst();
//        searchType.selectFirst();
//        searchTableModel.searchKeyProperty().bindBidirectional(searchKey.textProperty());
//        searchTableModel.searchPlatformProperty().bindBidirectional(searchPlatform.valueProperty());
//        searchTableModel.searchTypeProperty().bindBidirectional(searchType.valueProperty());
    }

    void initLoadingBar() { //初始化加载条
        // loadingBar.visibleProperty().bind(searchTableModel.loadingProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initSearchInput();
        initLoadingBar();
        getNext20Weeks(LocalDate.of(2023, 8, 21));

        searchWeek.valueProperty().addListener((observable, oldValue, newValue) -> {
            // 这里处理选项改变的事件
            List<String> week = getCurrentWeek(Integer.parseInt(newValue));

            mon.setText(week.get(0));
            tue.setText(week.get(1));
            wed.setText(week.get(2));
            thu.setText(week.get(3));
            fri.setText(week.get(4));
            sat.setText(week.get(5));
            sun.setText(week.get(5));
        });
    }

    private List<String> getNext20Weeks(LocalDate startDate) {
//        dateList = new ArrayList<>(140);

        // 创建格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M月d日");

        // 循环生成后续140天（20周）的日期
        for (int i = 0; i < 140; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String dayOfWeek = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
            String formattedDate = currentDate.format(formatter);
            dateList.add(dayOfWeek + " [" + formattedDate + "]");
            // System.out.println(dayOfWeek + " [" + formattedDate + "]");
        }
        return dateList;
    }

    private List<String> getCurrentWeek(int week) {
        return dateList.subList((week - 1) * 7, (week - 1) * 7 + 7);
    }

    /**
     * 计算从指定的星期一开始到给定日期之间的完整周数。
     *
     * @param startMonday 起始日期（星期一）
     * @param currentDate 给定日期
     * @return 从起始星期一到给定日期的周数
     */
    private long calculateWeeksFromMonday(LocalDate startMonday, LocalDate currentDate) {
        if (startMonday.getDayOfWeek() != java.time.DayOfWeek.MONDAY) {
            throw new IllegalArgumentException("起始日期必须是星期一");
        }
        return ChronoUnit.WEEKS.between(startMonday, currentDate);
    }

    private void setWrapText() {
        // 第一行标签
        l1_12.setWrapText(true);
        l2_12.setWrapText(true);
        l3_12.setWrapText(true);
        l4_12.setWrapText(true);
        l5_12.setWrapText(true);
        l6_12.setWrapText(true);
        l7_12.setWrapText(true);
        // 第二行标签
        l1_34.setWrapText(true);
        l2_34.setWrapText(true);
        l3_34.setWrapText(true);
        l4_34.setWrapText(true);
        l5_34.setWrapText(true);
        l6_34.setWrapText(true);
        l7_34.setWrapText(true);
        // 第三行标签
        l1_56.setWrapText(true);
        l2_56.setWrapText(true);
        l3_56.setWrapText(true);
        l4_56.setWrapText(true);
        l5_56.setWrapText(true);
        l6_56.setWrapText(true);
        l7_56.setWrapText(true);
        // 第四行标签
        l1_78.setWrapText(true);
        l2_78.setWrapText(true);
        l3_78.setWrapText(true);
        l4_78.setWrapText(true);
        l5_78.setWrapText(true);
        l6_78.setWrapText(true);
        l7_78.setWrapText(true);
        // 第五行标签
        l1_910.setWrapText(true);
        l2_910.setWrapText(true);
        l3_910.setWrapText(true);
        l4_910.setWrapText(true);
        l5_910.setWrapText(true);
        l6_910.setWrapText(true);
        l7_910.setWrapText(true);
    }
}
