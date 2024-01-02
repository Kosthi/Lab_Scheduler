package com.kosthi.labscheduler.mode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private String account;
    private String password;
    private Boolean isAdmin;
}
