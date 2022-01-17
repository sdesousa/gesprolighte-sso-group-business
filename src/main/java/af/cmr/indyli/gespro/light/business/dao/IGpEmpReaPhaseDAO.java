package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;

public interface IGpEmpReaPhaseDAO {

	public GpEmpReaPhase create(GpEmpReaPhase empResPhase);

	public void update(GpEmpReaPhase emReaphase);

	public List<GpEmpReaPhase> findAll();

	public void deleteById(Integer empReaPhaseId);

	public GpEmpReaPhase findById(Integer empReaPhaseId);
}
