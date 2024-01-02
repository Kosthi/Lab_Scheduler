package com.kosthi.labscheduler.controller;

import com.kosthi.labscheduler.mode.Calendar;
import com.kosthi.labscheduler.mode.HttpRequest;
import com.kosthi.labscheduler.mode.Teacher;
import com.kosthi.labscheduler.view.ViewFactory;
import com.kosthi.labscheduler.view.ViewType;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MainController implements Initializable {
    private static Map<String, Label> labelMap;
    @FXML
    MFXTextField searchKey;
    @FXML
    @Getter
    MFXComboBox<String> searchWeek;
    @FXML
    @Getter
    MFXComboBox<String> searchSchool;
    @FXML
    @Getter
    MFXComboBox<String> searchLab;
    List<String> dateList = new ArrayList<>(140);
    private boolean isLogin = false;
    @FXML
    private MFXButton logoutButton;
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
    private Label l1_1;
    @FXML
    private Label l2_1;
    @FXML
    private Label l3_1;
    @FXML
    private Label l4_1;
    @FXML
    private Label l5_1;
    @FXML
    private Label l6_1;
    @FXML
    private Label l7_1;
    // 第二行标签
    @FXML
    private Label l1_2;
    @FXML
    private Label l2_2;
    @FXML
    private Label l3_2;
    @FXML
    private Label l4_2;
    @FXML
    private Label l5_2;
    @FXML
    private Label l6_2;
    @FXML
    private Label l7_2;
    // 第三行标签
    @FXML
    private Label l1_3;
    @FXML
    private Label l2_3;
    @FXML
    private Label l3_3;
    @FXML
    private Label l4_3;
    @FXML
    private Label l5_3;
    @FXML
    private Label l6_3;
    @FXML
    private Label l7_3;
    // 第四行标签
    @FXML
    private Label l1_4;
    @FXML
    private Label l2_4;
    @FXML
    private Label l3_4;
    @FXML
    private Label l4_4;
    @FXML
    private Label l5_4;
    @FXML
    private Label l6_4;
    @FXML
    private Label l7_4;
    // 第五行标签
    @FXML
    private Label l1_5;
    @FXML
    private Label l2_5;
    @FXML
    private Label l3_5;
    @FXML
    private Label l4_5;
    @FXML
    private Label l5_5;
    @FXML
    private Label l6_5;
    @FXML
    private Label l7_5;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button searchButton;

    @FXML
    private Button loginButton;

    private Map<String, List<String>> schoolLabsMap;

    @Setter
    private Teacher teacher;

    @Getter
    private Calendar calendar;

    private void setLabelClickAble() {
        for (int i = 1; i <= 7; ++i) {
            for (int j = 1; j <= 5; ++j) {
                String key = String.valueOf(i) + '-' + j;
                if (labelMap.get(key).getText().isEmpty() || labelMap.get(key).getText().contains(teacher.getTeacherName())) {
                    labelMap.get(key).setOnMouseClicked(e -> {
                        ScheduleController scheduleController = (ScheduleController) ViewFactory.getController(ViewType.SCHEDULE);
                        // System.out.println(key.charAt(0) + " " + key.charAt(2));
                        scheduleController.setWeekDay(key.charAt(0));
                        scheduleController.setSession(key.charAt(2));
                        scheduleController.setVisible(!labelMap.get(key).getText().contains(teacher.getTeacherName()) || labelMap.get(key).getText().contains("可排课"));
                        ViewFactory.show(ViewType.SCHEDULE);
                    });
                }
            }
        }
    }

    private void setTips() {
        for (int i = 1; i <= 7; ++i) {
            for (int j = 1; j <= 5; ++j) {
                String key = String.valueOf(i) + '-' + j;
                if (labelMap.get(key).getText().isEmpty()) {
                    labelMap.get(key).setText("可排课");
                    labelMap.get(key).setStyle("-fx-text-fill: red");
                }
            }
        }
    }

    public void switchToLogin() {
        isLogin = true;
        loginButton.setVisible(false);
        logoutButton.setVisible(true);
        refresh();
        welcomeLabel.setText(teacher.getSchoolName() + " " + teacher.getTeacherName() + " " + "老师 你好！");
    }

    public void refresh() {
        clearLabelsText();
        search();
        if (isLogin) {
            setLabelClickAble();
            setTips();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setVisible(false);
        logoutButton.setOnMouseClicked(e -> {
            HttpRequest.logout(teacher.getAccount());
            logoutButton.setVisible(false);
            welcomeLabel.setText("");
            loginButton.setVisible(true);
            isLogin = false;
            refresh();
        });

        buildLabMap();

        loginButton.setOnMouseClicked(e -> ViewFactory.show(ViewType.LOGIN));

        // 更新周时间信息
        calendar = HttpRequest.getCalendar();
        getNext20Weeks(LocalDate.parse(calendar.getStartDate().toString()));

        schoolLabsMap = HttpRequest.getSchoolLabs();
        // System.out.println(schoolLabsMap);

        initSearchInput();

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

        // 检索排课信息
        search();
    }

    void initSearchInput() { //初始化搜索栏和多选框
        searchButton.setOnMouseClicked(e -> {
            clearLabelsText();
            search();
            if (isLogin) {
                setLabelClickAble();
                setTips();
            }
        });

        List<String> list = new ArrayList<>(20);
        for (int i = 1; i <= 20; ++i) {
            list.add(String.valueOf(i));
        }
        searchWeek.setItems(FXCollections.observableList(list));

        int week = (int) calculateWeeksFromMonday(LocalDate.of(2023, 8, 21), LocalDate.now());
        searchWeek.selectIndex(week);
        searchWeek.setScrollOnOpen(true);

        searchSchool.getItems().addAll(schoolLabsMap.keySet());
        searchSchool.selectFirst();

        searchLab.getItems().addAll(schoolLabsMap.get(searchSchool.getSelectedItem()));
        searchLab.selectFirst();

        searchSchool.setOnAction(e -> {
            searchLab.getItems().clear();
            searchLab.getItems().addAll(schoolLabsMap.get(searchSchool.getSelectedItem()));
            searchLab.selectFirst();
        });

        searchSchool.setScrollOnOpen(true);
        searchLab.setScrollOnOpen(true);

        setWrapText();
    }

    private void getNext20Weeks(LocalDate startDate) {
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

    private void buildLabMap() {
        labelMap = new HashMap<>();

        // 第一天的标签
        labelMap.put("1-1", l1_1);
        labelMap.put("1-2", l1_2);
        labelMap.put("1-3", l1_3);
        labelMap.put("1-4", l1_4);
        labelMap.put("1-5", l1_5);
        // 第二天的标签
        labelMap.put("2-1", l2_1);
        labelMap.put("2-2", l2_2);
        labelMap.put("2-3", l2_3);
        labelMap.put("2-4", l2_4);
        labelMap.put("2-5", l2_5);
        // 第三天的标签
        labelMap.put("3-1", l3_1);
        labelMap.put("3-2", l3_2);
        labelMap.put("3-3", l3_3);
        labelMap.put("3-4", l3_4);
        labelMap.put("3-5", l3_5);
        // 第四天的标签
        labelMap.put("4-1", l4_1);
        labelMap.put("4-2", l4_2);
        labelMap.put("4-3", l4_3);
        labelMap.put("4-4", l4_4);
        labelMap.put("4-5", l4_5);
        // 第五天的标签
        labelMap.put("5-1", l5_1);
        labelMap.put("5-2", l5_2);
        labelMap.put("5-3", l5_3);
        labelMap.put("5-4", l5_4);
        labelMap.put("5-5", l5_5);
        // 第六天的标签
        labelMap.put("6-1", l6_1);
        labelMap.put("6-2", l6_2);
        labelMap.put("6-3", l6_3);
        labelMap.put("6-4", l6_4);
        labelMap.put("6-5", l6_5);
        // 第七天的标签
        labelMap.put("7-1", l7_1);
        labelMap.put("7-2", l7_2);
        labelMap.put("7-3", l7_3);
        labelMap.put("7-4", l7_4);
        labelMap.put("7-5", l7_5);
    }

    private void setWrapText() {
        for (int i = 1; i <= 7; ++i) {
            for (int j = 1; j <= 5; ++j) {
                String key = String.valueOf(i) + '-' + j;
                labelMap.get(key).setWrapText(true);
                // labelMap.get(key).setAlignment(Pos.CENTER);
            }
        }
    }

    private void clearLabelsText() {
        for (int i = 1; i <= 7; ++i) {
            for (int j = 1; j <= 5; ++j) {
                String key = String.valueOf(i) + '-' + j;
                labelMap.get(key).setText("");
                labelMap.get(key).setStyle("");
                labelMap.get(key).setOnMouseClicked(e -> {
                });
            }
        }
    }

    private void search() {
        Map<Character, List<String>> schedule = HttpRequest.getSchedule(searchWeek.getSelectedItem(), searchSchool.getSelectedItem(), searchLab.getSelectedItem());
        schedule.forEach((weekday, valueList) -> valueList.forEach(value -> {
            String key = weekday + "-" + value.charAt(0);
            Label label = labelMap.get(key);
            if (label != null) {
                label.setText(value.substring(1));
            } else {
                throw new RuntimeException("Label not found for key: " + key);
            }
        }));
    }
}
