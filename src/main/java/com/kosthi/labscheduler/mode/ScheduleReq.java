package com.kosthi.labscheduler.mode;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ScheduleReq {
    private String account;
    private Calendar calendar;
    private String week;
    private String schoolName;
    private String labName;
    private Character weekDay;
    private Character session;
    private ScheduleInfo scheduleInfo;
}
