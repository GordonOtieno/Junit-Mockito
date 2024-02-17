package com.gordon.H2WebApplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.gordon.H2WebApplication.entity.Department;

@DataJpaTest
class DepartmentRepositoryTest {
	
	@Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;


	@BeforeEach
	void setUp() {
		 Department department =
	                Department.builder()
	                .departmentName("Mechanical Engineering")
	                .departmentCode("ME - 011")
	                .departmentAddress("Nairobi")
	                .build();

	        entityManager.persist(department);
	}

	@Test
	@DisplayName("Get data based on the ID privided")
	public void whenFindById_thenReturnDepartment() {
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "Mechanical Engineering");
    }

}
