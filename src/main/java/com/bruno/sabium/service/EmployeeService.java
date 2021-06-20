package com.bruno.sabium.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.sabium.entity.Employee;
import com.bruno.sabium.repository.EmployeeRepository;


@Service
public class EmployeeService implements CrudService<Employee>{

	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Transactional
	@Override
	public List<Employee> findAll() {
		List<Employee> list = new LinkedList<>();
		employeeRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	@Transactional
	public Employee findOne(long employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		if(employee!= null) {
			Hibernate.initialize(employee.getProjects());			
		}
		return employee;
	}
	
	@Transactional
	public List<Employee> findByMoreThanOneProject(){
		List<Employee> list = new LinkedList<>();
		employeeRepository.findByMoreOneProject().forEach(e -> list.add(e));
		return list;
	}

	@Transactional
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
		
	}

	@Transactional
	@Override
	public void delete(long employeeId) {
		employeeRepository.delete(employeeId);
		
	}

}
