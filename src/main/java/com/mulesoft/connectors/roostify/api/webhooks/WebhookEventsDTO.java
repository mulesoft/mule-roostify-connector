/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.roostify.api.webhooks;

import java.util.ArrayList;
import java.util.List;

public class WebhookEventsDTO {
    List<Object> webhookEvents = new ArrayList<Object>();

    public List<Object> getWebhookEvents() {
        return webhookEvents;
    }

    public void setWebhookEvents(List<Object> webhookEvents) {
        this.webhookEvents = webhookEvents;
    }
}
