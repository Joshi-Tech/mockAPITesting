import com.banking.clients.CustomerClient;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.utils.TestDataFactory;

public class CustomerSchemaValidation {

    @Test
    public void schemaValidation(){
        CustomerClient client = new CustomerClient();
        Response response = client.getSingleCustomerResponse(15);
        response
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("customerSchema.json"));
    }
}
