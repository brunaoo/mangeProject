package com.bruno.sabium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.sabium.entity.Project;
import com.bruno.sabium.service.ProjectService;

@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
    public Iterable<Project> list() {
        return projectService.findAll();
    }

}
