/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import static org.junit.Assert.assertNotNull;

public class RoostifyStatusUpdateOperationsTestCase extends MuleArtifactFunctionalTestCase {

    @Override
    protected String getConfigFile() {
        return "test-creating-status-update-flow.xml";
    }

    @Test
    public void executeCreateStatusUpdateOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("createStatusUpdateFlow").run()).getMessage().getPayload().getValue();
        assertNotNull(payloadValue);
    }
}
