package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.answerDao;
import DAO.studentDao;
import DAOImpl.answerDaoImpl;
import DAOImpl.studentDaoImpl;
import JavaBean.banji;
import JavaBean.student;

/**
 * Servlet implementation class showstuservlet
 */
@WebServlet("/showstuservlet")
public class showstuservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public showstuservlet() {
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
		String bname=request.getParameter("bname");
		System.out.println(bname);
		
		studentDao sDao=new studentDaoImpl();
		List<student> lStudents=sDao.findfromclass(bname);
		answerDao aDao=new answerDaoImpl();
		
		Map<String, String> sMap=aDao.findStuScore(bname);
		System.out.println(bname);
		String key;
		String value;
		int i=0;
		for(Map.Entry<String, String> entry:sMap.entrySet()){
			key=entry.getKey();
			value=entry.getValue();
			i++;
			sMap.put(key, String.valueOf(i));
		}
		System.out.println(sMap);

		request.setAttribute("banname",bname);
		request.setAttribute("lStudents", lStudents);
		request.setAttribute("sMap", sMap);
		String forward="HomePage1.jsp";
		request.getRequestDispatcher(forward).forward(request, response);
	}

}
