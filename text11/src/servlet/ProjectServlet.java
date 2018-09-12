package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DepartmentDao;
import Dao.ProjectDao;
import entity.Department;
import entity.Project;
import util.Countant;
import util.Pagination;

public class ProjectServlet extends HttpServlet{
	private static final String path="WEB-INF/project/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null||"search".equals("type")) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("addPro".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("updatePro".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//做到这里
			Project proj=new Project();
			String name = request.getParameter("name");
			proj.setName(name);
			ProjectDao proDao=new ProjectDao();
			
			
			int ye=1;
			if(request.getParameter("ye")!=null) {
				ye=Integer.parseInt(request.getParameter("ye"));
			}
			int count=proDao.searchCount(proj);
			Pagination p=new Pagination(ye,count,Countant.EMP_NUM_IN_PAGE,Countant.EMP_NUM_OF_PAGE);

			List<Project> list = proDao.search(proj,p.getBegin(),Countant.EMP_NUM_IN_PAGE);
			request.setAttribute("p", p);
			request.setAttribute("pr", proj);
			request.setAttribute("key", list);
			request.getRequestDispatcher(path+"pros.jsp").forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao=new ProjectDao();
			proDao.delete(id);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("project");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao proDao=new ProjectDao();
			Project pro=new Project();
			String name = request.getParameter("name");
			pro.setName(name);
			proDao.add(pro);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("project");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path+"addPro.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao=new ProjectDao();
			Project pro=proDao.search(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher(path+"updatePro.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Project pro=new Project();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			pro.setId(id);
			pro.setName(name);
			
			ProjectDao proDao=new ProjectDao();
			boolean flag = proDao.update(pro);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("project");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
