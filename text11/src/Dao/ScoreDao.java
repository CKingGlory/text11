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
import entity.Score;

public class ScoreDao {
	
	
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
			ResultSet rs = stat.executeQuery("select count(*) as count from v_emp_sc ");

			// 6 对结果集进行处理
			
			while (rs .next()) {
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

	public List<Score> search(int begin, int size) {
		List<Score> list = new ArrayList<Score>();
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
			String sql = "select e_id,e_name,d_name,p_name,value,grade,sc_id from v_emp_sc limit "+begin+","+size;

			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {

				Score sc = new Score();
				sc.setId(rs.getInt("sc_id"));
				sc.setValue(rs.getInt("value"));
				sc.setGrade(rs.getString("grade"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				sc.setEmp(emp);
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				sc.setDep(dep);
				Project pro = new Project();
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				list.add(sc);

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

	public int searchCount(Score scos) {
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
			if (scos.getEmp().getName()!=null&&!scos.getEmp().getName().equals("")) {
				where += " and e_name='" + scos.getEmp().getName() + "'";
			}
			if (scos.getDep().getId() != -1) {
				where += " and d_id=" + scos.getDep().getId();
			}
			if (scos.getPro().getId() != -1) {
				where += " and p_id=" + scos.getPro().getId();
			}
			if (scos.getValue()!= -1) {
				where += " and value=" + scos.getValue();
			}
			if (scos.getGrade()!=null&&!scos.getGrade().equals("")) {
				where += " and grade='" + scos.getGrade() + "'";
			}
			ResultSet rs = stat.executeQuery("select count(*) as count from v_emp_sc "+where);

			// 6 对结果集进行处理
			
			while (rs .next()) {
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

	public List<Score> search(Score scos, int begin, int size) {
		List<Score> list = new ArrayList<Score>();
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
			if (scos.getEmp().getName()!=null&&!scos.getEmp().getName().equals("")) {
				where += " and e_name='" + scos.getEmp().getName() + "'";
			}
			if (scos.getDep().getId() != -1) {
				where += " and d_id=" + scos.getDep().getId();
			}
			if (scos.getPro().getId() != -1) {
				where += " and p_id=" + scos.getPro().getId();
			}
			if (scos.getValue()!= -1) {
				where += " and value=" + scos.getValue();
			}
			if (scos.getGrade()!=null&&!scos.getGrade().equals("")) {
				where += " and grade='" + scos.getGrade() + "'";
			}
			String sql = "select e_id,e_name,d_name,p_name,value,grade,sc_id from v_emp_sc " + where+" limit "+begin+","+size;

			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {

				Score sc = new Score();
				sc.setId(rs.getInt("sc_id"));
				sc.setValue(rs.getInt("value"));
				sc.setGrade(rs.getString("grade"));
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				sc.setEmp(emp);
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				sc.setDep(dep);
				Project pro = new Project();
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				list.add(sc);

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

	public boolean save(List<Score> list) {
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
				Score sco = list.get(i);
				String sql = "update v_emp_sc set value='"+sco.getValue()+"' where sc_id= "+sco.getId();
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

	public List<Project> search(int depId) {
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
			String sql = "SELECT p_name FROM v_dep_pro WHERE d_id="+depId;

			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
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

}
