package servlet;

import java.io.IOException;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GeneralDao;
import DAO.teacherDao;
import DAOImpl.teacherDaoImpl;
import JavaBean.teacher;

/**
 * Servlet implementation class personservlet
 */
@WebServlet("/personservlet")
public class personservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public personservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username=(String)request.getSession().getAttribute("username");
		System.out.println(username);
		teacherDao gDao=new teacherDaoImpl();
		teacher tea=gDao.findId(username);
		request.setAttribute("tea", tea);
		System.out.println((tea.getAcademy()==null));
		System.out.println((tea.getSchool()==null));
		System.out.println("ahah");
		String forward="persondata.jsp";
		request.getRequestDispatcher(forward).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
