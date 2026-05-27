# Mock API Automation Framework

This project is a Java-based API automation testing framework for customer banking APIs.

## Tech Stack

- Java
- Maven
- JUnit
- Rest Assured
- JSON Schema Validation

## Project Structure

```text
src
├── main
│   ├── java/com/banking
│   │   ├── clients
│   │   ├── config
│   │   ├── constants
│   │   └── model
│   └── resources/config
│       └── customerSchema.json
└── test
    └── java/customers
        ├── CustomerGET
        ├── CustomerPOST
        ├── CustomerPUT
        ├── CustomerPATCH
        ├── CustomerDELETE
        └── CustomerSchemaValidation