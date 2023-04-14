package com.sush.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sush.entity.Employee;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Integer>{
	
}
