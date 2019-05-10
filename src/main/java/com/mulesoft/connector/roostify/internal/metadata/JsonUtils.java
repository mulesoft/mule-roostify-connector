/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connector.roostify.internal.metadata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mulesoft.connector.roostify.internal.exception.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
    }

    public static Map<String, Object> transformResToMap(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        String res = response.readEntity(String.class);
        Map<String, Object> map = new HashMap<>();
        if (!res.isEmpty()) {
            try {
                map = mapper.readValue(res, new TypeReference<Map<String, Object>>() {
                });
            } catch (IOException e) {
                LOGGER.info("Error message : " + e);
            }
        }
        return map;
    }

    public static String transformObjToMap(Object ressponse) {
        String jsonInString = new String();
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(ressponse);
        } catch (Exception E) {
            LOGGER.info("Error message : " + E);
        }
        return jsonInString;
    }

/*    public static List<Map<String, Object>> transformResToList(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = new ArrayList<>();
        JSONArray jsonarray = new JSONArray(response);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Map<String, Object> map = new HashMap<>();
            try {
                map = mapper.readValue(jsonobject.toString(), new TypeReference<Map<String, Object>>() {

                });
                list.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }*/

    public static String transformObjectToString(Object ressponse) {
        System.out.println("in transform");
        String jsonInString = new String();
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(ressponse);
        }
        catch (Exception E){ LOGGER.info("Error message : " + E);}
        return jsonInString;
    }

}