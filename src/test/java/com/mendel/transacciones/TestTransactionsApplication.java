package com.mendel.transacciones;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendel.transacciones.utils.DataTransactionJSON;

@EnableWebMvc
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {com.mendel.transactions.TransactionsApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestTransactionsApplication {
	
	private ObjectMapper objectMapper;
	private RestTemplate restTemplate;
	private Long idTransactionToInsert = 1L;
	private String urlPostTransaction;
	@LocalServerPort
	int randomServerPort;

	@BeforeEach
	void setUp() {
		restTemplate = new RestTemplate();
		urlPostTransaction = "http://localhost:" + randomServerPort + "/transactions/" + idTransactionToInsert;
			objectMapper = new ObjectMapper();
	}

	@Test
	@Order(1)
	@DisplayName("Post Transaction")
	void tesInsertTransaction() throws JsonMappingException, JsonProcessingException, RestClientException, JSONException {
		String personResultAsJsonStr = restTemplate.postForObject(urlPostTransaction, getRequestCreateTransaction(), String.class);
		JsonNode root = objectMapper.readTree(personResultAsJsonStr);
//		assertAll(() -> assertEquals("123", this.getValueFromWritingSearching("/id:" + idToDelete, "unitId"),
//				() -> "El escrito " + idToDelete + " no contiene la unidad esperada.")
//		);
	}
	private HttpEntity<String> getRequestCreateTransaction() throws JSONException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<String>(DataTransactionJSON.getJSONObjectTransaction().toString(), headers);
		
	}
	

}
