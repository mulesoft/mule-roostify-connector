/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package roostify;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.event.CoreEvent;

import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class RoostifyAccessControlOperationsIT extends MuleArtifactFunctionalTestCase  {


    @Override
    protected String getConfigFile() {
        return "test-access-control-flow.xml";
    }

    @Test
    public void executeCreateAccessControl() throws Exception {
        Random random = new Random(System.nanoTime());
        int number = random.nextInt(1000000000);
        System.out.println("number vale" + number);
        Object payloadValue = ((CoreEvent)flowRunner("createAccessControlGroupFlow").withPayload(number)
                .run())
                .getMessage()
                .getPayload()
                .getValue();
        assertNotNull(payloadValue);
    }

    @Test
    public void executeUpdateAccessControl() throws Exception {
        Object payloadValue = ((CoreEvent)flowRunner("updateAccessControlGroupFlow")
                .run())
                .getMessage()
                .getPayload()
                .getValue();
        assertNotNull(payloadValue);

    }
}
