package DAO;

import java.util.List;

import JavaBean.course;

public interface courseDao extends GeneralDao<course> {
	public List<course> findfromteaid(String c);
	public String findcourse(int c);
	public int findcid(String c);
	public List<String> findfromBID(int bid);
	public String findcoursename(int cid);
}
