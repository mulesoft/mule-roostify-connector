/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.services;

import com.mulesoft.connector.roostify.api.response.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface WebhookService extends ConnectorService {

	Result<InputStream, ResponseStatus> listingWebhooks(String page, String count);
	Result<InputStream,ResponseStatus> retrievingWebhook(String webhookId);
	Result<InputStream,ResponseStatus> creatingWebhook(Map<String, Object> webhookBody);
	Result<InputStream,ResponseStatus> deletingWebhook(String webhookId);
}
