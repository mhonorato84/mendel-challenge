package com.mendel.transacciones;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendel.transacciones.utils.DataTransactionJSON;
import com.mendel.transacciones.utils.VariablesForTest;
import com.mendel.transactions.utils.ValidateTransaction;

@EnableWebMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {
		com.mendel.transactions.TransactionsApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestTransactionsApplication {

	private ObjectMapper objectMapper;
	private RestTemplate restTemplate;
	private String urlPostTransaction, urlPostTransactionAutoreferencce, urlGetTypes, urlGetSum;

	@LocalServerPort
	int randomServerPort;

	@BeforeEach
	void setUp() {
		restTemplate = new RestTemplate();
		urlPostTransaction = "http://localhost:" + randomServerPort + "/transactions/"
				+ VariablesForTest.ID_TRANSACTION_TO_INSERT;
		urlPostTransactionAutoreferencce = "http://localhost:" + randomServerPort + "/transactions/"
				+ VariablesForTest.ID_TRANSACTION_TO_INSERT_AUTOREFERENCE;
		urlGetTypes = "http://localhost:" + randomServerPort + "/transactions/types/"
				+ VariablesForTest.TYPE;
		urlGetSum = "http://localhost:" + randomServerPort + "/transactions/sum/"
				+ VariablesForTest.ID_TRANSACTION_FOR_SUM;
		objectMapper = new ObjectMapper();
	}

	@Test
	@Order(1)
	@DisplayName("Post Transaction success")
	void tesInsertTransaction()
			throws JsonMappingException, JsonProcessingException, RestClientException, JSONException {
		String personResultAsJsonStr = restTemplate.postForObject(urlPostTransaction, getRequestCreateTransaction(),
				String.class);
		JsonNode root = objectMapper.readTree(personResultAsJsonStr);
		assertEquals(VariablesForTest.SUCCESS_PERSIST, root.get("Message").asText());
		;
	}

	@Test
	@Order(2)
	@DisplayName("Post Transaction fail, autoreference")
	void tesInsertTransactionAutoreference()
			throws JsonMappingException, JsonProcessingException, RestClientException, JSONException {

		try {
			restTemplate.postForObject(urlPostTransactionAutoreferencce,
					getRequestCreateTransactionAutoreference(), String.class);

		} catch (Exception ex) {
			assertTrue(ex.getMessage().contains(ValidateTransaction.ID_PARENT_SELF_REFERENCE));
		}

	}
	@Test
	@Order(5)
	@DisplayName("Get Types")
	void testGetTypesOfTransactions()
			throws JsonMappingException, JsonProcessingException, RestClientException, JSONException {
		
		try {
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlGetTypes, String.class);
			assertEquals(OK, responseEntity.getStatusCode());
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	@Test
	@Order(6)
	@DisplayName("Get Sum")
	void testGetSumOfTransactions()
			throws JsonMappingException, JsonProcessingException, RestClientException, JSONException {
		
		try {
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(urlGetSum, String.class);
			assertEquals(OK, responseEntity.getStatusCode());
			responseEntity.getBody().contains("13.0");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}

	private HttpEntity<String> getRequestCreateTransaction() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(DataTransactionJSON.getJSONObjectTransaction().toString(), headers);
	}

	private HttpEntity<String> getRequestCreateTransactionAutoreference() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(DataTransactionJSON.getJSONObjectTransactionAutoreference().toString(), headers);
	}

}
