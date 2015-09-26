package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBConnection {
	public static Connection getConnection(){
		Connection conn=null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/messagesystem?useUnicode=true&characterEncoding=utf8", "root", "root");
		}catch(ClassNotFoundException e){
				e.printStackTrace();
		}catch(SQLException e){
				e.printStackTrace();
		}
		return conn;
		}
		public static void close(Connection conn) {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		public static void close(PreparedStatement pstmt) {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static void close(ResultSet rs) {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
