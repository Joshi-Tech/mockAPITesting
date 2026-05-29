package customers;

import com.banking.clients.CustomerClient;
import com.banking.model.CustomerRequest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.utils.TestDataFactory;

import static customers.CustomerPOST.getId;
import static org.hamcrest.Matchers.equalTo;

public class CustomerPUT {

    @Test
    public static void customerUpdate() {
        CustomerRequest request = TestDataFactory.createCustomerRequest();
        CustomerClient client = new CustomerClient();
        Response response = client.updateACustomer(request, getId());
        JsonPath jsonPath = response.jsonPath();
        String createdAt = jsonPath.getString("createdAt");
        String postcode = jsonPath.getString("address.postCode");
        String firstLine = jsonPath.getString("address.firstLine");
        System.out.println("id in PUT call: "+ getId());
        response
                .then()
                .statusCode(200)
                .body("createdAt", equalTo(createdAt))
                .body("address.firstLine", equalTo(firstLine))
                .body("address.postCode", equalTo(postcode))
                .contentType(ContentType.JSON)
                .header("server", "Heroku")
                .header("x-powered-by", "Express");
    }
}
