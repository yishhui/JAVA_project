package dao;


import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccess {
	
	public DbAccess() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//装载驱动
			Class.forName("com.hxtt.sql.access.AccessDriver");
			
			String path=null;
			try {
				
				path = this.getClass().getClassLoader().getResource("lib/campus1.accdb").toURI().getPath();
				System.out.println(path);
				
			} catch (URISyntaxException e1) {
				e1.printStackTrace();// TODO: handle exception
			}
			String url = "jdbc:Access:"+path;
			//String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+path;
			
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
			
			String sqlstr = "select * from tblCourse";
			ResultSet rs = stmt.executeQuery(sqlstr); 
			while(rs.next()) {
				System.out.println(rs.getString("couTeacher"));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (null != stmt) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
			
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
			
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		DbAccess selfpath = new DbAccess();
		
	}

}
