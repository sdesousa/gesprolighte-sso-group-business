package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpTechnicianService extends IGpEmployeeService<GpTechnician>{
	public GpTechnician create(GpTechnician emp) throws GesproBusinessException;
	public void update(GpTechnician emp) throws GesproBusinessException;
	public List<GpTechnician> findAll();
	public GpTechnician findById(Integer empId);

}
