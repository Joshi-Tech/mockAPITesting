package com.banking.constants;

public final class ApiEndpoints {

    private ApiEndpoints() {
    }

    public static final String CUSTOMERS = "/customer";
    public static final String CUSTOMER = "/customer/{id}";
    public static final String ACCOUNT_BY_ID = "/api/v1/accounts/{accountId}";
    public static final String DEPOSIT = "/api/transactions/deposit";
    public static final String WITHDRAW = "/accounts/{accountId}/withdraw";
    public static final String TRANSFERS = "/transfers";
    public static final String TRANSACTIONS = "/accounts/{accountId}/transactions";
}