package com.gordon.H2WebApplication.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; 

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import com.gordon.H2WebApplication.entity.Department;
import com.gordon.H2WebApplication.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private Department department;

	@BeforeEach
	void setUp() {
		   department = Department.builder()
				.departmentId(1L)
				.departmentAddress("kisumu")
				.departmentCode("IT-06")
				.departmentName("IT")
				.build();
		
	}

	@Test
	void SaveDepartment() throws Exception {
		Department inputDepartment = Department.builder()
				.departmentName("IT")
				.departmentAddress("kisumu")
				.departmentCode("IT-06")
				.build();
		Mockito.when(departmentService.saveDepartment(inputDepartment))
		.thenReturn(department);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/departments")

		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\n" +
		                "\t\"departmentName\":\"IT\",\n" +
		                "\t\"departmentAddress\":\"kisumu\",\n" +
		                "\t\"departmentCode\":\"IT-06\"\n" +
		                "}"))
		                .andExpect(MockMvcResultMatchers.status().isOk());
	}

	 @Test
	    void fetchDepartmentById() throws Exception {
	        Mockito.when(departmentService.findDepartment(1L))
	                .thenReturn(department);

	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/departments/1")
	        .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(jsonPath("$.departmentName").
	                value(department.getDepartmentName()));
	    }

}
