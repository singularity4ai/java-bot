package com.singularai.watty.utils;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

/**
 * Created by vamsi on 13/03/17.
 */
public class RestUtilities {

    public static String buildSuccessResponse(Object data) {
        return buildSuccessResponse(data, "data");
    }

    public static String buildSuccessResponse(Object data, String key) {
        JSONObject json = new JSONObject();
        json.put("status", Response.Status.ACCEPTED);
        if(data != null)
            json.put(key, data);
        return json.toString();
    }

    public static String buildFailureResponse(Object data, String key) {
        JSONObject json = new JSONObject();
        json.put("status", Response.Status.INTERNAL_SERVER_ERROR);
        if(data != null)
            json.put(key, data);
        return json.toString();
    }
}
