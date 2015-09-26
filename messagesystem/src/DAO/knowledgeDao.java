package DAO;

import java.util.List;

import JavaBean.knowledge;

public interface knowledgeDao extends GeneralDao<knowledge> {
	public List<knowledge> findallKnowledges(String c);
	public void delete(String c);
}
