/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.metadata;

import com.mulesoft.connector.roostify.api.resultObject.StaticPayloadDTO;

import javax.ws.rs.core.Response;

public class StaticPayloadGenerator {

	public static StaticPayloadDTO setPayload(Response response){
		StaticPayloadDTO status = new StaticPayloadDTO();
		status.setStatusCode(response.getStatus());
		if(response.getStatus() == 204) {
			status.setSuccess(true);
		}
		return status;
	}
}
