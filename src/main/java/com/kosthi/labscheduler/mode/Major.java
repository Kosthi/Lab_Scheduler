package com.kosthi.labscheduler.mode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Major {
    private String majorName;
    private List<Course> courses;
}
