package br.com.tokio.marine.rest;


import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class CorpUtilTest {


	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void condicaoExclusivaAtualizarAvisoSinistro() {
		
		ResponseEntity<T> response = null;
		String messageError = "";

		Long nrItseg = 25539891L;
		Long cdAvisoSinis = 1L;
		String dataOcorrencia = "30/02/2017";

		StringBuffer url = new StringBuffer(
				"http://localhost:8086/CorpUtil/rest/condicaoExclusiva/atualizarAvisoSinistro?");
		url.append("nrItseg=" + nrItseg);
		url.append("&cdAvisoSinis=" + cdAvisoSinis);
		url.append("&dataOcorrencia=" + dataOcorrencia);
		url.append("&municipioOcorrencia=");
		url.append("&ufOcorrencia=");

		System.out.println("Inicio da chamada " + url.toString());
		try {
			response = (ResponseEntity<T>) new RestTemplate().getForEntity(url.toString(), String.class);
		} catch (HttpClientErrorException e) {
			messageError = e.getResponseBodyAsString();
		} catch (HttpServerErrorException e) {
			messageError = e.getResponseBodyAsString();
		}

		if (messageError.isEmpty()) {
			System.out.println("response " + response.getBody());
		} else {
			System.out.println("response " + messageError);
		}
	}

}