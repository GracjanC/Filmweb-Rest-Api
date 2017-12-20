package com.codecool.bolec.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JSonParser {

    public static String objectToJSon(Object object) {
        String serializedObject = new Gson().toJson(object);

        return serializedObject;
    }

    public static String  listToJSon (List<?> objects) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<?>>() {}.getType();
        String json = gson.toJson(objects, listType);

        return json;

    }

    public static Object jsonToObject(String json, Type type) throws ClassNotFoundException{
        Class<?> className = ReflectionHelpers.getClass(type);
        Object object = new Gson().fromJson(json, className);

        return object;
    }
}
