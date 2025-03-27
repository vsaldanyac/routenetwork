package com.caravelo.business.service.impl;

import com.caravelo.business.exception.EndpointNotAvailableException;
import com.caravelo.business.exception.ParsingExternalDataException;
import com.caravelo.business.model.external.FlightData;
import com.caravelo.business.service.BackupRoutesExtApiService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BackupRoutesExtApiServiceImpl implements BackupRoutesExtApiService {

	private static final Logger LOGGER = LogManager.getLogger(BackupRoutesExtApiServiceImpl.class);

	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	@Value("${caravelo.api.backup.service.url:https://script.google.com/macros/s/AKfycbxRNENzlOcpCkzuPQvurSoqI0MTk1qgyOuBqdVyKF-B80BjKes-R0I4pbLnqCGBYaBu3g/exec/routes_api_key_iata_W6}")
	private String backupApiService;

	private final RestTemplate restTemplate;

	public BackupRoutesExtApiServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	private void getFlightsFromGoogleScript() {
		try {
			// Carga las credenciales de OAuth 2.0 desde el archivo client_secret.json
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(BackupRoutesExtApiServiceImpl.class.getResourceAsStream("/client_secret.json")));

			// Configura el flujo de autorización de OAuth 2.0
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				  new NetHttpTransport(), JSON_FACTORY, clientSecrets, Collections.singleton("https://www.googleapis.com/auth/script.execute"))
				  .setAccessType("offline")
				  .build();

			// Realiza la autorización interactiva con el usuario
			LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
			Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

			// Crea un RestTemplate y configura el encabezado de autorización
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://script.google.com/macros/s/AKfycbxRNENzlOcpCkzuPQvurSoqI0MTk1qgyOuBqdVyKF-B80BjKes-R0I4pbLnqCGBYaBu3g/exec/routes_api_key_iata_W6";
			String response = restTemplate.getForObject(url, String.class, credential.getAccessToken());

		} catch (Exception e) {
			LOGGER.error("ERROR retrieving data from backup endpoint", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FlightData> getRoutes() throws EndpointNotAvailableException, ParsingExternalDataException {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			Map<String, String> urlParams = new HashMap<>();
			HttpEntity<String> entity = new HttpEntity<>(headers);
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(backupApiService);
			ResponseEntity<FlightData[]> response = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, entity, FlightData[].class);
			return response.getBody() != null ? Arrays.stream(response.getBody()).collect(Collectors.toList()) : null;
		} catch (HttpClientErrorException e) {
			LOGGER.error("ERROR retrieving data from endpoint", e);
			throw new EndpointNotAvailableException("Endpoint not available");
		} catch (RestClientException e) {
			LOGGER.error("ERROR retrieving data from endpoint", e);
			throw new ParsingExternalDataException("Error parsing data");
		} catch (Exception e) {
			LOGGER.error("ERROR retrieving data from endpoint", e);
			throw e;
		}
	}
}
