package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javassist.expr.NewArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.answerDao;
import DAO.banjiDao;
import DAO.courseDao;
import DAO.studentDao;
import DAOImpl.answerDaoImpl;
import DAOImpl.banjiDaoImpl;
import DAOImpl.courseDaoImpl;
import DAOImpl.studentDaoImpl;
import JavaBean.student;

/**
 * Servlet implementation class allscoreservlet
 */
@WebServlet("/allscoreservlet")
public class allscoreservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allscoreservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bname=request.getParameter("bname");
		System.out.println(bname);
		studentDao sDao=new studentDaoImpl();
		answerDao aDao=new answerDaoImpl();
		courseDao cDao=new courseDaoImpl();
		banjiDao bDao=new banjiDaoImpl();
		List<student> slList=sDao.findfromclass(bname);
		List<Integer> aList=aDao.findexams(bname);
		if(aList.size()!=0){
			List<String> courList=new ArrayList<String>();
			int k=bDao.findclassnum(bname);
			int m=0;
			List<Integer> integer=new ArrayList<Integer>();
			Map<String, Map<String, Integer>> ascoreMap=new HashMap<String, Map<String, Integer>>();
			for (int i = 0; i < aList.size(); i++) {
				Map<String, Integer> ascoreList=aDao.findsumScore1(bname, aList.get(i));
				String courseString=cDao.findcourse(aList.get(i));
				courList.add(courseString);
				ascoreMap.put(courseString, ascoreList);
				for (Map.Entry entry : ascoreList.entrySet()){
					int z=Integer.parseInt(entry.getValue().toString());
					if (z>=60) {
						m++;
					}
				}
				m=m*100/k;
				integer.add(m);
				m=0;
			}
			request.setAttribute("integer", integer);
			request.setAttribute("slList", slList);
			request.setAttribute("courList", courList);
			request.setAttribute("ascoreMap", ascoreMap);
			request.setAttribute("banname", bname);
			String forward="AllScore.jsp";
			request.getRequestDispatcher(forward).forward(request, response);
		}else{
			request.setAttribute("banname", bname);
			request.setAttribute("tip", "该班级暂无成绩");
			String forward="AllScore.jsp";
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

}
