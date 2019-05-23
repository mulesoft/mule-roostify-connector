/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.internal.connection.provider;

import org.mule.connectors.commons.template.connection.ConnectorConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mulesoft.connector.roostify.internal.connection.RoostifyConnection;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public abstract class RoostifyConnectionProvider extends ConnectorConnectionProvider<RoostifyConnection> implements ConnectionProvider<RoostifyConnection> {

    private static final Logger logger = LoggerFactory.getLogger(RoostifyConnectionProvider.class);

    @Override
    public void disconnect(RoostifyConnection connection) {
        try {
            connection.invalidate();
        } catch (Exception e) {
            logger.info("Error while disconnecting :", e);
        }
    }

}
