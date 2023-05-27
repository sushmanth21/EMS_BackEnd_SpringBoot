package com.sush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sush.dto.EmployeeDTO;
import com.sush.exception.EmployeeException;
import com.sush.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/AddEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDto){
		
		String msg;
		try {
			msg = employeeService.addEmployee(employeeDto);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		}
		catch (EmployeeException e){
			String str = environment.getProperty("Api.SOMETHING_ERROR");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, str);
		}
	}
	
	@PutMapping(value = "/EditEmployee/{id}")
	public ResponseEntity<String> editEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDto){
		
		String msg;
		try {
			msg = employeeService.editEmployee(id, employeeDto);
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		}
		catch (EmployeeException e){
			String str = environment.getProperty("Api.EMPLOYEE_NOT_FOUND");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, str);
		}
	}
	
	@DeleteMapping(value = "/DeleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		
		String msg;
		try {
			msg = employeeService.deleteEmployee(id);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		catch (EmployeeException e){
			String str = environment.getProperty("Api.EMPLOYEE_NOT_FOUND");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, str);
		}
	}
	
	@GetMapping(value = "/GetEmployees")
	public ResponseEntity<List<EmployeeDTO>> getEmployees(){
		
		List<EmployeeDTO> list;
		try {
			list = employeeService.getEmployee();
			return new ResponseEntity<List<EmployeeDTO>> (list, HttpStatus.ACCEPTED);
		}
		catch (EmployeeException e){
			String str = environment.getProperty("Api.EMPLOYEES_NOT_FOUND");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, str);
		}
	}
	
	@GetMapping(value = "/GetEmployee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeesById(@PathVariable Integer id){
		
		EmployeeDTO msg;
		try {
			msg = employeeService.getEmployeeById(id);
			return new ResponseEntity<EmployeeDTO>(msg, HttpStatus.OK);
		}
		catch (EmployeeException e){
			String str = environment.getProperty("Api.EMPLOYEE_NOT_FOUND");
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, str);
		}
	}
}
