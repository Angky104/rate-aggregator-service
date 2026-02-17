package com.allobank.angky.rateaggregatorservice.client.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate> {

    @Override
    public RestTemplate getObject() {
        return new RestTemplate();
    }

    @Override
    public Class<?> getObjectType() {
        return RestTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
