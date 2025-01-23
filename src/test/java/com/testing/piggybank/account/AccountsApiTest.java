package com.testing.piggybank.account;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpHeaders;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountsApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAccounts() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-User-Id", "1");

        // Act
        ResponseEntity<GetAccountsResponse> response =
                restTemplate.exchange("/api/v1/accounts", HttpMethod.GET,
                        new HttpEntity<>(headers), GetAccountsResponse.class);
    }

}
