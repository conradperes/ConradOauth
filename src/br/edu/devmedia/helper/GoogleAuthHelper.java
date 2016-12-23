package br.edu.devmedia.helper;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public class GoogleAuthHelper {

	private static final String CLIENTE_ID = "751198304437-1aphi8uhqq8iobv78ujclo0daioo3kdh.apps.googleusercontent.com";
	
	private static final String SENHA_ID = "hbS6YaKirHDBFu5CCxITdFqW";
	
	private static final String URL_CALLBACK = "http://localhost:8080/devmedia-oauth/google_login";
	
	private static final String ESCOPOS_STR = "https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email;";
	
	private static final Collection<String> ESCOPO = Arrays.asList(ESCOPOS_STR.split(";"));
	
	private static final String INFO_USUARIO = "https://www.googleapis.com/oauth2/v1/userinfo";
	
	private static final JsonFactory FACTORY_JSON = new JacksonFactory();
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	private String token;
	
	private final GoogleAuthorizationCodeFlow fluxo;
	
	public GoogleAuthHelper() {
		fluxo = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, FACTORY_JSON, CLIENTE_ID, SENHA_ID, ESCOPO).build();
		
		gerarEstadoToken();
	}
	
	public String construirURLLogin () {
		GoogleAuthorizationCodeRequestUrl url = fluxo.newAuthorizationUrl();
		
		return url.setRedirectUri(URL_CALLBACK).setState(token).build();
	}
	
	private void gerarEstadoToken() {
		SecureRandom sc = new SecureRandom();
		
		token = "google;" + sc.nextInt();
	}

	public String getToken() {
		return token;
	}
	
	public String getInfoUsuarioJson(final String codigoAuth) throws IOException {
		final GoogleTokenResponse response = fluxo.newTokenRequest(codigoAuth).setRedirectUri(URL_CALLBACK).execute();
		Credential credencial = fluxo.createAndStoreCredential(response, null);
		HttpRequestFactory requestFact = HTTP_TRANSPORT.createRequestFactory(credencial);
		
		final GenericUrl url = new GenericUrl(INFO_USUARIO);
		HttpRequest request = requestFact.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		
		final String jsonId = request.execute().parseAsString();
		
		return jsonId;
	}
	
}
