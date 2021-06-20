package com.bruno.sabium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.sabium.entity.Employee;
import com.bruno.sabium.entity.Project;
import com.bruno.sabium.service.EmployeeService;
import com.bruno.sabium.service.ProjectService;

@RestController
public class EmployeeController {
	

	@Autowired
    private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/employee/moreOneProject", method = RequestMethod.GET)
    public Iterable<Employee> moreThanOneProject() {
        return employeeService.findByMoreThanOneProject();
    }
	
	@RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		if(employee!=null) {
			employee = employeeService.save(employee);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        
    }
	
	@RequestMapping(value="/employee/addProject/{id}", method=RequestMethod.POST)
	public ResponseEntity<Employee> addProjectToEmployee(@PathVariable("id") long idEmployee,
			@RequestBody Project project){
		Employee employee = employeeService.findOne(idEmployee);
		if(project!= null && employee != null) {
			
			project.getEmployee().add(employee);
			projectService.save(project);
			
			employee.getProjects().add(project);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@RequestMapping(value = "/employee/delete", method = RequestMethod.GET)
    public @ResponseBody String delete(@RequestParam("id") long idEmployee) {
		employeeService.delete(idEmployee);
        return "O Funcionario foi deletado...";
    }
	
	@RequestMapping(value = "/employee/all", method = RequestMethod.GET)
	public @ResponseBody List<Employee> allEmployee() {
		return employeeService.findAll();
		
	}
	
	
}
