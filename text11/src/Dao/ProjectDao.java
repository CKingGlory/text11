package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;
import util.page;

public class ProjectDao {

	public int searchCount(Project proj) {
		int count=0;
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String where = " where 1=1 ";
			if (proj.getName()!=null&&!proj.getName().equals("")) {
				where += " and name='" + proj.getName() + "'";
			}
			ResultSet rs = stat.executeQuery("select count(*) as count from project "+where);

			// 6 对结果集进行处理
			
			if (rs .next()) {
				count=rs.getInt(1);
			}

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count ;
	}
	
	public List<Project> search(Project proj,int begin,int size) {
		List<Project> list = new ArrayList<Project>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			//
			// 6 对结果集进行处理
			String where = " where 1=1 ";
			if (proj.getName()!=null&&!proj.getName().equals("")) {
				where += " and name='" + proj.getName() + "'";
			}
			String sql = " select * from project "
					+ where+" limit "+begin+","+size;
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {

				Project pro=new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void delete(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "123");
			Statement statement = connection.createStatement();

			String sql = " delete from project where id=" + id;
			statement.executeUpdate(sql);
//			statement.close();
//			sql = " update employee set d_id=null where d_id=" + id;
//			statement.executeUpdate(sql);
//			statement.close();
//			sql = " delete from r_dep_pro where d_id=" + id;
//			statement.executeUpdate(sql);

			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean add(Project pro) {
		boolean flag = false;
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql="insert into project(name) value('" + pro.getName() + "')";
			int rs = stat.executeUpdate(sql);
			// 6 对结果集进行处理

			if (rs > 0) {
				flag = true;
			}

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<Project> searchByNotDepartment(int depId) {

		List<Project> list = new ArrayList<Project>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "SELECT p.id,p.name FROM project as p where p.name not in(SELECT p.name as pName FROM department as d LEFT JOIN r_dep_pro as r ON d.id=r.d_id LEFT JOIN project as p ON r.p_id=p.id where d.id="
					+ depId + ")";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Project search(int id) {
		Project pro=new Project();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select * from project where id=" + id;
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			if (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	public List<Project> search() {
		List<Project> list=new ArrayList<Project>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select * from project";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Project pro=new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean update(Project pro) {
		boolean flag = false;
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "update project set name='" + pro.getName() + "' where id=" + pro.getId();
			int rs = stat.executeUpdate(sql);
			// 6 对结果集进行处理

			if (rs > 0) {
				flag = true;
			}

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public List<Project> search(Department dep) {
		List<Project> proList = new ArrayList<Project>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "SELECT p.id,p.name FROM project as p where p.name not in(SELECT p.name as pName FROM department as d LEFT JOIN r_dep_pro as r ON d.id=r.d_id LEFT JOIN project as p ON r.p_id=p.id where d.name='"+dep.getName()+"')";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Project pro=new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				proList.add(pro);

			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proList;
	}
}
