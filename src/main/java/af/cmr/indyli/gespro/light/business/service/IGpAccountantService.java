package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpAccountantService extends IGpEmployeeService<GpAccountant>{
	public GpAccountant create(GpAccountant emp) throws GesproBusinessException;
	public List<GpAccountant> findAll();
	public GpAccountant findById(Integer empId);

}
