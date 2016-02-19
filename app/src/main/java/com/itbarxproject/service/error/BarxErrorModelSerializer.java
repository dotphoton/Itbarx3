package com.itbarxproject.service.error;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * TODO: Add a class header comment!
 */
public class BarxErrorModelSerializer implements JsonSerializer<BarxErrorModel> {
    @Override
    public JsonElement serialize(BarxErrorModel src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject object = new JsonObject();
        object.addProperty("Result", src.getErrResult());
        object.addProperty("Message", src.getErrMessage());
        object.addProperty("Data", src.getErrData());
        object.addProperty("DataType", src.getErrDataType());
        return object;
    }
}
