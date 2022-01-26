package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.service.IGpDeliverableService;

public class GpDeliverableServiceImpl implements IGpDeliverableService<GpDeliverable>{

	private IGpDeliverableDAO<GpDeliverable> dlrDAO = new GpDeliverableDAOImpl();
	
	public GpDeliverable create(GpDeliverable dlr){
		return this.dlrDAO.create(dlr);
	}

	public void update(GpDeliverable dlr){
		this.dlrDAO.update(dlr);
	}

	public List<GpDeliverable> findAll() {
		return this.dlrDAO.findAll();
	}

	public void deleteById(Integer dlrId) {
		this.dlrDAO.deleteById(dlrId);
	}

	public GpDeliverable findById(Integer dlrId) {
		return this.dlrDAO.findById(dlrId);
	}

}
