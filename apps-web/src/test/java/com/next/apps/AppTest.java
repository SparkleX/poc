package com.next.apps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppTest
{
//	@Autowired
//	private WebTestClient webClient;
	
	@Autowired
	private TestRestTemplate restTemplate;
	/*@Test
	public void exampleTest() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World");
	}*/
	
	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		System.out.println(body);
		//assertThat(body).isEqualTo("Hello World");
	}
}
