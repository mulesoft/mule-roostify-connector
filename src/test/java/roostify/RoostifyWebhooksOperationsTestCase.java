/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RoostifyWebhooksOperationsTestCase extends MuleArtifactFunctionalTestCase {


    @Override
    protected String getConfigFile() {
        return "test-webhooks_reference-flow.xml";
    }

    @Test
    public void executeWebhookListsOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getWebhooksFlow").run()).getMessage().getPayload();
        JSONObject jObject  = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);

    }

    @Test
    public void executeCreateWebhookOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createWebhookFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeRetrieveWebhookOperation() throws Exception {
        Object payloadValueWebhook = ((CoreEvent) flowRunner("createWebhookFlow").run()).getMessage().getPayload();
        JSONObject jObjectWebhook  = new JSONObject(payloadValueWebhook);
        Assert.assertNotNull(jObjectWebhook);
    }

    @Test
    public void executeDeleteWebhookOperation() throws Exception {

        Object payloadValue = ((CoreEvent) flowRunner("deleteWebhookFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executecreateOrUpdateReferenceIdOperation() throws Exception {
        Object payloadValueWebhook = ((CoreEvent) flowRunner("createOrUpdateReferenceIdFlowFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValueWebhook);
    }

}
