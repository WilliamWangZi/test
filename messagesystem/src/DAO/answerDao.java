package DAO;

import java.util.List;
import java.util.Map;

import JavaBean.answer;

public interface answerDao extends GeneralDao<answer> {
	public Map<String, String> findStuScore(String c);
	public List<answer> findeveryScore(String c,int d);
	public List<Double> findavgScore(String c,int d);
	public List<Integer> findsumScore(String c,int d);
	public List<Integer> findexams(String c);
	public Map<String, Integer> findsumScore1(String c,int d);
	public int findpassnum(String c,int d);
	public int getid(int a, int b,String c);
	public answer getanswer(int a, int b,String c);
	public List<Integer> getbid(int eid);
	public void deletemore(int examid,int que_no);
}
