# Roostify Connector Demo

The Roostify Connector will allow to connect to the Roostify application. Almost every operation that can be done via the Roostify's API can be done through this connector. 

```
<dependency>
    <groupId>com.mulesoft.connectors</groupId>
    <artifactId>mule-roostify-connector</artifactId>
    <version>1.0.0</version>
    <classifier>mule-plugin</classifier>
</dependency>
```

1. Add the configuration values in application.properties located at src/main/resources.
2. Check if global-configuration.xml has picked the properties by clicking on Test Connection.
3. Open one of the mule configuration files and run the project.
4. Once the project gets deployed successfully, hit the end points mentioned in listener path from any rest client. 
5. For all the localhost endpoints and valid payload values please import the "Roostify Localhost Collection.postman_collection.json" file in postman client.
6. Note that below listed services do not return any value.Instead they return a {} response and a success code 200/204.
 
        1) Create a Status Update
		2) Deactivate a User
		3) Activate a User  

Note: TLS configuration is not mandatory for running the connector. If you want to use configurations you should have keystore.jks in src/main/resources directory, demo file for keystore.jks packaged within the demo for reference.