package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import DAO.courseDao;
import JavaBean.banji;
import JavaBean.course;

public class courseDaoImpl implements courseDao {

	@Override
	public List<course> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(course c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(course c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(course c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<course> findfromteaid(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select CID,Cname from course where CTID=(select TID from teacher where TeacherID=?);";
		PreparedStatement pStatement=null;
		List<course> list=new ArrayList<course>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				course cou=new course();
				cou.setCid(rSet.getInt(1));
				cou.setCname(rSet.getString(2));
				list.add(cou);
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
	public String findcourse(int c) {
		Connection connection=DBConnection.getConnection();
		String sql="select Cname from course where CID=(select ECID from exam where EID=?);";
		PreparedStatement pStatement=null;
		String course=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c);
			ResultSet rSet=pStatement.executeQuery();
			if(rSet.next()){
				course=rSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return course;
	}

	@Override
	public int findcid(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select CID from course where Cname=?";
		PreparedStatement pStatement=null;
		int cid=0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			if(rSet.next()){
				cid=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return cid;
	}

	@Override
	public List<String> findfromBID(int bid) {
		Connection connection=DBConnection.getConnection();
		String sql="select Cname from course,learn where course.CID=learn.LCID and learn.LBID=?;";
		PreparedStatement pStatement=null;
		List<String> list=new ArrayList<String>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, bid);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				list.add(rSet.getString(1));
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
	public String findcoursename(int cid) {
		Connection connection=DBConnection.getConnection();
		String sql="select Cname from course where CID=?";
		PreparedStatement pStatement=null;
		String course=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, cid);
			
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				course=rSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return course;
	}

}
