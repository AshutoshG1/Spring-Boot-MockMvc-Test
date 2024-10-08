package com.codewithashutosh.spring.mockito.controller;

import com.codewithashutosh.spring.mockito.entity.Employee;
import com.codewithashutosh.spring.mockito.entity.Response;
import com.codewithashutosh.spring.mockito.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/EmployeeService")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;

	@PostMapping("/addEmployee")
	public Response addEmployee(@RequestBody Employee employee) {
		repository.save(employee);
		return new Response(employee.getId() + " inserted", Boolean.TRUE);
	}

	@GetMapping("/getEmployees")
	public Response getAllEmployees() {
		List<Employee> employees = repository.findAll();
		return new Response("record counts : " + employees.size(), Boolean.TRUE);
	}
}
