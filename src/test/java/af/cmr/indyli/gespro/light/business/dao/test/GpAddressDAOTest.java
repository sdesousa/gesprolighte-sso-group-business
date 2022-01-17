package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;

public class GpAddressDAOTest {
	private IGpAddressDAO addressDAO = new GpAddressDAOImpl();

	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;

	@Test
	public void testFindAllAdminWithSuccess() {
		// Given

		// When
		List<GpAddress> addrList = this.addressDAO.findAll();
		// Then
		Assert.assertTrue(addrList.size() == 0);
	}

	@Before
	public void prepareAllEntityBefore() {
		GpAddress addr = new GpAddress();
		Assert.assertNull(addr.getId());
		addr.setId(1050);
		addr.setStreetNumber(1050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("ROYAL");
		byte isMain = 5;
		addr.setIsMain(isMain);
		addr = addressDAO.create(addr);
		addrIdForAllTest = addr.getId();

	}

	@After
	public void deleteAllEntityAfter() {
		this.addressDAO.deleteById(this.addrIdForAllTest);
		if (!Objects.isNull(this.createAddrId)) {
			this.addressDAO.deleteById(this.createAddrId);
		}
	}
}
