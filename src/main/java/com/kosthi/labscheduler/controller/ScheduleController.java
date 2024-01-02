package com.kosthi.labscheduler.controller;

import com.kosthi.labscheduler.mode.*;
import com.kosthi.labscheduler.view.ViewFactory;
import com.kosthi.labscheduler.view.ViewType;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import lombok.Setter;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleController {

    @FXML
    private MFXComboBox<String> searchSchool;

    @FXML
    private MFXComboBox<String> searchMajor;

    @FXML
    private MFXComboBox<String> searchCourse;

    @FXML
    private MFXComboBox<String> searchClass1;

    @FXML
    private MFXComboBox<String> searchClass2;

    @FXML
    private MFXTextField note;

    @FXML
    private MFXButton scheduleButton;

    @FXML
    private MFXButton cancelScheduleButton;

    @Setter
    private Teacher teacher;

    @Setter
    private char weekDay;

    @Setter
    private char session;

    public void initialized() {

        searchSchool.getItems().addAll(teacher.getSchoolTeach().stream()
                .map(School::getSchoolName)
                .collect(Collectors.toList()));
        searchSchool.selectFirst();

        searchSchool.setOnAction(e -> {
            searchMajor.getItems().clear();
            searchMajor.getItems().addAll(teacher.getSchoolTeach().get(searchSchool.getSelectedIndex()).getMajors().stream()
                    .map(Major::getMajorName)
                    .collect(Collectors.toList()));
            searchMajor.selectFirst();
        });

        searchMajor.setOnAction(e -> {
            searchCourse.getItems().clear();
            searchCourse.getItems().addAll(teacher.getSchoolTeach()
                    .get(searchSchool.getSelectedIndex())
                    .getMajors()
                    .get(searchMajor.getSelectedIndex())
                    .getCourses().stream()
                    .map(Course::getCourseName)
                    .collect(Collectors.toList()));
            searchCourse.selectFirst();
        });

        searchCourse.setOnAction(e -> {
            searchClass1.getItems().clear();
            searchClass1.getItems().addAll(teacher.getSchoolTeach()
                    .get(searchSchool.getSelectedIndex())
                    .getMajors()
                    .get(searchMajor.getSelectedIndex())
                    .getCourses()
                    .get(searchCourse.getSelectedIndex())
                    .getClasses().stream()
                    .map(ClassYearInfo::toString)
                    .collect(Collectors.toList()));
            searchClass1.selectFirst();
        });

        searchClass1.setOnAction(e -> {
            searchClass2.getItems().clear();

            List<String> list = new ArrayList<>(searchClass1.getItems());
            list.remove(searchClass1.getSelectedItem());

            searchClass2.getItems().addAll(list);
        });

        scheduleButton.setOnMouseClicked(e -> {
            if (searchSchool.getSelectedItem() == null || searchMajor.getSelectedItem() == null || searchCourse.getSelectedItem() == null || searchClass1.getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("排课失败 信息不完整");
                System.out.println("排课失败");
                alert.showAndWait();
                return;
            }
            MainController mainController = (MainController) ViewFactory.getController(ViewType.MAIN);

            Calendar calendar = mainController.getCalendar();

            String week = mainController.getSearchWeek().getSelectedItem();
            String schoolName = mainController.getSearchSchool().getSelectedItem();
            String labName = mainController.getSearchLab().getSelectedItem();

            List<String> teachersName = new ArrayList<>();
            teachersName.add(teacher.getTeacherName());

            List<ClassYearInfo> classYearInfos = new ArrayList<>();

            String className1 = searchClass1.getSelectedItem();
            // 将当前年份转换为字符串并获取前两位
            String year = String.valueOf(Year.now().getValue()).substring(0, 2) + className1.substring(0, 2);
            String className = String.valueOf(Integer.parseInt(className1.substring(2)));
            classYearInfos.add(new ClassYearInfo(className, Integer.parseInt(year)));

            if (searchClass2.getSelectedItem() != null) {
                String className2 = searchClass2.getSelectedItem();
                // 将当前年份转换为字符串并获取前两位
                year = String.valueOf(Year.now().getValue()).substring(0, 2) + className2.substring(0, 2);
                className = String.valueOf(Integer.parseInt(className2.substring(2)));
                classYearInfos.add(new ClassYearInfo(className, Integer.parseInt(year)));
            }

            ScheduleInfo scheduleInfo = ScheduleInfo.builder()
                    .teachersName(teachersName)
                    .courseName(searchCourse.getSelectedItem())
                    .majorName(searchMajor.getSelectedItem())
                    .classYearInfos(classYearInfos)
                    .note(note.getText())
                    .build();

            ScheduleReq scheduleReq = ScheduleReq.builder()
                    .account(teacher.getAccount())
                    .calendar(calendar)
                    .week(week)
                    .schoolName(schoolName)
                    .labName(labName)
                    .weekDay(weekDay)
                    .session(session)
                    .scheduleInfo(scheduleInfo)
                    .build();

            if (HttpRequest.schedule(scheduleReq)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("排课成功！\n" + scheduleInfo);
                alert.showAndWait();
            }

            mainController.refresh();
            ViewFactory.close(ViewType.SCHEDULE);
        });

        cancelScheduleButton.setOnMouseClicked(e -> {
            MainController mainController = (MainController) ViewFactory.getController(ViewType.MAIN);

            Calendar calendar = mainController.getCalendar();

            String week = mainController.getSearchWeek().getSelectedItem();
            String schoolName = mainController.getSearchSchool().getSelectedItem();
            String labName = mainController.getSearchLab().getSelectedItem();

            List<String> teachersName = new ArrayList<>();
            teachersName.add(teacher.getTeacherName());

            List<ClassYearInfo> classYearInfos = new ArrayList<>();

            String className1 = searchClass1.getSelectedItem();
            // 将当前年份转换为字符串并获取前两位
            String year = String.valueOf(Year.now().getValue()).substring(0, 2) + className1.substring(0, 2);
            String className = String.valueOf(Integer.parseInt(className1.substring(2)));
            classYearInfos.add(new ClassYearInfo(className, Integer.parseInt(year)));

            if (searchClass2.getSelectedItem() != null) {
                String className2 = searchClass2.getSelectedItem();
                // 将当前年份转换为字符串并获取前两位
                year = String.valueOf(Year.now().getValue()).substring(0, 2) + className2.substring(0, 2);
                className = String.valueOf(Integer.parseInt(className2.substring(2)));
                classYearInfos.add(new ClassYearInfo(className, Integer.parseInt(year)));
            }

            ScheduleInfo scheduleInfo = ScheduleInfo.builder()
                    .teachersName(teachersName)
                    .courseName(searchCourse.getSelectedItem())
                    .majorName(searchMajor.getSelectedItem())
                    .classYearInfos(classYearInfos)
                    .note(note.getText())
                    .build();

            ScheduleReq scheduleReq = ScheduleReq.builder()
                    .account(teacher.getAccount())
                    .calendar(calendar)
                    .week(week)
                    .schoolName(schoolName)
                    .labName(labName)
                    .weekDay(weekDay)
                    .session(session)
                    .scheduleInfo(scheduleInfo)
                    .build();
        });
    }

    public void setVisible(boolean isVisible) {
        searchSchool.setVisible(isVisible);
        searchCourse.setVisible(isVisible);
        searchMajor.setVisible(isVisible);
        searchClass1.setVisible(isVisible);
        searchClass2.setVisible(isVisible);
        note.setVisible(isVisible);
        scheduleButton.setVisible(isVisible);
    }
}
