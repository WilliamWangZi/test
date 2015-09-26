package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GeneralDao;
import DAOImpl.teacherDaoImpl;
import JavaBean.teacher;

/**
 * Servlet implementation class alterservlet
 */
@WebServlet("/alterservlet")
public class alterservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public alterservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("uname");
		String password=request.getParameter("pw");
		String phone=request.getParameter("phone");
		String school=request.getParameter("school");
		String academy=request.getParameter("academy");
		String department=request.getParameter("department");
		System.out.println(username+"111");
		String teacherid=(String)request.getSession().getAttribute("username");
		GeneralDao gDao=new teacherDaoImpl();
		teacher c=new teacher();
		c.setTeacherid(teacherid);
		c.setTname(username);
		c.setPassword(password);
		c.setPhone(phone);
		c.setSchool(school);
		c.setAcademy(academy);
		c.setDepartment(department);
		gDao.update(c);
		PrintWriter pWriter=response.getWriter();
		pWriter.write("true");
		pWriter.flush();
		pWriter.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		String username=request.getParameter("uname");
//		String password=request.getParameter("pw");
//		String phone=request.getParameter("phone");
//		String school=request.getParameter("school");
//		String academy=request.getParameter("academy");
//		String department=request.getParameter("department");
//		System.out.println(username+"111");
//		String teacherid=(String)request.getSession().getAttribute("username");
//		GeneralDao gDao=new teacherDaoImpl();
//		teacher c=new teacher();
//		c.setTeacherid(teacherid);
//		c.setTname(username);
//		c.setPassword(password);
//		c.setPhone(phone);
//		c.setSchool(school);
//		c.setAcademy(academy);
//		c.setDepartment(department);
//		gDao.update(c);
//		PrintWriter pWriter=response.getWriter();
//		pWriter.write("true");
//		pWriter.flush();
//		pWriter.close();
	}

}
