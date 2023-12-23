import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	public static boolean Login(String username, String password) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/SCMarketPro";
			String user = "root";
			String myPass ="20020814";
			conn = DriverManager.getConnection(url, user, myPass);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * From Users");
			
			while(rs.next()) {
				String name = rs.getString("Username");
				String pass = rs.getString("Password");
				
				if(name.equals(username) && pass.equals(password)) {
					return true;
				}
			}
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(st != null) {
					st.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		
		return false;
	}
}