package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDirector;

public interface IGpDirectorDAO extends IGpEmployeeDAO<GpDirector>{
	public GpDirector create(GpDirector emp);
	public List<GpDirector> findAll();
	public GpDirector findById(Integer empId);

}
