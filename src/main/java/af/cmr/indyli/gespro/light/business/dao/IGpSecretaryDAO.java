package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public interface IGpSecretaryDAO extends IGpEmployeeDAO<GpSecretary>{
	public GpSecretary create(GpSecretary emp);
	public void update(GpSecretary emp);
	public List<GpSecretary> findAll();
	public GpSecretary findById(Integer empId);

}
