package com.sush.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sush.dto.EmployeeDTO;
import com.sush.entity.Employee;
import com.sush.exception.EmployeeException;
import com.sush.repositary.EmployeeRepositary;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepositary employeeRepositary;
	
	@Autowired
	Environment environment;

	@Override
	public String addEmployee(EmployeeDTO employeeDto) throws EmployeeException {

		Employee employee = new Employee();
		
		employee.setId(employeeDto.getId());
		employee.setName(employeeDto.getName());
		employee.setGender(employeeDto.getGender());
		employee.setAge(employeeDto.getAge());
		employee.setState(employeeDto.getState());
		
		employeeRepositary.save(employee);
		
		String str = environment.getProperty("Service.ADD");
		return str;
	}

	@Override
	public String editEmployee(Integer id, EmployeeDTO employeeDto) throws EmployeeException {
		
		Optional<Employee> optional = employeeRepositary.findById(id);
		
		if(optional.isEmpty()) {
			throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
		}
		
		Employee employee = optional.get();
		
		employee.setName(employeeDto.getName());
		employee.setGender(employeeDto.getGender());
		employee.setAge(employeeDto.getAge());
		employee.setState(employeeDto.getState());
		
		employeeRepositary.saveAndFlush(employee);
		
		String str = environment.getProperty("Service.EDIT");
		return str;
	}

	@Override
	public String deleteEmployee(Integer id) throws EmployeeException {
		
		Optional<Employee> optional = employeeRepositary.findById(id);
		
		if(optional.isEmpty()) {
			throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
		}
		
		employeeRepositary.deleteById(id);
		
		String str = environment.getProperty("Service.DELETE");
		return str;
	}

	@Override
	public List<EmployeeDTO> getEmployee() throws EmployeeException {
		List<Employee> list = employeeRepositary.findAll();
		
		if(list.isEmpty()) {
			throw new EmployeeException("Service.EMPLOYEES_NOT_FOUND");
		}
		
		List<EmployeeDTO> employeesList = new ArrayList<EmployeeDTO>();
		
		for(Employee employee : list) {
			EmployeeDTO employeeDto = new EmployeeDTO();
			
			employeeDto.setId(employee.getId());
			employeeDto.setName(employee.getName());
			employeeDto.setGender(employee.getGender());
			employeeDto.setAge(employee.getAge());
			employeeDto.setState(employee.getState());
			
			employeesList.add(employeeDto);
		}
		return employeesList;
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) throws EmployeeException {
		
		Optional<Employee> optional = employeeRepositary.findById(id);
		
		if(optional.isEmpty()) {
			throw new EmployeeException("Service.EMPLOYEE_NOT_FOUND");
		}
		
		Employee employee = optional.get();
		
		EmployeeDTO employeeDto = new EmployeeDTO();
		
		employeeDto.setId(employee.getId());
		employeeDto.setName(employee.getName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setAge(employee.getAge());
		employeeDto.setState(employee.getState());

		return employeeDto;
	}
}
