package customers;

import com.banking.clients.CustomerClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CustomerDELETE {

    @Test
    public void deleteSingleCustomer() {
        CustomerClient client = new CustomerClient();
        Response response = client.deleteSingleCustomerResponse(32);
        System.out.println(response.prettyPrint());
        JsonPath jsonPath = response.jsonPath();
        String createdAt = jsonPath.getString("createdAt");
        response
                .then()
                .statusCode(200)
                .body("createdAt", equalTo(createdAt))
                .contentType("application/json")
                .header("server", "Heroku");
    }
}
