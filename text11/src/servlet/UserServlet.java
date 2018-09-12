package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.prism.Image;

import Dao.DepartmentDao;
import Dao.UserDao;
import entity.Department;
import entity.RandomNumber;
import entity.User;
import entity.ValidateCode;
import util.CreateMD5;

public class UserServlet extends HttpServlet {
	private static final String path = "WEB-INF/login/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("showLogin".equals(type)) {
			showLogin(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		} else if ("showRegister".equals(type)) {
			showRegister(request, response);
		} else if ("doRegister".equals(type)) {
			doRegister(request, response);
		}

	}
	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = new UserDao();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			boolean flag1 = userDao.addPro(username);
			if(!flag1) {
			if (password.equals(password2)) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(CreateMD5.getMd5(password));
				boolean flag = userDao.add(user);
				if (flag) {
					response.sendRedirect("user?type=showLogin");
				} else {
					response.sendRedirect("user?type=showRegister");
				}
			} else {
				request.setAttribute("mes", "密码不一致");
				request.getRequestDispatcher(path + "register.jsp").forward(request, response);
			}
			}else {
				request.setAttribute("mes", "用户名已存在");
				request.getRequestDispatcher(path + "register.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path + "register.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {
						name = cookies[i].getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.getRequestDispatcher(path + "login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String random = request.getParameter("random");

			if (random.equals(session.getAttribute("rand"))) {
				UserDao userDao = new UserDao();
				User user = new User();
				user.setUsername(username);
				user.setPassword(CreateMD5.getMd5(password));
				boolean flag = userDao.search(user);

				if (flag) {
					session.setAttribute("user", username);
					Cookie cookie = new Cookie("username", username);
					cookie.setMaxAge(30);
					response.addCookie(cookie);

					// out.print("登录成功");
					// request.getRequestDispatcher("index").forward(request, response);
					response.sendRedirect("index");
				} else {
					// out.print("登录失败");
					request.getRequestDispatcher(path + "login.jsp").forward(request, response);
					// response.sendRedirect("WEB-INF/fail.jsp");
				}
			} else {
				request.setAttribute("mes", "验证码错误");
				request.getRequestDispatcher(path + "login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn = new RandomNumber();

		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			ValidateCode vc = rn.generateImage();
			ServletOutputStream outStream = response.getOutputStream();

			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}