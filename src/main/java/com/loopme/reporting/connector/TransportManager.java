/*******************************************************************************
 * Copyright (c) 2014 PE INTERNATIONAL AG.
 * All rights reserved.
 *******************************************************************************/
package com.loopme.reporting.connector;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class TransportManager {

   private static Client client;
   private static HttpClientConnectionManager connectionManager;


   public static void init() {
      ClientConfig clientConfig = new ClientConfig();

      // setup http connections pooling for REST client
      connectionManager = new PoolingHttpClientConnectionManager();
      ((PoolingHttpClientConnectionManager)connectionManager).setMaxTotal(Integer.MAX_VALUE);
      ((PoolingHttpClientConnectionManager)connectionManager).setDefaultMaxPerRoute(20);
      clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);

      clientConfig.connectorProvider(new ApacheConnectorProvider());
      RequestConfig reqConfig = RequestConfig.custom()
            .setConnectTimeout(5000)
            .setSocketTimeout(20000)
            .setConnectionRequestTimeout(10000)
            .build();
      clientConfig.property(ApacheClientProperties.REQUEST_CONFIG, reqConfig);

      client = ClientBuilder.newClient(clientConfig);
   }


   public static void destroy() {
      client.close();
   }

   public static Client getClient() {
      return client;
   }

   public static HttpClientConnectionManager getConnectionManager() {
      return connectionManager;
   }

}
