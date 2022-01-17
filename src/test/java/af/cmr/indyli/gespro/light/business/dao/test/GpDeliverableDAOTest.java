package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpDeliverableDAOTest {

	private IGpDeliverableDAO deliverableDAO = new GpDeliverableDAOImpl();

	@Test
	public void testCreateDeliverableWithSuccess() {
		// Given
		GpDeliverable deliverable = new GpDeliverable();
		Assert.assertNull(deliverable.getId());
		deliverable.setDelCode("Deliverable-1");
		deliverable.setDescription("Deliverable Test Unit");
		deliverable.setLabel("Label Test");
		deliverable.setDelPath("Path Test Unit");
		deliverable.setCreationDate(new Date());

		// début code-tempo

		GpPhase gpPhase = new GpPhase();
		gpPhase.setId(2);

		deliverable.setGpPhase(gpPhase);

		// fin code-tempo

		// When
		deliverable = deliverableDAO.create(deliverable);

		// Then
		Assert.assertNotNull(deliverable.getId());
	}

	@Test
	public void testFindAllDeliverablesWithSuccess() {
		// Given

		// When
		List<GpDeliverable> deliverables = this.deliverableDAO.findAll();
		// Then
		Assert.assertTrue(deliverables.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer deliverableId = 1;

		// When
		GpDeliverable deliverable = this.deliverableDAO.findById(deliverableId);
		// Then
		Assert.assertNotNull(deliverable);
	}

	@Test
	public void testDeleteDeliverableWithSuccess() {
		// Given
		GpDeliverable deliverable = new GpDeliverable();
		Assert.assertNull(deliverable.getId());
		deliverable.setDelCode("Deliverable-1");
		deliverable.setDescription("Deliverable Test Unit");
		deliverable.setLabel("Label Test");
		deliverable.setDelPath("Path Test Unit");
		deliverable.setCreationDate(new Date());
		
		// début code-tempo

		GpPhase gpPhase = new GpPhase();
		gpPhase.setId(2);

		deliverable.setGpPhase(gpPhase);

		// fin code-tempo

		// When
		deliverable = deliverableDAO.create(deliverable);

		deliverableDAO.deleteById(deliverable.getId());

	}
}
