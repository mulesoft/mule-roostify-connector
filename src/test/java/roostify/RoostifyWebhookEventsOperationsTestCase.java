/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

public class RoostifyWebhookEventsOperationsTestCase extends MuleArtifactFunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "test-webhook-events-flow.xml";
    }

    @Test
    public void executeWebhookEventsOperation() throws Exception {
        Object payloadValue = ((CoreEvent)flowRunner("getWebhookEventsFlow").run()).getMessage().getPayload();
        JSONObject jObject  = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);
   }
}
