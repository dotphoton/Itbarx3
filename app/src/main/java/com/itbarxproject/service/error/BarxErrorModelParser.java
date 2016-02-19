package com.itbarxproject.service.error;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * TODO: Add a class header comment!
 */
public class BarxErrorModelParser {

    public BarxErrorModel getErrModelParserFromJson(String jsonString) {
        BarxErrorModel err = null;

        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(BarxErrorModel.class, new BarxErrorModelDeserializer()).create();
            Type type = new TypeToken<BarxErrorModel>() {
            }.getType();
            err = gson.fromJson(jsonString, type);

            /*
            if (err != null) {
                if (!err.getIsError()) {

                    return err;
                }
            }
            */
        } catch (Exception ex) {

            return err;
        }

        return err;
    }

    public String getErrModelJSONString(BarxErrorModel barxErrorModel)
    {
        Gson gson = new GsonBuilder().registerTypeAdapter(BarxErrorModel.class, new BarxErrorModelSerializer()).setPrettyPrinting().create();

        return gson.toJson(barxErrorModel);
    }
}
