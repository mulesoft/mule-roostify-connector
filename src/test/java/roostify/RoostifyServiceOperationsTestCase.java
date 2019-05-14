/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Assert;
import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

public class RoostifyServiceOperationsTestCase extends MuleArtifactFunctionalTestCase {
    @Override
    protected String getConfigFile() {
        return "test-retrieve-your-service-flow.xml";
    }

    @Test
    public void executeYourServiceOperation() throws Exception {
        Object payloadValue = ((CoreEvent) flowRunner("retrieveYourServiceFlow").run()).getMessage().getPayload();
	    Assert.assertNotNull(payloadValue);
    }
}
