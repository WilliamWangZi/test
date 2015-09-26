package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import connection.DBConnection;
import DAO.GeneralDao;
import DAO.teacherDao;
import JavaBean.teacher;

public class teacherDaoImpl implements teacherDao {

	@Override
	public List<teacher> find() {
		Connection connection=DBConnection.getConnection();
		String sql="select * from teacher;";
		PreparedStatement pStatement=null;
		List<teacher> list=new ArrayList<teacher>();
		try {
			pStatement=connection.prepareStatement(sql);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				teacher tea=new teacher();
				tea.setTid(rSet.getInt(1));
				tea.setTeacherid(rSet.getString(2));
				tea.setTname(rSet.getString(3));
				tea.setPassword(rSet.getString(4));
				tea.setPhone(rSet.getString(5));
				tea.setSchool(rSet.getString(6));
				tea.setAcademy(rSet.getString(7));
				tea.setDepartment(rSet.getString(8));
				list.add(tea);
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
	public void save(teacher c) {
		Connection connection=DBConnection.getConnection();
		String sql="insert into teacher(TeacherID,Password)value(?,?);";
		PreparedStatement pStatement=null;
	
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c.getTeacherid());
			pStatement.setString(2, c.getPassword());
			pStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
	}

	@Override
	public teacher findId(String h) {
		// TODO Auto-generated method stub
		Connection connection=DBConnection.getConnection();
		String sql="select * from teacher where TeacherID=?;";
		PreparedStatement pStatement=null;
		teacher tea=new teacher();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, h);
			ResultSet rSet=pStatement.executeQuery();
			if(rSet.next()){
				tea.setTid(rSet.getInt(1));
				tea.setTeacherid(rSet.getString(2));
				tea.setTname(rSet.getString(3));
				tea.setPassword(rSet.getString(4));
				tea.setPhone(rSet.getString(5));
				tea.setSchool(rSet.getString(6));
				tea.setAcademy(rSet.getString(7));
				tea.setDepartment(rSet.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return tea;
	}

	@Override
	public void update(teacher c) {
		// TODO Auto-generated method stub
		Connection connection=DBConnection.getConnection();
		String sql="update teacher set Tname=?,Password=?,Phone=?,school=?,Academy=?,Department=? where TeacherID=?;";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c.getTname());
			pStatement.setString(2, c.getPassword());
			pStatement.setString(3, c.getPhone());
			pStatement.setString(4, c.getSchool());
			pStatement.setString(5, c.getAcademy());
			pStatement.setString(6, c.getDepartment());
			pStatement.setString(7, c.getTeacherid());
			pStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
	}

	@Override
	public void delete(teacher c) {
		// TODO Auto-generated method stub
		
	}

}
