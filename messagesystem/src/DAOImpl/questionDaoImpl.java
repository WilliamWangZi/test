package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import DAO.questionDao;
import JavaBean.exam;
import JavaBean.question;

public class questionDaoImpl implements questionDao {

	@Override
	public List<question> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(question c) {
		Connection connection=DBConnection.getConnection();
		String sql="insert into question(Qvalue,Qtype,QBEID,QEID)value(?,?,?,?);";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c.getQvalue());
			pStatement.setString(2, c.getQtype());
			pStatement.setInt(3, c.getQbeid());
			pStatement.setInt(4, c.getQeid());
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
	public void update(question q) {
		Connection connection=DBConnection.getConnection();
		String sql="update  question set Qvalue=? , Qtype=? where QEID=? and QBEID=? ;";
		PreparedStatement pStatement=null;
			
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, q.getQvalue());
			pStatement.setString(2, q.getQtype());
			pStatement.setInt(3, q.getQeid());
			pStatement.setInt(4, q.getQbeid());
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
	public void delete(question c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<question> findquestions(int c) {
		Connection connection=DBConnection.getConnection();
		String sql="select QID,Qvalue,Qtype,QBEID from question where QEID=?;";
		PreparedStatement pStatement=null;
		List<question> list=new ArrayList<question>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				question que=new question();
				que.setQid(rSet.getInt(1));
				que.setQvalue(rSet.getInt(2));
				que.setQtype(rSet.getString(3));
				que.setQbeid(rSet.getInt(4));
				list.add(que);
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
	public int findqid(question q) {
		Connection connection=DBConnection.getConnection();
		String sql="select QID from question where QEID=? and QBEID=?;";
		PreparedStatement pStatement=null;
		int i=0;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, q.getQeid());
			pStatement.setInt(2, q.getQbeid());
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
	public void deletemore(int examid, int que_no) {
		Connection connection=DBConnection.getConnection();
		String sql="delete from question where QEID=? and QBEID>?;";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, examid);
			pStatement.setInt(2,que_no);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		
	}

}
