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

import DAO.banjiDao;
import DAOImpl.banjiDaoImpl;
import JavaBean.banji;

/**
 * Servlet implementation class getclassservlet
 */
@WebServlet("/getclassservlet")
public class getclassservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public getclassservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String cname=request.getParameter("cname");
		String username=(String)request.getSession().getAttribute("username");
		banjiDao bDao=new banjiDaoImpl();
		List<banji> baList=bDao.findfromcourse(cname, username);
		request.setAttribute("coursename", cname);
		request.setAttribute("balist", baList);
		System.out.println("tao");
		String forward="SingleScore.jsp";
		request.getRequestDispatcher(forward).forward(request, response);
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out=response.getWriter();
//		List<String> list=new ArrayList<String>();
//		String s=new String();
//		for (int i = 0; i < baList.size(); i++) {
//			list.set(i, baList.get(i).getBname());
//			System.out.println(baList.get(i).getBname());
//			s=s+baList.get(i).getBname()+" ";
//		}
//		System.out.println(s);
//		out.write(s);
//		out.flush();
//		out.close();
	}

}
