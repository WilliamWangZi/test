package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import DAO.knowledgeDao;
import JavaBean.course;
import JavaBean.knowledge;

public class knowledgeDaoImpl implements knowledgeDao {

	@Override
	public List<knowledge> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(knowledge c) {
		Connection connection=DBConnection.getConnection();
		String sql="insert into knowledge(Kcontent,KCID)value(?,?);";
		PreparedStatement pStatement=null;
		List<knowledge> list=new ArrayList<knowledge>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c.getKcontent());
			pStatement.setInt(2, c.getKcid());
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
	public void update(knowledge c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(knowledge c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<knowledge> findallKnowledges(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select KID,Kcontent from knowledge where KCID=(select CID from course where Cname=?);";
		PreparedStatement pStatement=null;
		List<knowledge> list=new ArrayList<knowledge>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				knowledge kno=new knowledge();
				kno.setKid(rSet.getInt(1));
				kno.setKcontent(rSet.getString(2));
				list.add(kno);
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
	public void delete(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="delete from knowledge where KID=?";
		PreparedStatement pStatement=null;
		
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
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
