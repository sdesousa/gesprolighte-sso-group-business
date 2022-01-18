package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpAccountant;

public interface IGpAccountantDAO extends IGpEmployeeDAO<GpAccountant>{
	public GpAccountant create(GpAccountant emp);
	public void update(GpAccountant emp);
	public List<GpAccountant> findAll();
	public void deleteById(Integer empId);
	public GpAccountant findById(Integer empId);

}
