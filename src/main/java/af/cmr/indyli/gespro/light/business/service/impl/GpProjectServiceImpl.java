package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;

public class GpProjectServiceImpl implements IGpProjectService<GpProject>{

	private IGpProjectDAO<GpProject> prjDAO = new GpProjectDAOImpl();
	
	public GpProject create(GpProject prj) throws GesproBusinessException{
		if (prj.getProjectCode() == null) {
			throw new GesproBusinessException(String.format("Le code du projet est requis"));
		}
		if (this.prjDAO.isProjectCodeExist(prj.getProjectCode())) {
			throw new GesproBusinessException(String.format("Un projet existe deja avec ce code[%s]",prj.getProjectCode()));
		}
		if ( prj.getGpOrganization() == null || !this.prjDAO.isOrganizationCreated(prj.getGpOrganization().getId())) {
			throw new GesproBusinessException(String.format("Cette organisation n'existe pas en base de donn�e"));
		}
		if ( prj.getGpChefProjet() == null || !this.prjDAO.isProjectManagerCreated(prj.getGpChefProjet().getId())) {
			throw new GesproBusinessException(String.format("Ce chef de projet n'existe pas en base de donn�e"));
		}
		if (!this.prjDAO.isDateExist(prj.getStartDate())) {
			throw new GesproBusinessException(String.format("La date de d�marrage du projet est requise"));
		}
		if (!this.prjDAO.isDateValid(prj.getStartDate(), prj.getEndDate())) {
			throw new GesproBusinessException(String.format("La date de fin dois �tre post�rieur � la date de d�but"));
		}
		return this.prjDAO.create(prj);
	}

	public void update(GpProject prj) throws GesproBusinessException{
		if (prj.getProjectCode() == null) {
			throw new GesproBusinessException(String.format("Le code du projet est requis"));
		}
		if (this.prjDAO.isProjectCodeExistUpdate(prj.getProjectCode(), prj.getId())) {
			throw new GesproBusinessException(String.format("Un projet existe deja avec ce code[%s]",prj.getProjectCode()));
		}
		if ( prj.getGpOrganization() == null || !this.prjDAO.isOrganizationCreated(prj.getGpOrganization().getId())) {
			throw new GesproBusinessException(String.format("Cette organisation n'existe pas en base de donn�e"));
		}
		if ( prj.getGpChefProjet() == null || !this.prjDAO.isProjectManagerCreated(prj.getGpChefProjet().getId())) {
			throw new GesproBusinessException(String.format("Ce chef de projet n'existe pas en base de donn�e"));
		}
		if (!this.prjDAO.isDateExist(prj.getStartDate())) {
			throw new GesproBusinessException(String.format("La date de d�marrage du projet est requise"));
		}
		if (!this.prjDAO.isDateValid(prj.getStartDate(), prj.getEndDate())) {
			throw new GesproBusinessException(String.format("La date de fin dois �tre post�rieur � la date de d�but"));
		}
		this.prjDAO.update(prj);
	}

	public List<GpProject> findAll() {
		return this.prjDAO.findAll();
	}

	public void deleteById(Integer prjId) {
		this.prjDAO.deleteById(prjId);
	}

	public GpProject findById(Integer prjId) {
		return this.prjDAO.findById(prjId);
	}

}
