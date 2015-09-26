package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import connection.DBConnection;
import DAO.answerDao;
import JavaBean.answer;
import JavaBean.exam;
import JavaBean.student;

public class answerDaoImpl implements answerDao {

	@Override
	public List<answer> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(answer c) {
		Connection connection=DBConnection.getConnection();
		String sql="insert into answer(ABEID,Avalue,AEID,AStuID,ABID)value(?,?,?,?,?);";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c.getAbeid());
			pStatement.setInt(2, c.getAvalue());
			pStatement.setInt(3, c.getAeid());
			pStatement.setString(4, c.getAstuid());
			pStatement.setInt(5, c.getAbid());
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
	public void update(answer c) {
		Connection connection=DBConnection.getConnection();
		String sql="update  answer set Avalue=? where AEID=? and AStuID=? and ABEID=?;";
		PreparedStatement pStatement=null;
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, c.getAvalue());
			pStatement.setInt(2, c.getAeid());
			pStatement.setString(3, c.getAstuid());
			pStatement.setInt(4, c.getAbeid());
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
	public void delete(answer c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> findStuScore(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select AStuID,sum(Avalue) as sumvalue from answer where ABID=(select BID from class where Bname=?) group by AStuID ORDER BY sumvalue DESC; ";
		PreparedStatement pStatement=null;
		Map<String, String> sMap=new LinkedHashMap<String, String>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				sMap.put(rSet.getString(1), rSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return sMap;
	}

	@Override
	public List<answer> findeveryScore(String c, int d) {
		Connection connection=DBConnection.getConnection();
		String sql="select AID,ABEID,Avalue,AStuID from answer where ABID=(select BID from class where Bname=?) and AEID=?;";
		PreparedStatement pStatement=null;
		List<answer> list=new ArrayList<answer>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setInt(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				answer ans=new answer();
				ans.setAid(rSet.getInt(1));
				ans.setAbeid(rSet.getInt(2));
				ans.setAvalue(rSet.getInt(3));
				ans.setAstuid(rSet.getString(4));
				list.add(ans);
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
	public List<Double> findavgScore(String c, int d) {
		Connection connection=DBConnection.getConnection();
		String sql="select AVG(Avalue) from answer where ABID=(select BID from class where Bname=?) and AEID=? group by ABEID ;";
		PreparedStatement pStatement=null;
		List<Double> list=new ArrayList<Double>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setInt(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				list.add(rSet.getDouble(1));
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
	public List<Integer> findsumScore(String c, int d) {
		Connection connection=DBConnection.getConnection();
		String sql="select sum(Avalue) from answer where ABID=(select BID from class where Bname=?) and AEID=? group by AStuID;";
		PreparedStatement pStatement=null;
		List<Integer> list=new ArrayList<Integer>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setInt(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				list.add(rSet.getInt(1));
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
	public List<Integer> findexams(String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select DISTINCT AEID from answer where ABID=(select BID from class where Bname=?);";
		PreparedStatement pStatement=null;
		List<Integer> list=new ArrayList<Integer>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				list.add(rSet.getInt(1));
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
	public Map<String, Integer> findsumScore1(String c, int d) {
		Connection connection=DBConnection.getConnection();
		String sql="select AStuID,sum(Avalue) from answer where ABID=(select BID from class where Bname=?) and AEID=? group by AStuID;";
		PreparedStatement pStatement=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, c);
			pStatement.setInt(2, d);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				map.put(rSet.getString(1), rSet.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
		return map;
	}

	@Override
	public int findpassnum(String c, int d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getid(int a, int b, String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select AID from answer where ABEID=? and AEID=? and AStuID=?;";
		PreparedStatement pStatement=null;
		int i=0;
		try {
			pStatement=connection.prepareStatement(sql);
			
			pStatement.setInt(1, a);
			pStatement.setInt(2, b);
			pStatement.setString(3, c);
			System.out.print("   "+a+b+c+"    ");
//			System.out.println("pStatement.executeQuery()="+pStatement.execute());
			ResultSet rSet=pStatement.executeQuery();
//			int j=0;
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
//		System.out.println("数据"+i);
		return i;
	}

	@Override
	public answer getanswer(int a, int b, String c) {
		Connection connection=DBConnection.getConnection();
		String sql="select * from answer where ABEID=? and AEID=? and AStuID=?;";
		PreparedStatement pStatement=null;
		int i=0;
		answer an=new answer();
		try {
			pStatement=connection.prepareStatement(sql);
			
			pStatement.setInt(1, a);
			pStatement.setInt(2, b);
			pStatement.setString(3, c);
//			System.out.print("   "+a+b+c+"    ");
//			System.out.println("pStatement.executeQuery()="+pStatement.execute());
			ResultSet rSet=pStatement.executeQuery();
//			int j=0;
//			System.out.print(rSet.isLast()+" ");
			while(rSet.next()){
//				System.out.print(rSet.isLast()+" ");
				an.setAid(rSet.getInt(1));
				an.setAbeid(rSet.getInt(2));
				an.setAvalue(rSet.getInt(3));
				an.setAeid(rSet.getInt(4));
				an.setAstuid(rSet.getString(5));
				an.setAbid(rSet.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(pStatement);
			DBConnection.close(connection);
		}
//		System.out.println("数据"+i);
		return an;
	}

	@Override
	public List<Integer> getbid(int eid) {
		Connection connection=DBConnection.getConnection();
		String sql="select DISTINCT ABID from answer where AEID=?;";
		PreparedStatement pStatement=null;
		List<Integer> list=new ArrayList<Integer>();
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, eid);
			ResultSet rSet=pStatement.executeQuery();
			while(rSet.next()){
				list.add(rSet.getInt(1));
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
	public void deletemore(int examid, int que_no) {
		Connection connection=DBConnection.getConnection();
		String sql="delete from answer where AEID=? and ABEID>?;";
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
