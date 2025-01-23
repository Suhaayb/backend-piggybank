package com.testing.piggybank.transaction;

import com.testing.piggybank.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_createTransaction_responseOk() {
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setAmount(new BigDecimal("100"));
        createTransactionRequest.setReceiverAccountId(2L);
        createTransactionRequest.setSenderAccountId(1L);
        createTransactionRequest.setCurrency(Currency.EURO);
        createTransactionRequest.setDescription("Test transaction");

        HttpEntity<CreateTransactionRequest> request = new HttpEntity<>(createTransactionRequest);

        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(
                "/api/v1/transactions", request, HttpStatus.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void test_getTransactions_validAccount() {
        ResponseEntity<GetTransactionsResponse> response = restTemplate.getForEntity(
                "/api/v1/transactions/1?limit=2", GetTransactionsResponse.class);

        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().getTransactions().size());
    }

}
