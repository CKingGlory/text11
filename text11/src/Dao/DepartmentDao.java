package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;
import entity.Project;

public class DepartmentDao {
	
	public int searchCount(Department depl) {
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
			if (depl.getName()!=null&&!depl.getName().equals("")) {
				where += " and name='" + depl.getName() + "'";
			}
			if (depl.getEmp_count() != -1) {
				where += " and ifnull(emp_count,0)=" + depl.getEmp_count();
			}
			ResultSet rs = stat.executeQuery("select count(*) as count from department "+where);

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
	
	public List<Department> search(Department depl,int begin,int size) {
		List<Department> list = new ArrayList<Department>();
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
			if (depl.getName()!=null&&!depl.getName().equals("")) {
				where += " and name='" + depl.getName() + "'";
			}
			if (depl.getEmp_count() != -1) {
				where += " and ifnull(emp_count,0)= " + depl.getEmp_count();
			}
			String sql = " select * from department "
					+ where+" limit "+begin+","+size;
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {

				Department dep=new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);

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
	
	public boolean add(Department dep) {
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
			int rs = stat.executeUpdate("insert into department(name) values('" + dep.getName() + "')");
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
	
	public boolean update(Department dep) {
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
			String sql = "update department set name='" + dep.getName() + "' where id=" + dep.getId();
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
	
	public void delete(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "123");
			Statement statement = connection.createStatement();

			String sql = " delete from department where id=" + id;
			statement.executeUpdate(sql);
			statement.close();
			sql = " update employee set d_id=null where d_id=" + id;
			statement.executeUpdate(sql);
			statement.close();
			sql = " delete from r_dep_pro where d_id=" + id;
			statement.executeUpdate(sql);

			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Project> searchByDepartment(int depId) {

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
			ResultSet rs = stat.executeQuery("SELECT * FROM v_dep_pro where d_id=" + depId + "");
			// 6 对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
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
	
	public Department search(int id) {
		Department dep=new Department();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select * from department where id=" + id;
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			if (rs.next()) {
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));

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
		return dep;
	}

	public List<Department> search() {
		List<Department> depList = new ArrayList<Department>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select * from department";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Department dep=new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
				depList.add(dep);

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
		return depList;
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
			String sql = "SELECT p.id,p.name as pName FROM department as d LEFT JOIN r_dep_pro as r ON d.id=r.d_id LEFT JOIN project as p ON r.p_id=p.id where d.name='"+dep.getName()+"'";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p.id"));
				pro.setName(rs.getString("pName"));
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

	public boolean addPro(int depId, int proId) {
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
			int rs = stat.executeUpdate("insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ")");
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

	public boolean deletePro(int depId, int proId) {
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
			int rs = stat.executeUpdate("delete from r_dep_pro where d_id=" + depId + " and p_id=" + proId + "");
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

	public boolean addBatchPro(String str) {
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
			String sql="insert into r_dep_pro(d_id,p_id) values"+str;
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

	public boolean deleteBatchPro(int depId, String proIds) {
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
			int rs = stat.executeUpdate("delete from r_dep_pro where d_id=" + depId + " and p_id in (" + proIds + ")");
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

}
