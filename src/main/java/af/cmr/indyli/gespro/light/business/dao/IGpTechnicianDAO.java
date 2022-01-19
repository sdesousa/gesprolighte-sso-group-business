package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpTechnician;

public interface IGpTechnicianDAO extends IGpEmployeeDAO<GpTechnician>{
	public GpTechnician create(GpTechnician emp);
	public void update(GpTechnician emp);
	public List<GpTechnician> findAll();
	public GpTechnician findById(Integer empId);

}
