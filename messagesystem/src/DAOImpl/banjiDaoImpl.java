package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import DAO.banjiDao;
import JavaBean.banji;
import JavaBean.student;

public class banjiDaoImpl implements banjiDao {

	@Override
	public List<banji> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(banji c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(banji c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(banji c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<banji> findID(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select DISTINCT BID,Bname,Bnum from class,learn where class.BID=learn.LBID and learn.LTID=(select TID from teacher where TeacherID=? );";
		PreparedStatement pStatement=null;
		List<banji> list=new ArrayList<banji>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				banji ban=new banji();
				ban.setBid(rSet.getInt(1));
				ban.setBname(rSet.getString(2));
				ban.setBnum(rSet.getInt(3));
				list.add(ban);
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
	public List<banji> findfromcourse(String c,String d) {
		Connection connection=DBConnection.getConnection();
		String sql="select DISTINCT BID,Bname,Bnum from class,learn where class.BID=learn.LBID and learn.LCID=(select CID from course where Cname=?) and learn.LTID=(select TID from teacher where TeacherID=?);";
		PreparedStatement pStatement=null;
		List<banji> list=new ArrayList<banji>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setString(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				banji ban=new banji();
				ban.setBid(rSet.getInt(1));
				ban.setBname(rSet.getString(2));
				ban.setBnum(rSet.getInt(3));
				list.add(ban);
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
	public int findclassnum(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select Bnum from class where Bname=?;";
		PreparedStatement pStatement=null;
		int i = 0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				i=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return i;
	}

	@Override
	public int findbanjiid(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select BID from class where Bname=?;";
		PreparedStatement pStatement=null;
		int i = 0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				i=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return i;
	}

	@Override
	public String findbanjiname(int i) {
		Connection connection=DBConnection.getConnection();
		String sql="select Bname from class where BID=?;";
		PreparedStatement pStatement=null;
		String banji=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, i);
			
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				banji=rSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return banji;
	}

}
