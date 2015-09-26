package DAO;

import java.util.List;

import JavaBean.question;

public interface questionDao extends GeneralDao<question> {
	public List<question> findquestions(int c);
	public int findqid(question q);
	public void deletemore(int examid,int que_no);
}
