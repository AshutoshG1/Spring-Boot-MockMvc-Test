package com.codewithashutosh.spring.mockito;

import com.codewithashutosh.spring.mockito.entity.Employee;
import com.codewithashutosh.spring.mockito.entity.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringbootMockitoTestApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	private ObjectMapper om = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("Ashutosh");
		employee.setDept("IT");

		String jsonRequest = om.writeValueAsString(employee);

		MvcResult result = mockMvc.perform(post("/EmployeeService/addEmployee")
						.content(jsonRequest)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		assertTrue(response.isStatus());
	}

	@Test
	public void getEmployeesTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/EmployeeService/getEmployees")
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		assertTrue(response.isStatus());
	}
}
