package DAO;

import java.util.List;

import JavaBean.teacher;

public interface teacherDao extends GeneralDao<teacher> {
	public teacher findId(String h);
}
