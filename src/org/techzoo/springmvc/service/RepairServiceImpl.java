package org.techzoo.springmvc.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.techzoo.springmvc.form.RepairPresentation;
import org.techzoo.springmvc.jdbcDAO.RepairJDBC;

@Service("repairService")
public class RepairServiceImpl implements RepairService
{
	@Override
	public int insertRepairPresentation(RepairPresentation repairPresentation)
	{
		String sql = "INSERT INTO repair_presentation (classno,userid,name,phone,place,buildingno,buildingdescribe,hydropower,furniture"
				+ ",other,explaindetail,picture,repairtime,createtime,repairacceptance,repairedacceptance,status) values ('"
				+ repairPresentation.getClassno()
				+ "','"
				+ repairPresentation.getUserid()
				+ "','"
				+ repairPresentation.getName()
				+ "','"
				+ repairPresentation.getPhone()
				+ "','"
				+ repairPresentation.getPlace()
				+ "','"
				+ repairPresentation.getBuildingno()
				+ "','"
				+ repairPresentation.getBuildingdescribe()
				+ "','"
				+ repairPresentation.getHydropower()
				+ "','"
				+ repairPresentation.getFurniture()
				+ "','"
				+ repairPresentation.getOther()
				+ "','"
				+ repairPresentation.getExplaindetail()
				+ "','"
				+ repairPresentation.getPicture()
				+ "','"
				+ repairPresentation.getRepairtime()
				+ "',now(),'"
				+ repairPresentation.getRepairacceptance()
				+ "','"
				+ repairPresentation.getRepairedacceptance() + "'," + repairPresentation.getStatus() + ")";

		RepairJDBC jdbc = new RepairJDBC();
		jdbc.setSql(sql);
		return jdbc.executeUpdate();
	}

	@Override
	public List<Map<String, Object>> queryRepairList(int status)
	{
		String sql = "SELECT * FROM repair_presentation WHERE status=" + status + " ORDER BY id DESC";

		if (status < 0)
		{
			sql = "SELECT * FROM repair_presentation ORDER BY id DESC";
		}
		RepairJDBC jdbc = new RepairJDBC();
		jdbc.setSql(sql);
		List<Map<String, Object>> list = jdbc.executeQuery();
		return list;
	}

	@Override
	public Map<String, Object> getRepair(int id)
	{
		String sql = "SELECT * FROM repair_presentation WHERE id=" + id;
		RepairJDBC jdbc = new RepairJDBC();
		jdbc.setSql(sql);
		List<Map<String, Object>> list = jdbc.executeQuery();

		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int setRepairAudited(int id, int status)
	{
		String sql = "UPDATE repair_presentation SET status=" + status + " WHERE id=" + id;
		RepairJDBC jdbc = new RepairJDBC();
		jdbc.setSql(sql);
		return jdbc.executeUpdate();
	}
}