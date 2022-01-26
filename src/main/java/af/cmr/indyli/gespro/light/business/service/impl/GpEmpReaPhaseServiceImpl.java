package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.service.IGpEmpReaPhaseService;

public class GpEmpReaPhaseServiceImpl implements IGpEmpReaPhaseService<GpEmpReaPhase>{

	private IGpEmpReaPhaseDAO<GpEmpReaPhase> erpDAO = new GpEmpReaPhaseDAOImpl();
	
	public GpEmpReaPhase create(GpEmpReaPhase erp){
		return this.erpDAO.create(erp);
	}

	public void update(GpEmpReaPhase erp){
		this.erpDAO.update(erp);
	}

	public List<GpEmpReaPhase> findAll() {
		return this.erpDAO.findAll();
	}

	public void deleteById(Integer erpId) {
		this.erpDAO.deleteById(erpId);
	}

	public GpEmpReaPhase findById(Integer erpId) {
		return this.erpDAO.findById(erpId);
	}

}
