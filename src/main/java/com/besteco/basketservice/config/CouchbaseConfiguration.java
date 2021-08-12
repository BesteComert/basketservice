package com.besteco.basketservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.host}")
    private String connectionString;

    @Value("${couchbase.userName}")
    private String userName;

    @Value("${couchbase.password}")
    private String password;

    @Value("${couchbase.bucketName}")
    private String bucketName;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }
}

