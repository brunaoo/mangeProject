package com.bruno.sabium.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@SequenceGenerator(name = "seq_project", sequenceName = "seq_project")
@Table(name = "project", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@Audited
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
	private Long id;
	@NotEmpty(message = "Nome no projeto é obrigátorio")
	@Size(min = 2, max = 300, message = "Nome do projeto deve ter entre 2 a 300 caracteres")
	private String name;
	@ManyToMany
	@JoinTable(name = "emp_proj", joinColumns = { @JoinColumn(name = "proj_id") }, inverseJoinColumns = {
			@JoinColumn(name = "emp_id") })
	@JsonBackReference
	private Set<Employee> employee;
	
	public Project() {
		
	}

	public Project(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployee() {
		if(employee== null) {
			employee = new LinkedHashSet<>();
		}
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
