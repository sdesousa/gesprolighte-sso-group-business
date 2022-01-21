package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;

public class GpOrganizationServiceImpl implements IGpOrganizationService<GpOrganization>{

	private IGpOrganizationDAO<GpOrganization> orgDAO = new GpOrganizationDAOImpl();
	
	public GpOrganization create(GpOrganization org){
		return this.orgDAO.create(org);
	}

	public void update(GpOrganization org){
		this.orgDAO.update(org);
	}

	public List<GpOrganization> findAll() {
		return this.orgDAO.findAll();
	}

	public void deleteById(Integer orgId) {
		this.orgDAO.deleteById(orgId);
	}

	public GpOrganization findById(Integer orgId) {
		return this.orgDAO.findById(orgId);
	}

}
