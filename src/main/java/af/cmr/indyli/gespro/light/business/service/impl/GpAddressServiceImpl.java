package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.service.IGpAddressService;

public class GpAddressServiceImpl implements IGpAddressService<GpAddress>{

	private IGpAddressDAO<GpAddress> orgDAO = new GpAddressDAOImpl();
	
	public GpAddress create(GpAddress org){
		return this.orgDAO.create(org);
	}

	public void update(GpAddress org){
		this.orgDAO.update(org);
	}

	public List<GpAddress> findAll() {
		return this.orgDAO.findAll();
	}

	public void deleteById(Integer orgId) {
		this.orgDAO.deleteById(orgId);
	}

	public GpAddress findById(Integer orgId) {
		return this.orgDAO.findById(orgId);
	}

}
