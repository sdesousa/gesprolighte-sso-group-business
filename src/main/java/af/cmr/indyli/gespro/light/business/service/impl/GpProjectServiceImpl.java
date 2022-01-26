package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;

public class GpProjectServiceImpl implements IGpProjectService<GpProject>{

	private IGpProjectDAO<GpProject> prjDAO = new GpProjectDAOImpl();
	
	public GpProject create(GpProject prj){
		return this.prjDAO.create(prj);
	}

	public void update(GpProject prj){
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
