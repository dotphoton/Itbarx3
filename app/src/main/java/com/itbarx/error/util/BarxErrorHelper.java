package com.itbarx.error.util;

import com.itbarx.error.json.BarxErrorModelParser;
import com.itbarx.error.model.BarxErrorModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarxErrorHelper {

    public boolean hasJsonErrorCode(String response) {
        boolean status = false;

        if (isJSONValid(response)) {
            status = true;
        }
        return status;
    }

    public boolean hasSoapXMLErrorCode(String response) {
        boolean status = false;

        String tag = "s:Envelope";

        if (response.indexOf(tag) != -1)
            status = true;
        return status;
    }

    public BarxErrorModel getServiceErrorModel(String response) {
        BarxErrorModel errorModel = null;
        if (hasJsonErrorCode(response)) {

            BarxErrorModelParser parser = new BarxErrorModelParser();
            errorModel= parser.getErrModelParserFromJson(response);
        }
        return errorModel;
    }

    public boolean isJSONValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {

            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
