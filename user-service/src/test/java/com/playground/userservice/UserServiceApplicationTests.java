package com.playground.userservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.userservice.dto.AuthResponse;
import com.playground.userservice.dto.CreateProfileRequest;
import com.playground.userservice.dto.LoginRequest;
import com.playground.userservice.dto.UpdateProfileRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	private static String jwtToken;
    
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

	@Test
	@Order(1)
	void shouldReturn401WhenNoToken() throws Exception {
		mockMvc.perform(get("/api/v1/profile"))
				.andExpect(status().isForbidden());
	}

	@Test
	@Order(2)
	void shouldLoginAndGetToken() throws Exception {
		LoginRequest loginRequest = new LoginRequest("user", "password");
		
		String response = mockMvc.perform(post("/api/v1/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists())
				.andReturn().getResponse().getContentAsString();

		AuthResponse authResponse = objectMapper.readValue(response, AuthResponse.class);
		jwtToken = authResponse.getToken();
	}

	@Test
	@Order(3)
	void shouldCreateProfileWithToken() throws Exception {
		CreateProfileRequest request = new CreateProfileRequest();
		request.setEmail("tanvir.hossain@example.com");
		request.setFirstName("Tanvir");
		request.setLastName("Hossain");
		request.setBirthDate(LocalDate.of(1995, 1, 1));
		request.setPhoneNumber("603424567");
		request.setStreet("Calle Real 123");
		request.setCity("Barcelona");
		request.setCountry("Spain");
		request.setPostalCode("08975");

		mockMvc.perform(post("/api/v1/profile")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.email").value("tanvir.hossain@example.com"));
	}

	@Test
	@Order(4)
	void shouldGetProfileWithToken() throws Exception {
		mockMvc.perform(get("/api/v1/profile")
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value("tanvir.hossain@example.com"));
	}

	@Test
	@Order(5)
	void shouldUpdateProfileWithToken() throws Exception {
		UpdateProfileRequest request = new UpdateProfileRequest();
		request.setCity("Madrid");

		mockMvc.perform(put("/api/v1/profile")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.city").value("Madrid"));
	}
    
    @Test
    @Order(6)
    void shouldReturn400WhenCreateProfileInvalid() throws Exception {
        CreateProfileRequest request = new CreateProfileRequest();

        mockMvc.perform(post("/api/v1/profile")
                .header("Authorization", "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

	@Test
	@Order(8)
	void shouldReturn409WhenCreateExistingProfile() throws Exception {
		CreateProfileRequest request = new CreateProfileRequest();
		request.setEmail("tanvir.hossain@example.com");
		request.setFirstName("Tanvir");
		request.setLastName("Hossain");
        
		mockMvc.perform(post("/api/v1/profile")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isConflict());
	}
}
