package com.master.nst.elasticsearch;

import com.master.nst.elasticsearch.util.ElasticSearchUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Scope;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Scope(scopeName = "singleton")
public class ElasticSearchClient {

    private Client client;

    public ElasticSearchClient() {
        Settings settings = Settings.builder().put("cluster.name", ElasticSearchUtil.CLUSTER_NAME).build();
        TransportClient transportClient = new PreBuiltTransportClient(settings);
        try {
            transportClient = transportClient
                .addTransportAddress(new TransportAddress(InetAddress.getByName(ElasticSearchUtil.IP_ADDRESS), ElasticSearchUtil.PORT));
        } catch (UnknownHostException e) {
            return;
        }
        client = transportClient;
    }

    public Client getClient() {
        return client;
    }

}
