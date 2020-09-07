package org.acardenosa.service;

import java.util.List;

import org.acardenosa.model.Employee;
import org.acardenosa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository ;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
		
	}

}
