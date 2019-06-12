/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;

public class RoostifyMessagesOperationsIT extends MuleArtifactFunctionalTestCase {
    @Override
    protected String getConfigFile() {
        return "test-message-flow.xml";
    }

    @Test
    public void executeMessagesOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getMessageFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeListingMessagesOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getMessagesFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateMessageOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createMessageFlow").run()).getMessage().getPayload().getValue();
       assertNotNull(payloadValue);
    }
}
