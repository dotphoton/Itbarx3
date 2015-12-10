
package com.itbarx.service.error;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.itbarx.utils.DateUtility;

import java.lang.reflect.Type;


public class BarxErrorModelDeserializer implements JsonDeserializer<BarxErrorModel> {


    @Override
    public BarxErrorModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BarxErrorModel err = new BarxErrorModel();

        if (json.getAsJsonObject().get("Result") != null) {
            if (!json.getAsJsonObject().get("Result").isJsonNull())
                err.setErrResult(json.getAsJsonObject().get("Result").getAsString());
        }

        if (json.getAsJsonObject().get("Message") != null) {
            if (!json.getAsJsonObject().get("Message").isJsonNull())
                err.setErrMessage(json.getAsJsonObject().get("Message").getAsString());
        }
        if (json.getAsJsonObject().get("Data") != null) {
            if (!json.getAsJsonObject().get("Data").isJsonNull())
                err.setErrData(json.getAsJsonObject().get("Data").toString());
        }
        if (json.getAsJsonObject().get("DataType") != null) {
            if (!json.getAsJsonObject().get("DataType").isJsonNull())
                err.setErrDataType(json.getAsJsonObject().get("DataType").getAsString());
        }

        err.setErrDateTime(DateUtility.getNowDate());
      if(!err.getErrResult().equalsIgnoreCase("true")){
          err.setIsError(true);
        }
        else {
          err.setIsError(false);
      }
        return err;

    }
}
