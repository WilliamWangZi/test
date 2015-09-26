package DAO;

import java.util.List;

import JavaBean.teacher;

public interface GeneralDao<T> {
	public List<T> find();
	public void save(T c);
	
	public void update(T c);
	public void delete(T c);
}
