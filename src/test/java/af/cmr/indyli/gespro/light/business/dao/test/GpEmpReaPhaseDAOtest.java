package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImp;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpEmpReaPhaseDAOtest {

	private IGpEmpReaPhaseDAO empReaPhaseDAO = new GpEmpReaPhaseDAOImp();
	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();
	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();

	private Integer emReaPhaseIdForAllTest = null;
	private Integer emReaPhaseCreateIdTest = null;

	private GpPhase phaseTest;
	private GpProjectManager empTest;
	private GpOrganization orgTest;
	private GpProject pjTest;

	@Test
	public void testCreateProjectWithSuccess() {
		GpEmpReaPhase gpEmpReaPhase = new GpEmpReaPhase();
		gpEmpReaPhase.setCreationDate(new Date());
		gpEmpReaPhase.setGpPhase(this.phaseTest);
		gpEmpReaPhase.setGpEmployee(empTest);

		gpEmpReaPhase = this.empReaPhaseDAO.create(gpEmpReaPhase);
		Assert.assertNotNull(gpEmpReaPhase);

		this.emReaPhaseCreateIdTest = gpEmpReaPhase.getId();
	}

	@Test
	public void testFindAllProjectsWithSuccess() {
		// Given

		// When
		List<GpEmpReaPhase> empReaPhases = this.empReaPhaseDAO.findAll();
		// Then
		Assert.assertTrue(empReaPhases.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empReaId = this.emReaPhaseIdForAllTest;
		// When
		Assert.assertNotNull(empReaId);
		GpEmpReaPhase empReaPhase = this.empReaPhaseDAO.findById(empReaId);
		// Then
		Assert.assertNotNull(empReaPhase.getId());
	}

	@Test
	public void testDeleteProjectWithSuccess() {
		// Given
//		Integer empReaId = this.emReaPhaseIdForAllTest;
//		Assert.assertNotNull(empReaId);
//		// When
//		this.empReaPhaseDAO.deleteById(empReaId);
	}

	@Before
	public void prepareAllEntityBefore() {

		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("segos.royal");
		emp = this.empDAO.create(emp);

		this.empTest = new GpProjectManager();
		this.empTest = emp;
		assertNotNull(this.empTest.getId());

		// création organisation

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("ALPHA");
		organization.setName("Big Org");
		organization.setAdrWeb("bigorg.com");
		organization.setContactEmail("big@org.com");
		organization.setPhoneNumber(7895);
		organization.setId(1);// à remplacer par org from daoOrganization

		this.orgTest = new GpOrganization();
		this.orgTest = organization;
		Assert.assertNotNull(this.orgTest.getId());

		// création project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-1");
		project.setName("Project-1");
		project.setDescription("First Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(this.empTest);
		project = this.projectDAO.create(project);
		Assert.assertNotNull(project.getId());

		this.pjTest = new GpProject();
		this.pjTest = project;

		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("Phase-1");
		phase.setDescription("Première phase du projet");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5623.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseDAO.create(phase);
		Assert.assertNotNull(phase.getId());
		
		this.phaseTest = phase;

		GpEmpReaPhase empReaPhase = new GpEmpReaPhase();
		empReaPhase.setCreationDate(new Date());
		empReaPhase.setGpEmployee(empTest);
		empReaPhase.setGpPhase(phaseTest);
		empReaPhase = empReaPhaseDAO.create(empReaPhase);

		this.emReaPhaseIdForAllTest = empReaPhase.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empReaPhaseDAO.deleteById(this.emReaPhaseIdForAllTest);
		if (!Objects.isNull(this.emReaPhaseCreateIdTest)) {
			this.empDAO.deleteById(this.empTest.getId());
		}
		if (!Objects.isNull(this.empTest.getId())) {
			this.empDAO.deleteById(this.empTest.getId());
		}
	}

}
