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

public class EmployeeDao {

	public List<Employee> search(int begin,int size) {

		List<Employee> list = new ArrayList<Employee>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select id,name,sex,age from employee limit "+begin+","+size;
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);

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

	public boolean add(Employee emp) {
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
			int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id,picture) values('" + emp.getName() + "','"
					+ emp.getSex() + "'," + emp.getAge() + ","+emp.getDep().getId()+",'"+emp.getPicture()+"')");
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

	public Employee search(int id) {
		Employee emp = new Employee();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select e.*,d.name as dName,emp_count from employee as e left join department as d on e.d_id=d.id where e.id=" + id;
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep=new Department();
				dep.setName(rs.getString("dName"));
				emp.setDep(dep);

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
		return emp;
	}

	public List<Employee> search(String ids) {
		List<Employee> list = new ArrayList<>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select e.*,d.name as dName,emp_count from employee as e left join department as d on e.d_id=d.id where e.id in (" + ids + ")";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("e.name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep=new Department();
				dep.setName(rs.getString("dName"));
				emp.setDep(dep);
				list.add(emp);

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

	public boolean update(Employee emp) {
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
			String sql = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age='"
					+ emp.getAge() + "',d_id='" + emp.getDep().getId() + "' where id=" + emp.getId();
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

	public boolean delete(int id) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "123");
			Statement statement = connection.createStatement();

			String sql = " delete from employee where id=" + id;

			int rs = statement.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}

			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "123");
			Statement statement = connection.createStatement();

			String sql = " delete from employee where id in(" + ids + ")";

			int rs = statement.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}

			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateBatch1(String ids, Employee emp) {
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
			String sql = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age='"
					+ emp.getAge() + "',d_id='" + emp.getDep().getId() + "' where id in (" + ids + ")";
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

	public boolean updateBatch2(List<Employee> list) {
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
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				String sql = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age='"
						+ emp.getAge() + "',d_id='" + emp.getDep().getId() + "' where id=" + emp.getId();
				int rs = stat.executeUpdate(sql);
				// 6 对结果集进行处理

				if (rs > 0) {
					flag = true;
				}
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

	public int searchCount() {
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
			ResultSet rs = stat.executeQuery("select count(*) as count from employee");

			// 6 对结果集进行处理
			
			while (rs .next()) {
				count=rs.getInt("count");
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

	public List<Employee> search(Employee emp,int begin,int size) {
		List<Employee> list = new ArrayList<Employee>();
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
			if (emp.getName()!=null&&!emp.getName().equals("")) {
				where += " and e.name='" + emp.getName() + "'";
			}
			if (emp.getSex()!=null&&!emp.getSex().equals("")) {
				where += " and sex='" + emp.getSex() + "'";
			}
			if (emp.getAge() != -1) {
				where += " and age=" + emp.getAge();
			}
			if (emp.getDep().getId() != -1) {
				where += " and d_id=" + emp.getDep().getId();
			}
			String sql = "select e.*,d.name as dName ,emp_count from employee as e left join department as d on e.d_id=d.id" + where+" limit "+begin+","+size;

			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {

				Employee empl = new Employee();
				empl.setId(rs.getInt("id"));
				empl.setName(rs.getString("name"));
				empl.setSex(rs.getString("sex"));
				empl.setAge(rs.getInt("age"));
				empl.setPicture(rs.getString("picture"));
				Department dep=new Department();
				dep.setName(rs.getString("dName"));
				empl.setDep(dep);
				list.add(empl);

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
	
	public int searchCount(Employee emp) {
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
			if (emp.getName()!=null&&!emp.getName().equals("")) {
				where += " and e.name='" + emp.getName() + "'";
			}
			if (emp.getSex()!=null&&!emp.getSex().equals("")) {
				where += " and sex='" + emp.getSex() + "'";
			}
			if (emp.getAge() != -1) {
				where += " and age=" + emp.getAge();
			}
			if (emp.getDep().getId() != -1) {
				where += " and d_id=" + emp.getDep().getId();
			}
			ResultSet rs = stat.executeQuery("select count(*) as count from employee as e left join department as d on e.d_id=d.id"+where);

			// 6 对结果集进行处理
			
			while (rs .next()) {
				count=rs.getInt("count");
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

	public List<Employee> search() {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			// 2利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3 建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123");
			// 4 建立statement sql语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql语句并得到结果
			String sql = "select * from employee";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理

			while (rs.next()) {
				Employee emp=new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				empList.add(emp);

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
		return empList;
	}
}
