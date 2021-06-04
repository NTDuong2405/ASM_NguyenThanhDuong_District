package com.staxrt.tutorial;

import com.staxrt.tutorial.model.Street;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TutorialApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllStreets() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/streets",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetStreetById() {
		Street street = restTemplate.getForObject(getRootUrl() + "/streets/1", Street.class);
		System.out.println(street.getStreetName());
		Assert.assertNotNull(street);
	}

	@Test
	public void testCreateStreet() {
		Street street = new Street();
		street.setStreetName("Hoang Hoa Tham");
		street.setDescription("Hoang Hoa Tham");
		street.setStatus("Đang sử dụng");
		street.setCreatedBy("admin");
		street.setUpdatedBy("admin");

		ResponseEntity<Street> postResponse = restTemplate.postForEntity(getRootUrl() + "/streets", street, Street.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

}
