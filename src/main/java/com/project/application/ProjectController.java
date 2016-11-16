package com.project.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apptium.project.types.CategoryResponse;
import com.apptium.project.types.ProjectInformationResponse;
import com.apptium.project.types.ProjectRequest;

@RestController
@RequestMapping(value = { "", "/project" })
public class ProjectController {

	@Autowired
	ProjectManagementServiceImpl projectManagementServiceImpl;

	@RequestMapping(value = "/getProjectInformation/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProjectInformationResponse getProjectInformation(@PathVariable("projectId") Integer projectId) {
		return projectManagementServiceImpl.getProjectInformation(projectId);
	}

	@RequestMapping(value = "/getProjectCategories",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryResponse> getCategoryInfo(){
		return projectManagementServiceImpl.getAllCategories();
	}

	@GetMapping("/getprojects")
	public List<ProjectInformationResponse> readAllProject() {
		return projectManagementServiceImpl.getAllProjects();
	}

	@PostMapping("/create")
	public ProjectInformationResponse addProject(@RequestBody ProjectRequest projectInfo) {
		ProjectInformationResponse projectResponse = projectManagementServiceImpl.createProject(projectInfo);
		return projectResponse;
	}

	@DeleteMapping("/delete")
	public void deleteProject(@RequestBody ProjectRequest projectInfo) {
		projectManagementServiceImpl.deleteProject(projectInfo.getProjectIdNew());
	}

	@PutMapping("/updateproject")
	public ProjectInformationResponse updateProject(@RequestBody ProjectRequest projectInfo) {
		 ProjectInformationResponse projectResponse = projectManagementServiceImpl.updateProject(projectInfo,projectInfo.getProjectIdNew());
		 return projectResponse;
	}
}
