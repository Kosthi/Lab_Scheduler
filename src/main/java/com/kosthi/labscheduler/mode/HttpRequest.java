package com.kosthi.labscheduler.mode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kosthi.labscheduler.util.LocalDateAdapter;
import com.kosthi.labscheduler.util.MD5Util;
import com.kosthi.labscheduler.util.OkHttpUtil;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpRequest {
    public static Calendar getCalendar() {
        String uri = "http://localhost:8080/api/calendar";
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.fromJson(OkHttpUtil.get(uri, null), Calendar.class);
    }

    public static Map<String, List<String>> getSchoolLabs() {
        String uri = "http://localhost:8080/api/schoolLabs";
        // 创建表示 Map<String, List<String>> 的类型标记
        Type type = new TypeToken<Map<String, List<String>>>() {
        }.getType();
        return OkHttpUtil.GSON.fromJson(Objects.requireNonNull(OkHttpUtil.get(uri, null)), type);
    }

    // 星期几 -> 1排课安排
    public static Map<Character, List<String>> getSchedule(String week, String schoolName, String labName) {
        String uri = "http://localhost:8080/api/schedule?week=" + week + "&schoolName=" + schoolName + "&labName=" + labName;
        // 创建表示 Map<String, List<String>> 的类型标记
        Type type = new TypeToken<Map<Character, List<String>>>() {
        }.getType();
        return OkHttpUtil.GSON.fromJson(Objects.requireNonNull(OkHttpUtil.get(uri, null)), type);
    }

    public static boolean checkIfUserExists(String account) {
        String uri = "http://localhost:8080/api/exist?account=" + account;
        return Boolean.parseBoolean(OkHttpUtil.get(uri, null));
    }

    public static Teacher login(String account, String password) {
        String uri = "http://localhost:8080/api/login";

        // 创建请求体,并添加数据（body参数不需要时，可以省略，需要时添加到bodyParam中即可.）
        Map<String, Object> bodyParam = new HashMap<>(2);
        // 与json中的键值一致
        bodyParam.put("account", account);
        bodyParam.put("password", MD5Util.encrypt(password));

        return OkHttpUtil.GSON.fromJson(OkHttpUtil.postJson(uri, bodyParam), Teacher.class);
    }

    public static void logout(String account) {
        String uri = "http://localhost:8080/api/logout?account=" + account;
        OkHttpUtil.postJson(uri, null);
    }

    public static Boolean schedule(ScheduleReq scheduleReq) {
        String uri = "http://localhost:8080/api/schedule";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return Boolean.parseBoolean(OkHttpUtil.post(uri, null, gson.toJson(scheduleReq)));
    }
}
