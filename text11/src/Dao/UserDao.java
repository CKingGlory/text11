package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UserDao extends BaseDao {

	public boolean search(User user) {
		boolean flag = false;
		PreparedStatement pstat=null;
		Connection conn=null;
		ResultSet rs=null;
		// 2利用反射，加载数据库驱动
		try {
			// 3 建立连接
			conn = getConnection();
			// 4 建立statement sql语句执行器
			String sql="SELECT * FROM user where username=? and password=?";
			pstat = conn.prepareStatement(sql);
			// 5 执行sql语句并得到结果
			pstat.setString(1,user.getUsername());
			pstat.setString(2,user.getPassword());
			
			rs = pstat.executeQuery();
			// 6 对结果集进行处理

			if (rs.next()) {
				// out.print("登录成功");
				// request.getRequestDispatcher("success.jsp").forward(request, response);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				closeAll(rs, conn, pstat);
		}
		return flag;
	}

	public boolean add(User user) {
			PreparedStatement pstat=null;
			Connection conn=null;
			int rs=0;
			// 2利用反射，加载数据库驱动
			try {
				// 3 建立连接
				conn = getConnection();
				// 4 建立statement sql语句执行器
				String sql="insert into user(username,password) value(?,?)";
				pstat = conn.prepareStatement(sql);
				// 5 执行sql语句并得到结果
				pstat.setString(1,user.getUsername());
				pstat.setString(2,user.getPassword());
				
				rs = pstat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
					closeAll(null, conn, pstat);
			}
			return rs>0;
	}

	public boolean addPro(String username) {
		boolean flag1 = false;
		PreparedStatement pstat=null;
		Connection conn=null;
		ResultSet rs=null;
		// 2利用反射，加载数据库驱动
		try {
			// 3 建立连接
			conn = getConnection();
			// 4 建立statement sql语句执行器
			String sql="SELECT * FROM user where username=?";
			pstat = conn.prepareStatement(sql);
			// 5 执行sql语句并得到结果
			pstat.setString(1,username);
			
			rs = pstat.executeQuery();
			// 6 对结果集进行处理

			if (rs.next()) {
				// out.print("登录成功");
				// request.getRequestDispatcher("success.jsp").forward(request, response);
				flag1 = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				closeAll(rs, conn, pstat);
		}
		return flag1;
	}
}
