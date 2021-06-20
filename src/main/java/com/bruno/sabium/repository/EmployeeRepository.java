package com.bruno.sabium.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bruno.sabium.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Transactional
	//@Query("SELECT e FROM Employee e join e.projects p WHERE p.id = :idProject"
	//		+ "group by e having count(p) > 1 ")
	//public List<Employee> findByIdProject(@Param("idProject") Long idProject);
	@Query("SELECT e FROM Employee e join e.projects p group by e having count(p) > 1 ")
	public List<Employee> findByMoreOneProject();
	
}
