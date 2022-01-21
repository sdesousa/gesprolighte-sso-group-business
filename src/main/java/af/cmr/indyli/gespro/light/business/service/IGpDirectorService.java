package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpDirectorService extends IGpEmployeeService<GpDirector>{
	public GpDirector create(GpDirector emp) throws GesproBusinessException;
	public List<GpDirector> findAll();
	public GpDirector findById(Integer empId);

}
