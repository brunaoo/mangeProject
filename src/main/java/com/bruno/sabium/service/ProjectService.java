package com.bruno.sabium.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.sabium.entity.Employee;
import com.bruno.sabium.entity.Project;
import com.bruno.sabium.repository.EmployeeRepository;
import com.bruno.sabium.repository.ProjectRepository;

@Service
public class ProjectService implements CrudService<Project>{

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	public EmployeeRepository employeeRepository;
	
	
	@Override
	@Transactional
	public List<Project> findAll() {
		List<Project> list = new LinkedList<>();
		projectRepository.findAll().forEach(p -> list.add(p));
		return list;
	}

	@Override
	@Transactional
	public Project findOne(long idProject) {
		Project project = projectRepository.findOne(idProject);
		Hibernate.initialize(project.getEmployee());
		return project;
	}

	
	@Override
	@Transactional
	public <T> Object save(Project project) {
		for(Employee emp : project.getEmployee()) {
			if(employeeRepository.findOne(emp.getId()).getProjects().size() ==2) {
				throw new IllegalArgumentException("Funcionario exedeu limite de participações em projetos.");
			}
		}
		return projectRepository.save(project);
	}

	@Override
	@Transactional
	public void delete(long projectId) {
		projectRepository.delete(projectId);
		
	}


}
