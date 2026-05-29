package customers;

import com.banking.clients.CustomerClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static customers.CustomerPOST.getId;
import static org.hamcrest.Matchers.equalTo;

public class CustomerDEL {

    @Test
    public void deleteSingleCustomer() {
        CustomerClient client = new CustomerClient();
        Response response = client.deleteSingleCustomerResponse(getId());
        JsonPath jsonPath = response.jsonPath();
        String createdAt = jsonPath.getString("createdAt");
        System.out.println("id in DELETE call: " + getId());
        response
                .then()
                .statusCode(200)
                .body("createdAt", equalTo(createdAt))
                .contentType("application/json")
                .header("server", "Heroku");
    }
}
