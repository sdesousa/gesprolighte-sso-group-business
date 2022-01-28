package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;

public class GpPhaseServiceImpl implements IGpPhaseService<GpPhase>{

	private IGpPhaseDAO<GpPhase> phsDAO = new GpPhaseDAOImpl();
	
	public GpPhase create(GpPhase phs) throws GesproBusinessException{
		if(phs.getStartDate() != null && !phsDAO.isDateValid(phs.getStartDate(), phs.getGpProject().getStartDate())) {
			throw new GesproBusinessException(String.format("La phase doit commencer apr�s le projet"));
		}
		if(phs.getStartDate() != null && !phsDAO.isAmountValid(phs.getStartDate(), phs.getEndDate(), phs.getAmount())) {
			throw new GesproBusinessException(String.format("Le montant doit �tre sup�rieur � 150.000"));
		}
		return this.phsDAO.create(phs);
	}

	public void update(GpPhase phs) throws GesproBusinessException{
		if(phs.getStartDate() != null && !phsDAO.isDateValid(phs.getStartDate(), phs.getGpProject().getStartDate())) {
			throw new GesproBusinessException(String.format("La phase doit commencer apr�s le projet"));
		}
		if(phs.getStartDate() != null && !phsDAO.isAmountValid(phs.getStartDate(), phs.getEndDate(), phs.getAmount())) {
			throw new GesproBusinessException(String.format("Le montant doit �tre sup�rieur � 150.000"));
		}
		this.phsDAO.update(phs);
	}

	public List<GpPhase> findAll() {
		return this.phsDAO.findAll();
	}

	public void deleteById(Integer phsId) {
		this.phsDAO.deleteById(phsId);
	}

	public GpPhase findById(Integer phsId) {
		return this.phsDAO.findById(phsId);
	}

}
