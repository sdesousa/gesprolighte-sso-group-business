package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectManagerService extends IGpEmployeeService<GpProjectManager>{
	public GpProjectManager create(GpProjectManager emp) throws GesproBusinessException;
	public List<GpProjectManager> findAll();
	public GpProjectManager findById(Integer empId);

}
