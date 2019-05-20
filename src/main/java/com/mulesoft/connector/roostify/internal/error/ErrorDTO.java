/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mule.runtime.extension.api.annotation.Ignore;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"code",
		"title",
		"detail"
})

public class ErrorDTO {

	@Ignore
	private String code;

	private int statusCode;

	@JsonProperty("title")
	private String errorType ;

	@JsonProperty("detail")
	private String errorMessage;

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString(){
		String returnMsg = (statusCode != 0) ? "\n\nError code : " + statusCode + "\n" : "";
		returnMsg = (errorType != null) ? returnMsg + "Error type : " + errorType + "\n" : returnMsg;
		returnMsg = (errorMessage != null) ? returnMsg + "Error message : " + errorMessage + "\n\n" : returnMsg;
		return returnMsg;
	}

}


