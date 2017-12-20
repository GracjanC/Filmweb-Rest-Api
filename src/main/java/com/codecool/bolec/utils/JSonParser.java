package com.codecool.bolec.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JSonParser {
    private static final String TYPE_NAME_PREFIX = "class ";

    public static String objectToJSon(Object object) {
        String serializedObject = new Gson().toJson(object);

        return serializedObject;
    }
    
}
