package com.sush.service;

import java.util.List;

import com.sush.dto.EmployeeDTO;
import com.sush.exception.EmployeeException;

public interface EmployeeService {

	public String addEmployee(EmployeeDTO employeeDto) throws EmployeeException;
	public String editEmployee(Integer id, EmployeeDTO employeeDto) throws EmployeeException;
	public String deleteEmployee(Integer id) throws EmployeeException;
	public List<EmployeeDTO> getEmployee() throws EmployeeException;
	public EmployeeDTO getEmployeeById(Integer id) throws EmployeeException;
}
