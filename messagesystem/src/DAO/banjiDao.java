package DAO;

import java.util.List;

import JavaBean.banji;

public interface banjiDao extends GeneralDao<banji> {
	public List<banji> findID(String c);
	public List<banji> findfromcourse(String c, String d);
	public int findclassnum(String c);
	public int findbanjiid(String c);
	public String findbanjiname(int i);
}
