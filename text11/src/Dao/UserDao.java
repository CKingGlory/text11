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
		// 2���÷��䣬�������ݿ�����
		try {
			// 3 ��������
			conn = getConnection();
			// 4 ����statement sql���ִ����
			String sql="SELECT * FROM user where username=? and password=?";
			pstat = conn.prepareStatement(sql);
			// 5 ִ��sql��䲢�õ����
			pstat.setString(1,user.getUsername());
			pstat.setString(2,user.getPassword());
			
			rs = pstat.executeQuery();
			// 6 �Խ�������д���

			if (rs.next()) {
				// out.print("��¼�ɹ�");
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
			// 2���÷��䣬�������ݿ�����
			try {
				// 3 ��������
				conn = getConnection();
				// 4 ����statement sql���ִ����
				String sql="insert into user(username,password) value(?,?)";
				pstat = conn.prepareStatement(sql);
				// 5 ִ��sql��䲢�õ����
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
		// 2���÷��䣬�������ݿ�����
		try {
			// 3 ��������
			conn = getConnection();
			// 4 ����statement sql���ִ����
			String sql="SELECT * FROM user where username=?";
			pstat = conn.prepareStatement(sql);
			// 5 ִ��sql��䲢�õ����
			pstat.setString(1,username);
			
			rs = pstat.executeQuery();
			// 6 �Խ�������д���

			if (rs.next()) {
				// out.print("��¼�ɹ�");
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
