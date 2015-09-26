package DAO;

import java.util.List;

import JavaBean.exam;

public interface examDao extends GeneralDao<exam> {
	public List<exam> findfromexam(String c,String d);
	public int getid(String c,int d,int e);
	public List<exam> findforexam(String c);
	public int findque_num(int examid);
}
