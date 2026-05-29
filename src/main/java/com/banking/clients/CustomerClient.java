package com.banking.clients;

import com.banking.config.ConfigReader;
import com.banking.config.RequestSpecFactory;
import com.banking.constants.ApiEndpoints;
import com.banking.model.CustomerRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CustomerClient {

    public Response createCustomerResponse(CustomerRequest request) {
        return given()
                .spec(RequestSpecFactory.getRequestSpecification())
                .body(request)
                .when()
                .post(ApiEndpoints.CUSTOMERS);
    }

    public Response getCustomerResponse() {
        return given()
                .when()
                .get(ConfigReader.getProperty("base.url") + ApiEndpoints.CUSTOMERS);
    }

    public Response getSingleCustomerResponse(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(ConfigReader.getProperty("base.url") + ApiEndpoints.CUSTOMER);
    }

    public Response updateACustomer(CustomerRequest request, int id) {
        return given()
                .pathParam("id", id)
                .spec(RequestSpecFactory.getRequestSpecification())
                .body(request)
                .when()
                .put(ConfigReader.getProperty("base.url") + ApiEndpoints.CUSTOMER);
    }

    public Response updateACustomerDetailsPartially(CustomerRequest request, int id) {
        return given()
                .pathParam("id", id)
                .spec(RequestSpecFactory.getRequestSpecification())
                .body(request)
                .when()
                .patch(ConfigReader.getProperty("base.url") + ApiEndpoints.CUSTOMER);
    }

    public Response deleteSingleCustomerResponse(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(ConfigReader.getProperty("base.url") + ApiEndpoints.CUSTOMER);
    }
}
