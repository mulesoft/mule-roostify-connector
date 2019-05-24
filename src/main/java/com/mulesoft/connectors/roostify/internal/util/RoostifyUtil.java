/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RoostifyUtil {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RoostifyUtil.class);

    public static InputStream getInputStreamData(Object obj){

        Gson gson = new Gson();
        String json = gson.toJson(obj);

        InputStream inputStreamData = null;
        try {
            inputStreamData = IOUtils.toInputStream(json, "UTF-8");

        } catch (IOException e) {
            logger.info("Error : ", e);
        }
        return inputStreamData;
    }

    public static byte[] getByteArrayDataOfMap(Map mapObject){

        Gson gson = new Gson();
        String jsonBody = gson.toJson(mapObject);
        byte[] byteArrayData = jsonBody.getBytes(StandardCharsets.UTF_8);
        return byteArrayData;
    }

    public static InputStream getContentInputStream(CompletableFuture<HttpResponse> response) {

        InputStream inputStream = null;
        try {
            inputStream = response.get().getEntity().getContent();
            return inputStream;
        } catch (ExecutionException ex) {
            logger.info("Error : ", ex);
        } catch (InterruptedException ex) {
            logger.info("Error : ", ex);
        } catch (Exception ex) {
            logger.info("Error : ", ex);
        }
        return inputStream;
    }

    public static List<Object> getStreamList (InputStream response){
        ObjectMapper mapper = new ObjectMapper();
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Object.class);
        List<Object> dtos = new ArrayList<Object>();
        try {
            dtos = mapper.readValue(response, collectionType);
        }
        catch(IOException e)
        {
            logger.info("Error : ", e);
        }

        return dtos;
    }


}
