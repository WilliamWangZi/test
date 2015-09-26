package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GeneralDao;
import DAO.banjiDao;
import DAO.courseDao;
import DAOImpl.banjiDaoImpl;
import DAOImpl.courseDaoImpl;
import DAOImpl.teacherDaoImpl;
import JavaBean.banji;
import JavaBean.course;
import JavaBean.teacher;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public void init()throws ServletException{
		System.out.println("haha");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username+password);
		int i;
		List<teacher> list=new ArrayList<teacher>();
		GeneralDao dao=new teacherDaoImpl();
		list=dao.find();
		for (i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getTeacherid()+list.get(i).getPassword());
			if(list.get(i).getTeacherid().equals(username)){
				if (list.get(i).getPassword().equals(password)) {
					String forward="HomePage1.jsp";
					System.out.println("123");
					List<banji> bList=new ArrayList<banji>();
					banjiDao bDao=new banjiDaoImpl();
					courseDao cDao=new courseDaoImpl();
					bList=bDao.findID(username);
					List<course> clist=cDao.findfromteaid(username);
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("blist", bList);
					request.getSession().setAttribute("clist", clist);
					request.getRequestDispatcher(forward).forward(request, response);
					break;
				}
			}
		}
		if(i>=list.size()){
			response.sendRedirect("Login.jsp?pan=1");
		}
	}

}
