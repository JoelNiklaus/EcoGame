package ch.joelniklaus.ecogame.webapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/spring*.xml" })
public class IndexControllerIntegrationTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testSecurityError() throws Exception {
		this.mockMvc.perform(get("/security-error")).andExpect(status().isFound())
				.andExpect(redirectedUrl("/")).andExpect(flash().attributeExists("page_error"));
	}

	@Test
	public void testPath() throws Exception {
		this.mockMvc.perform(get("/ecogame/")).andExpect(status().isOk())
		.andExpect(forwardedUrl("/ecogame/game/start"));
	}

	@Test
	public void testNotFound() throws Exception {
		this.mockMvc.perform(get("/ecogame/notFound")).andExpect(status().isOk())
		.andExpect(view().name("/ecogame/notFound"));
	}
	
	@Test
	public void testLogin() throws Exception {
		this.mockMvc
		.perform(
				get("/ecogame/login").param("j_username", "test%40test.ch").param(
						"j_password", "test")).andExpect(status().isOk())
						.andExpect(forwardedUrl("/ecogame/game/start"))
				.andExpect(view().name("game/start"));
	}
	
}
