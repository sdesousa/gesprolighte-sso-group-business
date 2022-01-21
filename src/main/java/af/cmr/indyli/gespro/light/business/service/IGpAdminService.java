package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpAdminService extends IGpEmployeeService<GpAdmin>{
	public GpAdmin create(GpAdmin emp) throws GesproBusinessException;
	public List<GpAdmin> findAll();
	public GpAdmin findById(Integer empId);

}
