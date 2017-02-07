package com.davtyan.materialweather.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WebClientTests {
	private WebClient webClient;
	private String url;

	@Before
	public void beforeEach() {
		webClient = new WebClient();
		url = "https://edgardavtyan.github.io/for-web-unit-tests/";
	}

	@Test
	public void get_url_bytes_given_correct_url() {
		assertThat(webClient.getBytes(url))
				.isEqualTo(new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 10});
	}

	@Test
	public void get_url_string_given_correct_url() {
		assertThat(webClient.getString(url)).isEqualTo("Hello World\n");
	}

	@Test
	public void throw_runtime_exception_given_incorrect_url() {
		assertThatThrownBy(() -> webClient.getBytes("incorrect")).isInstanceOf(RuntimeException.class);
		assertThatThrownBy(() -> webClient.getString("incorrect")).isInstanceOf(RuntimeException.class);
	}
}
