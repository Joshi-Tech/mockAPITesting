package com.banking.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecFactory {

    private RequestSpecFactory() {
    }

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getProperty("base.url"))
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .build();
    }
}