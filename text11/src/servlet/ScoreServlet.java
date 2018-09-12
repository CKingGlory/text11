package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DepartmentDao;
import Dao.EmployeeDao;
import Dao.ProjectDao;
import Dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import util.Countant;
import util.Pagination;

public class ScoreServlet extends HttpServlet {
	private static final String path="WEB-INF/score/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "search".equals("type")) {
				search(request, response);
			} else if ("save".equals(type)) {
				save(request, response);
			} else if ("search1".equals(type)) {
				search1(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	private void search1(HttpServletRequest request, HttpServletResponse response) {
		try {
			Score scos = new Score();

			ScoreDao scoDao = new ScoreDao();
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = scoDao.searchCount();

			Pagination p = new Pagination(ye, count, Countant.EMP_NUM_IN_PAGE, Countant.EMP_NUM_OF_PAGE);
			List<Score> list = scoDao.search(p.getBegin(), Countant.EMP_NUM_IN_PAGE);

			request.setAttribute("p", p);
			request.setAttribute("s", scos);
			request.setAttribute("key", list);
			request.getRequestDispatcher(path+"score.jsp").forward(request, response);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		try {

			String values = request.getParameter("values");
			String[] array = values.split("!");
			List<Score> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temps = array[i].split(",");
				Score sco=new Score();
				sco.setId(Integer.parseInt(temps[0]));
				sco.setValue(Integer.parseInt(temps[1]));
				list.add(sco);
			}
			ScoreDao scoDao=new ScoreDao();
			boolean flag = scoDao.save(list);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("score");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			Score scos = new Score();
			int value = -1;
			if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
				value = Integer.parseInt(request.getParameter("value"));
			}
			String grade = request.getParameter("grade");
			scos.setValue(value);
			scos.setGrade(grade);

			Employee emp = new Employee();
			String empName = request.getParameter("emp_name");
			emp.setName(empName);
			scos.setEmp(emp);

			int dId = -1;
			if (request.getParameter("dep_id") != null && !"".equals(request.getParameter("dep_id"))) {
				dId = Integer.parseInt(request.getParameter("dep_id"));
			}
			Department dep = new Department();
			dep.setId(dId);
			scos.setDep(dep);

			int pId = -1;
			if (request.getParameter("pro_id") != null && !"".equals(request.getParameter("pro_id"))) {
				pId = Integer.parseInt(request.getParameter("pro_id"));
			}
			Project pro = new Project();
			pro.setId(pId);
			scos.setPro(pro);

			ScoreDao scoDao = new ScoreDao();
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = scoDao.searchCount(scos);

			Pagination p = new Pagination(ye, count, Countant.EMP_NUM_IN_PAGE, Countant.EMP_NUM_OF_PAGE);
			List<Score> list = scoDao.search(scos, p.getBegin(), Countant.EMP_NUM_IN_PAGE);

			EmployeeDao empDao = new EmployeeDao();
			List<Employee> empList = empDao.search();
			request.setAttribute("empList", empList);

			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);

			ProjectDao proDao = new ProjectDao();
			List<Project> proList = proDao.search();
			request.setAttribute("proList", proList);

			request.setAttribute("p", p);
			request.setAttribute("s", scos);
			request.setAttribute("key", list);
			request.getRequestDispatcher(path+"scos.jsp").forward(request, response);

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
