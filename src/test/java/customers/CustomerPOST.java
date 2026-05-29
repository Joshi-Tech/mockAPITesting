package customers;

import com.banking.clients.CustomerClient;
import com.banking.model.CustomerRequest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import utils.utils.TestDataFactory;

import static org.hamcrest.Matchers.equalTo;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerPOST {
    public static int id;

    @Test
    public static void shouldCreateCustomerSuccessfully() {
        CustomerRequest dataFactory = TestDataFactory.createCustomerRequest();
        CustomerClient client = new CustomerClient();
        Response response = client.createCustomerResponse(dataFactory);
        JsonPath jsonPath = response.jsonPath();
        String postcode = jsonPath.getMap("address").get("postCode").toString();
        String firstline = jsonPath.getMap("address").get("firstLine").toString();
        id = Integer.parseInt(jsonPath.get("id"));
        System.out.println("id in POST call: " + id);
        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .header("server", "Heroku")
                .header("x-powered-by", "Express")
                .body("address.postCode", equalTo(postcode))
                .body("address.firstLine", equalTo(firstline))
                .body("status", equalTo("ACTIVE"));
    }

    public static int getId() {
        return id;
    }
}
