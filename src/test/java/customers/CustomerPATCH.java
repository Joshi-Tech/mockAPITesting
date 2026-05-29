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

public class CustomerPATCH {

    @Test
    public static void customerUpdate() {

        CustomerRequest request = TestDataFactory.customerPartialUpdate("customer.detailChanged");
        CustomerClient client = new CustomerClient();
        Response response = client.updateACustomerDetailsPartially(request, getId());
        JsonPath jsonPath = response.jsonPath();
        String createdAt = jsonPath.getString("createdAt");
        String postcode = jsonPath.getString("address.postCode");
        String firstLine = jsonPath.getString("address.firstLine");
        System.out.println("id in PATCH call: "+getId());
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
