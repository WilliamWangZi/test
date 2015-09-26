package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import connection.DBConnection;
import DAO.studentDao;
import JavaBean.banji;
import JavaBean.student;
import JavaBean.teacher;

public class studentDaoImpl implements studentDao {

	@Override
	public List<student> find() {
		Connection connection=DBConnection.getConnection();
		String sql="select * from student;";
		PreparedStatement pStatement=null;
		List<student> list=new ArrayList<student>();
		try {
			pStatement=connection.prepareStatement(sql);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				student stu=new student();
				stu.setSid(rSet.getInt(1));
				stu.setStuid(rSet.getString(2));
				stu.setSname(rSet.getString(3));
				stu.setBid(rSet.getInt(4));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return list;
	}

	@Override
	public void save(student c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(student c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(student c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<student> findfromclass(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select * from student where SBID=(select BID from class where Bname=?);";
		PreparedStatement pStatement=null;
		List<student> list=new ArrayList<student>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				student stu=new student();
				stu.setSid(rSet.getInt(1));
				stu.setStuid(rSet.getString(2));
				stu.setSname(rSet.getString(3));
				stu.setBid(rSet.getInt(4));
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return list;
	}

	@Override
	public JsonArray findfromBID(int c) {
		Connection connection=DBConnection.getConnection();
		String sql="select StuID,Sname from student where SBID=?;";
		PreparedStatement pStatement=null;
		
//		Map<Integer,  List<String>> map=new HashMap<Integer, List<String>>();
		JsonArray array=new JsonArray();
		try {
			int i=0;
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				JsonObject jsonObject=new JsonObject();
				jsonObject.addProperty("Stuid",rSet.getString(1));
				jsonObject.addProperty("Sname",rSet.getString(2));
				array.add(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return array;
	}

}
