package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpProjectDAOTest {

	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();

	@Test
	public void testCreateProjectWithSuccess() {
		// Given
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-1");
		project.setName("Project-1");
		project.setDescription("First Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());

		// début code-tempo

		GpOrganization organization = new GpOrganization();
		organization.setId(1);
		project.setGpOrganization(organization);

		GpProjectManager manager = new GpProjectManager();
		manager.setId(1);
		project.setGpChefProjet(manager);
		// fin code-tempo

		// When
		project = projectDAO.create(project);

		// Then
		Assert.assertNotNull(project.getId());
	}

	@Test
	public void testFindAllProjectsWithSuccess() {
		// Given

		// When
		List<GpProject> projects = this.projectDAO.findAll();
		// Then
		Assert.assertTrue(projects.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer projectId = 1;

		// When
		GpProject project = this.projectDAO.findById(projectId);
		// Then
		Assert.assertNotNull(project);
	}

	@Test
	public void testDeleteProjectWithSuccess() {
		// Given
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());

		project.setProjectCode("X");
		project.setName("X");
		project.setDescription("Project To Test Delete");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());

		// début code-tempo

		GpOrganization organization = new GpOrganization();
		organization.setId(1);
		project.setGpOrganization(organization);

		GpProjectManager manager = new GpProjectManager();
		manager.setId(1);
		project.setGpChefProjet(manager);
		// fin code-tempo

		// When
		project = projectDAO.create(project);
		// Then
		Assert.assertNotNull(project.getId());

		projectDAO.deleteById(project.getId());

	}

}
