package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dao.DepartmentDao;
import Dao.EmployeeDao;
import Dao.UserDao;
import entity.Department;
import entity.Employee;
import util.Countant;
import util.Pagination;
import util.page;

public class EmployeeServlet extends HttpServlet {

	private static final String path = "WEB-INF/employee/";
	static String [] emps ;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");

			String type = request.getParameter("type");
			if (type == null || "search".equals("type")) {//
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("showUpdateBatch1".equals(type)) {
				showUpdateBatch1(request, response);
			} else if ("updateBatch1".equals(type)) {
				updateBatch1(request, response);
			} else if ("showUpdateBatch2".equals(type)) {
				showUpdateBatch2(request, response);
			} else if ("updateBatch2".equals(type)) {
				updateBatch2(request, response);
			} else if ("updateBatch2".equals(type)) {
				updateBatch2(request, response);
			} else if ("showAdd2".equals(type)) {
				showAdd2(request, response);
			} else if ("upload".equals(type)) {
				upload(request, response);
			} else if ("add2".equals(type)) {
				add2(request, response);
			} else if ("remove".equals(type)) {
				remove(request, response);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	private void remove(HttpServletRequest request, HttpServletResponse response) {
		
		String remove=request.getParameter("removePic");
		
		File myFile=new File("C:\\tt\\tu");
		File[] files=myFile.listFiles();
		for(File f :files) {
			if(f.getName().equals(remove)) {
				f.delete();
			}
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {

			Employee empl = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = -1;
			if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));
			}
			int dId = -1;
			if (request.getParameter("d_name") != null && !"".equals(request.getParameter("d_name"))) {
				dId = Integer.parseInt(request.getParameter("d_name"));
			}
			Department dep = new Department();
			dep.setId(dId);
			empl.setDep(dep);
			empl.setName(name);
			empl.setSex(sex);
			empl.setAge(age);

			EmployeeDao empDao = new EmployeeDao();

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = empDao.searchCount(empl);
			Pagination p = new Pagination(ye, count, Countant.EMP_NUM_IN_PAGE, Countant.EMP_NUM_OF_PAGE);

			List<Employee> list = empDao.search(empl, p.getBegin(), Countant.EMP_NUM_IN_PAGE);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.setAttribute("p", p);
			request.setAttribute("e", empl);
			request.setAttribute("key", list);
			request.getRequestDispatcher(path + "emps.jsp").forward(request, response);

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
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.delete(id);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		try {

			String ids = request.getParameter("id");
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.deleteBatch(ids);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			// String path = request.getServletContext().getRealPath("/") + "/pic";
			// System.out.println(path);
			String path = "c:/tt/tu/";

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = new Employee();
			Department dep = new Department();
			String pic = "";
			String name = "";
			String sex = "";
			int age = -1;
			int dId = -1;

			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					pic = uuid.toString() + houzhui;
					File savedFile = new File(path, pic);
					item.write(savedFile);
				} else if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("select")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("age")) {
					age = Integer.parseInt(item.getString());
				} else if (item.getFieldName().equals("d_name")) {
					dId = Integer.parseInt(item.getString());
				}
			}
			dep.setId(dId);
			emp.setDep(dep);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setPicture(pic);
			boolean flag = empDao.add(emp);

			response.sendRedirect("employee");

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			// String path = request.getServletContext().getRealPath("/") + "/pic";
			// System.out.println(path);
			String path = "c:/tt/tu/";

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			String pic = "";

			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					pic = uuid.toString() + houzhui;
					File savedFile = new File(path, pic);
					item.write(savedFile);
				}
			}
			PrintWriter out = response.getWriter();
			out.print(pic);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("select");
			String picture = request.getParameter("picture");
			int age = Integer.parseInt(request.getParameter("age"));
			int dId = Integer.parseInt(request.getParameter("d_name"));
			Department dep = new Department();
			dep.setId(dId);
			emp.setDep(dep);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setPicture(picture);
			empDao.add(emp);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	// try {
	// EmployeeDao empDao = new EmployeeDao();
	// int ye=1;
	// if(request.getParameter("ye")!=null) {
	// ye=Integer.parseInt(request.getParameter("ye"));
	// }
	// int count=empDao.searchCount();
	// Pagination p=new
	// Pagination(ye,count,Countant.EMP_NUM_IN_PAGE,Countant.EMP_NUM_OF_PAGE);
	//
	// List<Employee> list = empDao.search(p.getBegin(),Countant.EMP_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	// request.setAttribute("key", list);
	// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.search(id);
			request.setAttribute("emp", emp);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
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
			Employee emp = new Employee();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			int dId = Integer.parseInt(request.getParameter("d_name"));
			Department dep = new Department();
			dep.setId(dId);
			emp.setDep(dep);
			emp.setId(id);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.update(emp);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			String ids = request.getParameter("ids");

			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			int dId = Integer.parseInt(request.getParameter("d_name"));
			Department dep = new Department();
			dep.setId(dId);
			emp.setDep(dep);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch1(ids, emp);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("emp", list.get(0));
			request.setAttribute("ids", ids);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "updateBatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("list", list);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "updateBatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {

			String emps = request.getParameter("emps");
			String[] array = emps.split("!");
			List<Employee> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temps = array[i].split(",");
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(temps[0]));
				emp.setName(temps[1]);
				emp.setSex(temps[2]);
				emp.setAge(Integer.parseInt(temps[3]));
				Department dep = new Department();
				dep.setId(Integer.parseInt(temps[4]));
				emp.setDep(dep);
				list.add(emp);
			}
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch2(list);
			// request.getRequestDispatcher("WEB-INF/emps.jsp").forward(request, response);
			response.sendRedirect("employee");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
