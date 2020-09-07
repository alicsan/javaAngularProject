package org.acardenosa.controller;

import java.util.List;

import org.acardenosa.exception.ResourceNotFoundException;
import org.acardenosa.model.Employee;
import org.acardenosa.repository.EmployeeRepository;
import org.acardenosa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")	
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
//	@GetMapping("/employees")
//	public List<Employee> getAllEmployees(){
//		return employeeRepository.findAll();
//	}
//	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> get(){
		List<Employee> employee = employeeService.findAll();
		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
	}
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId)
			throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees/{id}")
	public Employee createEmployee (@Validated @RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
}
