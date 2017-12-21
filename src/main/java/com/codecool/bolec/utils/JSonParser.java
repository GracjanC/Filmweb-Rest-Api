package com.codecool.bolec.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JSonParser<T> {

    public String objectToJSon(T object) {
        String serializedObject = new Gson().toJson(object);

        return serializedObject;
    }

    public String listToJSon (List<T> objects) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<T>>() {}.getType();
        String json = gson.toJson(objects, listType);

        return json;

    }

    public T jsonToObject(String json, Type type) throws ClassNotFoundException{
        Class<?> className = ReflectionHelpers.getClass(type);
        T object = (T) new Gson().fromJson(json, className);

        return object;
    }
}
