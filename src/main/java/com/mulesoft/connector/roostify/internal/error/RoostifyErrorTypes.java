/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.error;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashMap;
import java.util.Map;

public enum RoostifyErrorTypes implements ErrorTypeDefinition<RoostifyErrorTypes> {

	BAD_REQUEST(400),
	UNAUTHORIZED(401),
	UN_PROCESSABLE_ENTITY(422),
	FORBIDDEN(403),
	NOT_FOUND(404),
	METHOD_NOT_ALLOWED(405),
	NOT_ACCEPTABLE(406),
	REQUEST_TIMEOUT(408),
	CONFLICT(409),
	INTERNAL_SERVER_ERROR(500),
	NOT_IMPLEMENTED(501),
	BAD_GATEWAY(502),
	SERVICE_UNAVAILABLE(503),
	EMPTY_HEAD_COUNT(0, "X-Total-Count header is Blank"),
	DEAD_TOKEN(1, "Dead access token"),
	GENERIC_EXCEPTION(2);

	private static Map<Integer, RoostifyErrorTypes> map = new HashMap<Integer, RoostifyErrorTypes>();
	public Integer status;
	public String message;

	static {
		for (RoostifyErrorTypes error : RoostifyErrorTypes.values()) {
			map.put(error.status, error);
		}
	}

	RoostifyErrorTypes(Integer status) {
		this.status = status;
	}

	RoostifyErrorTypes(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public static RoostifyErrorTypes valueOf(int status) {
		return map.get(status);
	}


}
