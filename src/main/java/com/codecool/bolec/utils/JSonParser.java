package com.codecool.bolec.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JSonParser {
    private static final String TYPE_NAME_PREFIX = "class ";

    public static String objectToJSon(Object object) {
        String serializedObject = new Gson().toJson(object);

        return serializedObject;
    }
    
    private static String getClassName(Type type) {
        if (type==null) {
            return "";
        }

        String className = type.toString();
        if (className.startsWith(TYPE_NAME_PREFIX)) {
            className = className.substring(TYPE_NAME_PREFIX.length());
        }
        return className;
    }


}
