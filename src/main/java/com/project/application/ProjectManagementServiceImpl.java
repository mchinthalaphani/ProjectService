package com.project.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apptium.project.types.CategoryResponse;
import com.apptium.project.types.ProjectInformationResponse;
import com.apptium.project.types.ProjectRequest;
import com.resourcemanagement.domain.Project;
import com.resourcemanagement.domain.UserStory;
import com.resourcemanagement.domain.UserStoryCategory;
import com.resourcemanagement.repository.CategoryRepository;
import com.resourcemanagement.repository.ProjectRepository;;

@Component
public class ProjectManagementServiceImpl {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	/**
	 * 
	 * @return - List of Projects
	 */
	public List<ProjectInformationResponse> getAllProjects() {
		List<Project> projectList = (List<Project>) projectRepo.findAll();
		List<ProjectInformationResponse> projectInfoList = new ArrayList<ProjectInformationResponse>(
				projectList.size());
		for (Project p : projectList)
			projectInfoList.add(createProjectResponse(p));

		return projectInfoList;
	}

	public ProjectInformationResponse getProjectInformation(Integer id) {
		Project project = projectRepo.findOne(id);
		ProjectInformationResponse projectResponse = createProjectResponse(project);
		return projectResponse;
	}

	/**
	 * 
	 * @return - All User Story Categories
	 */
	public List<CategoryResponse> getAllCategories() {
		List<UserStoryCategory> categoryList = (List<UserStoryCategory>) categoryRepo.findAll();
		List<CategoryResponse> categoryResponseList = new ArrayList<CategoryResponse>(categoryList.size());
		for (UserStoryCategory c : categoryList)
			categoryResponseList.add(createCategoryResponse(c));

		return categoryResponseList;
	}

	/**
	 * 
	 * @param pi-
	 *            ProjectInfo Custom object
	 * 
	 * @return - Project Entity Object
	 */
	public Project createNewProject(ProjectRequest pi) {

		Project project = new Project();
		if (pi.getProjectCompanyNameNew() != null)
			project.setProjectClientName((pi.getProjectCompanyNameNew()));
		if (pi.getProjectEndDateNew() != null)
			project.setProjectEndDate(pi.getProjectEndDateNew());
		if (pi.getProjectNameNew() != null)
			project.setProjectName(pi.getProjectNameNew());
		if (pi.getProjectStartDateNew() != null)
			project.setProjectStartDate(pi.getProjectStartDateNew());
		if (pi.getProjectStatusNew() != null)
			project.setProjectStatus(pi.getProjectStatusNew());
		if (pi.getProjectDescription() != null)
			project.setProjectDescription(pi.getProjectDescription());
		if (pi.getUserstories() != null)
			project.setUserstories(pi.getUserstories());
		if (pi.getUserstories() != null) {
			for (UserStory us : pi.getUserstories())
				us.setProjects(project);
		}
		return project;

	}

	/**
	 * 
	 * @param pi-
	 *            ProjectInfo Custom Request Body
	 * 
	 * @return - A new Project
	 */
	public ProjectInformationResponse createProject(ProjectRequest pi) {
		Project project = createNewProject(pi);
		projectRepo.save(project);
		ProjectInformationResponse projectResponse = createProjectResponse(project);
		return projectResponse;
	}

	/**
	 * 
	 * @param id-
	 *            ProjectID
	 * 
	 */
	public void deleteProject(Integer id) {
		projectRepo.delete(id);
	}

	/**
	 * 
	 * @param projectInfo
	 *            - ProjectInfo RequestBody
	 * 
	 * @param id-
	 *            ProjectID
	 * 
	 * @return - ProjectResponse Object
	 */
	public ProjectInformationResponse updateProject(ProjectRequest projectInfo, Integer id) {

		Project project = projectRepo.findOne(id);
		if (projectInfo.getProjectCompanyNameNew() != null)
			project.setProjectClientName(projectInfo.getProjectCompanyNameNew());
		if (projectInfo.getProjectEndDateNew() != null)
			project.setProjectEndDate(projectInfo.getProjectEndDateNew());
		if (projectInfo.getProjectNameNew() != null)
			project.setProjectName(projectInfo.getProjectNameNew());
		if (projectInfo.getProjectStartDateNew() != null)
			project.setProjectStartDate(projectInfo.getProjectStartDateNew());
		if (projectInfo.getProjectStatusNew() != null)
			project.setProjectStatus(projectInfo.getProjectStatusNew());
		projectRepo.save(project);
		ProjectInformationResponse projectResponse = createProjectResponse(project);
		return projectResponse;

	}

	public ProjectInformationResponse createProjectResponse(Project project) {
		ProjectInformationResponse projectResponse = new ProjectInformationResponse();
		projectResponse.setProjectClientName(project.getProjectClientName());
		projectResponse.setUserstories(project.getUserstories());
		projectResponse.setProjectDescription(project.getProjectDescription());
		projectResponse.setProjectId(project.getProjectId());
		projectResponse.setProjectName(project.getProjectName());
		projectResponse.setProjectStatus(project.getProjectStatus());
		projectResponse.setProjectStartDate(project.getProjectStartDate());
		projectResponse.setProjectEndDate(project.getProjectEndDate());
		return projectResponse;
	}

	public CategoryResponse createCategoryResponse(UserStoryCategory category) {

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setCategoryId(category.getCategoryId());
		categoryResponse.setCategoryName(category.getCategoryName());

		return categoryResponse;

	}

}
