package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpSecretary;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpSecretaryService extends IGpEmployeeService<GpSecretary>{
	public GpSecretary create(GpSecretary emp) throws GesproBusinessException;
	public List<GpSecretary> findAll();
	public GpSecretary findById(Integer empId);

}
