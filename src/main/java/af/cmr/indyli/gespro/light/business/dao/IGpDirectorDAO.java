package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDirector;

public interface IGpDirectorDAO extends IGpEmployeeDAO<GpDirector>{
	public GpDirector create(GpDirector emp);
	public void update(GpDirector emp);
	public List<GpDirector> findAll();
	public void deleteById(Integer empId);
	public GpDirector findById(Integer empId);

}
