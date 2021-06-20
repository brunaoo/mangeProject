package com.bruno.manageProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bruno.sabium.ManageProjectApplication;
import com.bruno.sabium.entity.Employee;
import com.bruno.sabium.entity.Project;
import com.bruno.sabium.service.EmployeeService;
import com.bruno.sabium.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManageProjectApplication.class)
public class ManageProjectApplicationTests {

		
	@Test
	public void contextLoads() {
	}

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@Test
	public void saveProject() {
		
		Project project = new Project("java Spring boot");
		project = (Project) projectService.save(project);
		
		assertEquals("save project",project, projectService.findOne(project.getId()));
	}
	
	@Test
	public void saveEmployee() {

		Employee employee = new Employee("BRUNO ALVES DE OLIVEIRA", new BigDecimal(1000));
		employee = employeeService.save(employee);
		assertEquals("save employee", employee, employeeService.findOne(employee.getId()));
	}

	
	@Test
	public void addEmployeeProject() {
		
		Employee employee = new Employee("Tiburcio", new BigDecimal(500));
		employee = employeeService.save(employee);
		
		Project project = new Project("php");
		project.getEmployee().add(employee);
		project = (Project) projectService.save(project);
		
		boolean contains = projectService.findOne(project.getId()).getEmployee().contains(employee);
		
		assertTrue("single employee in project",contains);
	}
	
	
	@Test
	public void addTwoProjectEmployee() {
		
		Employee employee = new Employee("smarf", new BigDecimal(1500));
		employee = employeeService.save(employee);
				
		Project projectC = new Project("rest web app");
		projectC.getEmployee().add(employee);
		projectC = (Project) projectService.save(projectC);
		
		Project projectNodeJs = new Project("NojeJs");
		projectNodeJs.getEmployee().add(employee);
		projectNodeJs = (Project) projectService.save(projectNodeJs);
		
		
		assertTrue("addTwoProjectEmployee", employeeService.findOne(employee.getId()).getProjects().size()==2);
		
	}
	
	@Test(expected  = IllegalArgumentException.class)
	public void addThreeProjectEmployee() {
		
		Employee employee = new Employee("Goku", new BigDecimal(1500));
		employee = employeeService.save(employee);
				
		Project projectC = new Project("c++");
		projectC.getEmployee().add(employee);
		projectC = (Project) projectService.save(projectC);
		
		Project projectNodeJs = new Project("NojeJs web app");
		projectNodeJs.getEmployee().add(employee);
		projectNodeJs = (Project) projectService.save(projectNodeJs);
		
		Project projectPhp = new Project("PHP");
		projectPhp.getEmployee().add(employee);
		projectPhp = (Project) projectService.save(projectPhp);
				
	}
	
	@Test
	public void findEmployeeMoreOneProject() {
		List<Employee> list = employeeService.findByMoreThanOneProject();
		for (Employee employee : list) {
			if (employee.getProjects().size() <= 1) {
				fail("error find employee several project");
			}
		}
		assertTrue("find employee several project", true);
	}
	
}
