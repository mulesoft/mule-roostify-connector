/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;

public class RoostifySettingsOperationsIT extends MuleArtifactFunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "test-retrieve-settings_leads-flow.xml";
    }

    @Test
    public void executeRetrievingSettingsOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("retrievingSettingsFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }

    @Test
    public void executeCreateLeadOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createLeadFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeGetLeadOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("getLeadFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }

}
