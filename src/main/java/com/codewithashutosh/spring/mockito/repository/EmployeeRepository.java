package com.codewithashutosh.spring.mockito.repository;


import com.codewithashutosh.spring.mockito.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
