/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.api.resultObject;

import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AttributesUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(AttributesUtil.class);
    public AttributesUtil() {
    }

    public static ResponseStatus setResponseAttributes(CompletableFuture<HttpResponse> response) {
        try {
            ResponseStatus responseStatus = new ResponseStatus();
            responseStatus.setStatusCode(response.get().getStatusCode());
            responseStatus.setHeaders(response.get().getHeaders());
            return responseStatus;
        }catch (ExecutionException e){
            LOGGER.info("Error : "+e);
        }catch (InterruptedException e){
            LOGGER.info("Error : "+e);
        }
        return  null;
    }

    public static ResponseStatus setResponseAttributesForSend(HttpResponse response) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(response.getStatusCode());
        responseStatus.setHeaders(response.getHeaders());
        return responseStatus;
    }


}
