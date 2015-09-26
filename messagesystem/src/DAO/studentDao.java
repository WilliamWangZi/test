package DAO;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;

import JavaBean.student;

public interface studentDao extends GeneralDao<student> {
	public List<student> findfromclass(String c);
	public JsonArray findfromBID(int c);
}
