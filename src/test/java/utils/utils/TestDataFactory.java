package utils.utils;

import com.banking.config.ConfigReader;
import com.banking.model.CustomerRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataFactory {

    public static CustomerRequest createCustomerRequest() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        int number = ThreadLocalRandom.current().nextInt(100000000, 1000000000);
        Map<String, Object> map = new HashMap<>();
        map.put("firstLine", "19 St. Ives Road");
        map.put("postCode", "SL5 9ZQ");
        return CustomerRequest.builder()
                .firstName("Shyam")
                .lastName("Sharma")
                .email("shyam." + unique + "@mail.com")
                .address(map)
                .phone("07" + number)
                .status("ACTIVE")
                .build();
    }

    public static CustomerRequest customerPartialUpdate(String update) {
        update = ConfigReader.getProperty(update);
        Map<String, Object> map = new HashMap<>();
        map.put("postCode", ConfigReader.getProperty("customer.address.postCode"));
        map.put("firstLine", ConfigReader.getProperty("customer.address.postCode"));
        return switch (update) {
            case "phone number" -> CustomerRequest.builder()
                    .phone(ConfigReader.getProperty("customer.phone"))
                    .build();

            case "firstName" -> CustomerRequest.builder()
                    .firstName(ConfigReader.getProperty("customer.firstName"))
                    .build();

            case "lastName" -> CustomerRequest.builder()
                    .lastName(ConfigReader.getProperty("customer.lastName"))
                    .build();

            case "first line" -> CustomerRequest.builder()
                    .address(map)
                    .build();

            case "post code" -> CustomerRequest.builder()
                    .address(map)
                    .build();

            case "email" -> CustomerRequest.builder()
                    .email(ConfigReader.getProperty("customer.email"))
                    .build();

            case "status" -> CustomerRequest.builder()
                    .status(ConfigReader.getProperty("customer.status"))
                    .build();

            default -> throw new RuntimeException("Address not found");
        };
    }
}
