package com.sush.service;

import java.util.List;

import com.sush.dto.EmployeeDTO;
import com.sush.exception.EmployeeException;

public interface EmployeeService {

	public String addEmployee(EmployeeDTO employeeDto) throws EmployeeException;
	public String editEmployee(Integer employeeId, EmployeeDTO employeeDto) throws EmployeeException;
	public String deleteEmployee(Integer employeeId) throws EmployeeException;
	public List<EmployeeDTO> getEmployee() throws EmployeeException;
}
