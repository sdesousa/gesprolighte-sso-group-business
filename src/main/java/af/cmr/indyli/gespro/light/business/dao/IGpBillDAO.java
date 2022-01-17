package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpBill;

public interface IGpBillDAO {
	public GpBill create(GpBill project);

	public void update(GpBill project);

	public List<GpBill> findAll();

	public void deleteById(Integer projectId);

	public GpBill findById(Integer projectId);
}