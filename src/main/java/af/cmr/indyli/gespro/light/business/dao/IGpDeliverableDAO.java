package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;

public interface IGpDeliverableDAO  {
	public GpDeliverable create(GpDeliverable del);
	public void update(GpDeliverable del);
	public List<GpDeliverable> findAll();
	public void deleteById(Integer projectId);
	public GpDeliverable findById(Integer delId);
}		