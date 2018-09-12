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

import org.apache.jasper.tagplugins.jstl.core.Out;

import Dao.DepartmentDao;
import Dao.ProjectDao;
import entity.Department;
import entity.Project;
import util.Countant;
import util.Pagination;
import util.page;

public class DepartmentServlet extends HttpServlet{
	private static final String path="WEB-INF/department/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null||"search".equals("type")) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("addDep".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("updateDep".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			}else if ("showManage".equals(type)) {
				showManage(request, response);
			}else if ("showProAdd".equals(type)) {
				showProAdd(request, response);
			}else if ("showProDelete".equals(type)) {
				showProDelete(request, response);
			}else if ("manage2".equals(type)) {
				manage2(request, response);
			}else if ("showProAdd2".equals(type)) {
				showProAdd2(request, response);
			}else if ("showProDelete2".equals(type)) {
				showProDelete2(request, response);
			}else if ("manage3".equals(type)) {
				manage3(request, response);
			}else if ("showProAddBatch2".equals(type)) {
				showProAddBatch2(request, response);
			}else if ("showProDeleteBatch2".equals(type)) {
				showProDeleteBatch2(request, response);
			}else if ("manage4".equals(type)) {
				manage4(request, response);
			}else if ("showProAdd4".equals(type)) {
				showProAdd4(request, response);
			}else if ("showProDelete4".equals(type)) {
				showProDelete4(request, response);
			}else if ("manage5".equals(type)) {
				manage5(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		

	}

	private void showProDelete4(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.deletePro(depId,proId);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
				//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void showProAdd4(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.addPro(depId,proId);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void manage4(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId =Integer.parseInt(request.getParameter("depId"));
			Department dep=new Department();
			DepartmentDao depDao=new DepartmentDao();
			dep=depDao.search(depId);
			
			
			ProjectDao proDao = new ProjectDao();
			
			
			List<Project> list=depDao.searchByDepartment(depId);
			List<Project> noList = proDao.searchByNotDepartment(depId);
			
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path+"manage4.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showProDeleteBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			String proIds=request.getParameter("proIds");
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.deleteBatchPro(depId,proIds);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
				//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void manage3(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId =Integer.parseInt(request.getParameter("depId"));
			Department dep=new Department();
			DepartmentDao depDao=new DepartmentDao();
			dep=depDao.search(depId);
			
			
			ProjectDao proDao = new ProjectDao();
			
			
			List<Project> list=depDao.searchByDepartment(depId);
			List<Project> noList = proDao.searchByNotDepartment(depId);
			
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path+"manage3.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void manage5(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId =Integer.parseInt(request.getParameter("depId"));
			Department dep=new Department();
			DepartmentDao depDao=new DepartmentDao();
			dep=depDao.search(depId);
			
			
			ProjectDao proDao = new ProjectDao();
			
			
			List<Project> list=depDao.searchByDepartment(depId);
			List<Project> noList = proDao.searchByNotDepartment(depId);
			
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path+"manage5.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showProDelete2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.deletePro(depId,proId);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
				//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	private void manage2(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId =Integer.parseInt(request.getParameter("depId"));
			Department dep=new Department();
			DepartmentDao depDao=new DepartmentDao();
			dep=depDao.search(depId);
			
			
			ProjectDao proDao = new ProjectDao();
			
			
			List<Project> list=depDao.searchByDepartment(depId);
			List<Project> noList = proDao.searchByNotDepartment(depId);
			
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path+"manage2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showProDelete(HttpServletRequest request, HttpServletResponse response) {		
		try {
		int depId=Integer.parseInt(request.getParameter("depId"));
		int proId=Integer.parseInt(request.getParameter("proId"));
		DepartmentDao depDao=new DepartmentDao();
		Department dep=depDao.search(depId);
		depDao.deletePro(depId,proId);
		// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showProAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId=Integer.parseInt(request.getParameter("depId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			depDao.addPro(depId,proId);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showProAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			int proId=Integer.parseInt(request.getParameter("proId"));
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.addPro(depId,proId);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showProAddBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId=Integer.parseInt(request.getParameter("depId"));
			String str=request.getParameter("str");
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			boolean flag=depDao.addBatchPro(str);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			//response.sendRedirect("department?type=showManage&name="+dep.getName()+"&id="+depId);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showManage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String  name=request.getParameter("name");
			int id =Integer.parseInt(request.getParameter("id"));
			Department dep=new Department();
			dep.setName(name);
			DepartmentDao depDao=new DepartmentDao();
			dep=depDao.search(id);
			
			
			ProjectDao proDao = new ProjectDao();
			List<Project> list=depDao.search(dep);
			List<Project> proList = proDao.search(dep);
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("proList", proList);
			request.getRequestDispatcher(path+"manage.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//做到这里
			Department depl = new Department();
			String name = request.getParameter("name");
			
			int empcount = -1;
			if(request.getParameter("emp_count")!=null&&!"".equals(request.getParameter("emp_count"))) {
				empcount=Integer.parseInt(request.getParameter("emp_count"));
			}

			depl.setName(name);
			depl.setEmp_count(empcount);
			DepartmentDao depDao = new DepartmentDao();
			
			
			int ye=1;
			if(request.getParameter("ye")!=null) {
				ye=Integer.parseInt(request.getParameter("ye"));
			}
			int count=depDao.searchCount(depl);
			Pagination p=new Pagination(ye,count,Countant.EMP_NUM_IN_PAGE,Countant.EMP_NUM_OF_PAGE);

			List<Department> list = depDao.search(depl,p.getBegin(),Countant.EMP_NUM_IN_PAGE);
			request.setAttribute("p", p);
			request.setAttribute("d", depl);
			request.setAttribute("key", list);
			request.getRequestDispatcher(path+"deps.jsp").forward(request, response);
			
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
			DepartmentDao depDao = new DepartmentDao();
			depDao.delete(id);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("department");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			Department dep=new Department();
			String name = request.getParameter("name");
			dep.setName(name);
			depDao.add(dep);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("department");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path+"addDep.jsp").forward(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			Department dep=depDao.search(id);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path+"updateDep.jsp").forward(request, response);
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
			Department dep=new Department();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			dep.setId(id);
			dep.setName(name);
			
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.update(dep);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("department");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
