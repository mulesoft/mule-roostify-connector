/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.internal.services;

import com.mulesoft.connectors.roostify.api.response.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface DocumentsService extends ConnectorService {

	Result< InputStream, ResponseStatus> listingDocuments(String page, String count,String filter,String search);
	Result<InputStream, ResponseStatus> retrievingDocument(String find_id);
	Result<InputStream, ResponseStatus> downloadingDocument(String id);
	Result<InputStream, ResponseStatus> creatingDocument(Map<String,Object> document);
	Result<InputStream, ResponseStatus> updateDocument(Map<String,Object>  documentBody, String id);


	}
