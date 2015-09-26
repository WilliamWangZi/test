package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GeneralDao;
import DAOImpl.teacherDaoImpl;
import JavaBean.teacher;

/**
 * Servlet implementation class registeredservlet
 */
@WebServlet("/registeredservlet")
public class registeredservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public registeredservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("zhuceuname");
		String password=request.getParameter("pw1");
		GeneralDao gDao=new teacherDaoImpl();
		teacher c=new teacher();
		c.setTeacherid(username);
		c.setPassword(password);
		gDao.save(c);
		response.sendRedirect("Login.jsp");
	}

}
