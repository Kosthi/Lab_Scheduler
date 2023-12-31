package com.kosthi.labscheduler.mode;

import com.google.gson.reflect.TypeToken;
import com.kosthi.labscheduler.util.OkHttpUtil;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpRequest {
    public static LocalDate getStartDate() {
        String uri = "http://localhost:8080/api/startDate";
        return LocalDate.parse(Objects.requireNonNull(OkHttpUtil.get(uri, null)));
    }

    public static Map<String, List<String>> getSchoolLabs() {
        String uri = "http://localhost:8080/api/schoolLabs";
        // 创建表示 Map<String, List<String>> 的类型标记
        Type type = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        return OkHttpUtil.GSON.fromJson(Objects.requireNonNull(OkHttpUtil.get(uri, null)), type);
    }

    // 星期几 -> 顺序排课安排
    public static Map<Character, List<String>> getSchedule(String week, String schoolName, String labName) {
        String uri = "http://localhost:8080/api/schedule?week=" + week + "&schoolName=" + schoolName + "&labName=" + labName;
        // 创建表示 Map<String, List<String>> 的类型标记
        Type type = new TypeToken<Map<Character, List<String>>>() {
        }.getType();
        return OkHttpUtil.GSON.fromJson(Objects.requireNonNull(OkHttpUtil.get(uri, null)), type);
    }
}
