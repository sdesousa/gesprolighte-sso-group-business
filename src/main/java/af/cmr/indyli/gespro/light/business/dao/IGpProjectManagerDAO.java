package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public interface IGpProjectManagerDAO extends IGpEmployeeDAO<GpProjectManager>{
	public GpProjectManager create(GpProjectManager emp);
	public void update(GpProjectManager emp);
	public List<GpProjectManager> findAll();
	public GpProjectManager findById(Integer empId);
	public GpProjectManager promoteToProjectManager(GpEmployee emp);
}
