package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GeneralDao;
import DAOImpl.teacherDaoImpl;
import JavaBean.teacher;

/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public registerservlet() {
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
		String username=request.getParameter("username");
		System.out.println(username+"12324546");
		List<teacher> list=new ArrayList<teacher>();
		GeneralDao dao=new teacherDaoImpl();
		list=dao.find();
		int i;
		for (i = 0; i < list.size(); i++) {
			if(list.get(i).getTeacherid().equals(username)){
				System.out.println(list.get(i).getTeacherid()+list.get(i).getPassword());
				PrintWriter out=response.getWriter();
				out.write("true");
				out.flush();
				out.close();
				break;
			}
		}
	}

}
