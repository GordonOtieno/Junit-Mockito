package com.gordon.H2WebApplication.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gordon.H2WebApplication.entity.Department;
import com.gordon.H2WebApplication.repository.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp()  {
		Department department = Department.builder()
					.departmentName("COM")
					.departmentAddress("COM0025")
					.departmentCode("COOM/0025/14")
					.departmentId(1L)
					.build();
		Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("COM"))
		      .thenReturn(department);
	}

	@Test
	@DisplayName("Get Data based on Valid Department Name")

	public void whenValidDepartmentName_thenDepartmentShouldFound() {
		String departmentName="COM";
		Department foundDepartment = departmentService.fetchDepartmentByName(departmentName);
		
		assertEquals(departmentName, foundDepartment.getDepartmentName());
	}



}
