package customers;

import com.banking.clients.CustomerClient;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static customers.CustomerPOST.getId;
import static org.hamcrest.Matchers.equalTo;

public class CustomerGET {

    @Test
    public void getCustomers() {
        CustomerClient client = new CustomerClient();
        Response response = client.getCustomerResponse();
        JsonPath jsonPath = response.jsonPath();
        String createdAt = jsonPath.getString("[0].createdAt");
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("server", "Heroku")
                .body("[0].createdAt", equalTo(createdAt));
    }

    @Test
    public void getSingleCustomer() {
        CustomerClient client = new CustomerClient();
        Response response = client.getSingleCustomerResponse(getId());

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("id"));
        String createdAt = jsonPath.getString("createdAt");
        System.out.println("id in GET call: "+getId());
        response
                .then()
                .statusCode(200)
                .body("createdAt", equalTo(createdAt))
                .contentType("application/json")
                .header("server", "Heroku");
    }
}
