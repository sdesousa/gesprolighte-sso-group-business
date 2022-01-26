package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.service.IGpAddressService;

public class GpAddressServiceImpl implements IGpAddressService<GpAddress>{

	private IGpAddressDAO<GpAddress> adrDAO = new GpAddressDAOImpl();
	
	public GpAddress create(GpAddress adr){
		return this.adrDAO.create(adr);
	}

	public void update(GpAddress adr){
		this.adrDAO.update(adr);
	}

	public List<GpAddress> findAll() {
		return this.adrDAO.findAll();
	}

	public void deleteById(Integer adrId) {
		this.adrDAO.deleteById(adrId);
	}

	public GpAddress findById(Integer adrId) {
		return this.adrDAO.findById(adrId);
	}

}
