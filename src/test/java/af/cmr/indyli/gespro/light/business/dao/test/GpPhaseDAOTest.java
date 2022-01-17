package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpPhaseDAOTest {

	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();

	@Test
	public void testCreatePhaseWithSuccess() {
		// Given
		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("Phase-1");
		phase.setDescription("Phase Test");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5623.66);
		phase.setCreationDate(new Date());

		// début code-tempo

		GpProject project = new GpProject();
		project.setId(1);
		
		phase.setGpProject(project);

		// fin code-tempo

		// When
		phase = phaseDAO.create(phase);

		// Then
		Assert.assertNotNull(phase.getId());
	}

	@Test
	public void testFindAllPhasesWithSuccess() {
		// Given

		// When
		List<GpPhase> phases = this.phaseDAO.findAll();
		// Then
		Assert.assertTrue(phases.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer phaseId = 1;

		// When
		GpPhase phase = this.phaseDAO.findById(phaseId);
		// Then
		Assert.assertNotNull(phase);
	}

	@Test
	public void testDeletePhaseWithSuccess() {
		// Given
		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("Phase-1");
		phase.setDescription("Phase Test");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5623.66);
		phase.setCreationDate(new Date());

		// début code-tempo

		GpProject project = new GpProject();
		project.setId(1);

		// fin code-tempo

		phase.setGpProject(project);
		phase = phaseDAO.create(phase);

		// Then
		Assert.assertNotNull(phase.getId());

		phaseDAO.deleteById(phase.getId());

	}

}
