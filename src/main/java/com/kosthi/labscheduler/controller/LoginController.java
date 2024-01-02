package com.kosthi.labscheduler.controller;

import com.kosthi.labscheduler.mode.HttpRequest;
import com.kosthi.labscheduler.mode.Teacher;
import com.kosthi.labscheduler.view.ViewFactory;
import com.kosthi.labscheduler.view.ViewType;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginController implements Initializable {
    @FXML
    private TextField account;
    @FXML
    private PasswordField password;
    @FXML
    private MFXButton loginButton;
    @FXML
    private Label accountError;
    @FXML
    private Label passwordError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event -> {
            if (checkUserInfo()) {
                Teacher teacher = HttpRequest.login(account.getText(), password.getText());
                if (teacher == null) {
                    passwordError.setText("！密码不匹配");
                    return;
                }

                account.clear();
                password.clear();
                ViewFactory.close(ViewType.LOGIN);

                MainController mainController = (MainController) ViewFactory.getController(ViewType.MAIN);
                mainController.setTeacher(teacher);
                mainController.switchToLogin();

                ScheduleController scheduleController = (ScheduleController) ViewFactory.getController(ViewType.SCHEDULE);
                scheduleController.setTeacher(teacher);
                scheduleController.initialized();
                // ViewFactory.show(ViewType.SCHEDULE);
            }
        });
    }

    private void clearAllError() {
        accountError.setText("");
        passwordError.setText("");
    }

    private boolean checkUserInfo() {
        clearAllError();

        String Account = account.getText();
        String Password = password.getText();
        if (Account.isEmpty() || Password.isEmpty()) {
            if (Account.isEmpty()) {
                accountError.setText("！未输入账号");
            }
            if (Password.isEmpty()) {
                passwordError.setText("！未输入密码");
            }
            return false;
        }
        String accountRegExp = "^\\d{5}$";
        String passwordReExp = "^[a-zA-Z0-9]{6,20}$";
        if (!Pattern.matches(accountRegExp, Account) || !Pattern.matches(passwordReExp, Password)) {
            if (!Pattern.matches(accountRegExp, Account)) {
                accountError.setText("！错误,账号是5位职工号");
            }
            if (!Pattern.matches(passwordReExp, Password)) {
                passwordError.setText("！错误,密码是长度在6-20位的英文字母和数字");
            }
            return false;
        }
        if (!HttpRequest.checkIfUserExists(Account)) {
            accountError.setText("！错误,账号不存在");
            return false;
        }
        return true;
    }
}
