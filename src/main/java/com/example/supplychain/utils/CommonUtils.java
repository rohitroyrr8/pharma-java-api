package com.example.supplychain.utils;

public class CommonUtils {
    public static boolean isNull(String value) {
        if(value == null) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Object value) {
        if(value == null) {
            return true;
        }
        return false;
    }
}
