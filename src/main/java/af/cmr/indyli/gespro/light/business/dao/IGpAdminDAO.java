package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAdmin;

public interface IGpAdminDAO extends IGpEmployeeDAO<GpAdmin>{
	public GpAdmin create(GpAdmin emp);
	public void update(GpAdmin emp);
	public List<GpAdmin> findAll();
	public GpAdmin findById(Integer empId);

}
