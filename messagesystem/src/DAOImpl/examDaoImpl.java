package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import DAO.examDao;
import JavaBean.banji;
import JavaBean.exam;
import JavaBean.knowledge;

public class examDaoImpl implements examDao {

	@Override
	public List<exam> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(exam c) {
		Connection connection=DBConnection.getConnection();
		String sql="insert into exam(Examname,Questionnum,ETID,ECID)value(?,?,?,?);";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c.getExamname());
			pStatement.setInt(2, c.getQuestionnum());
			pStatement.setInt(3, c.getEtid());
			pStatement.setInt(4, c.getEcid());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}

	}

	@Override
	public void update(exam c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(exam c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<exam> findfromexam(String c, String d) {
		Connection connection=DBConnection.getConnection();
		String sql="select EID,Questionnum from exam where ETID=(select TID from teacher where TeacherID=?) and ECID=(select CID from course where Cname=?);";
		PreparedStatement pStatement=null;
		List<exam> list=new ArrayList<exam>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setString(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				exam ex=new exam();
				ex.setEid(rSet.getInt(1));
				ex.setQuestionnum(rSet.getInt(2));
				list.add(ex);
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
	public int getid(String c,int d,int h) {
		Connection connection=DBConnection.getConnection();
		String sql="select * from exam where Examname=? and ECID=? and ETID=?;";
		PreparedStatement pStatement=null;
		int i=0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setInt(2, d);
			pStatement.setInt(3, h);
			System.out.println(c+d+h);
//			System.out.println("pStatement.executeQuery()="+pStatement.execute());
			ResultSet rSet=pStatement.executeQuery();
			int j=0;
//			System.out.print(rSet.isLast()+" ");
			while(rSet.next()){
//				System.out.print(rSet.isLast()+" ");
				i=rSet.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
//		System.out.println("Êý¾Ý"+i);
		return i;
	}

	@Override
	public List<exam> findforexam(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select * from exam where ETID=(select TID from teacher where TeacherID=?);";
		PreparedStatement pStatement=null;
		List<exam> list=new ArrayList<exam>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				exam ex=new exam();
				ex.setEid(rSet.getInt(1));
				ex.setExamname(rSet.getString(2));
				ex.setQuestionnum(rSet.getInt(3));
				ex.setEtid(rSet.getInt(4));
				ex.setEcid(rSet.getInt(5));
				list.add(ex);
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
	public int findque_num(int examid) {
		Connection connection=DBConnection.getConnection();
		String sql="select Questionnum from exam where EID=?;";
		PreparedStatement pStatement=null;
		int que_num=0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, examid);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				que_num=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return que_num;
	}

}
