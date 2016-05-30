package com.readymade;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class postTest {

	@Test
	public void test() {
		String url = "http://localhost:8080/resume/data/skills";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, "java;js;", String.class);
	}

}
