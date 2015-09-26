package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.answerDao;
import DAO.banjiDao;
import DAO.examDao;
import DAO.questionDao;
import DAO.studentDao;
import DAOImpl.answerDaoImpl;
import DAOImpl.banjiDaoImpl;
import DAOImpl.examDaoImpl;
import DAOImpl.questionDaoImpl;
import DAOImpl.studentDaoImpl;
import JavaBean.answer;
import JavaBean.exam;
import JavaBean.question;
import JavaBean.student;

/**
 * Servlet implementation class SimpleDataservlet
 */
@WebServlet("/SimpleDataservlet")
public class SimpleDataservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleDataservlet() {
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
		String coursename=request.getParameter("coursename");
		String username=(String)request.getSession().getAttribute("username");
		examDao eDao=new examDaoImpl();
		studentDao sDao=new studentDaoImpl();
		List<exam> eList=eDao.findfromexam(username, coursename);
		System.out.println("elist="+eList.size());
		answerDao aDao=new answerDaoImpl();
		banjiDao bDao=new banjiDaoImpl();
		questionDao qDao=new questionDaoImpl();
		request.setAttribute("banname1", bname);
		request.setAttribute("coursename", coursename);
		for (int i = 0; i < eList.size(); i++) {
			if(!aDao.findeveryScore(bname, eList.get(i).getEid()).isEmpty()){
				System.out.println(aDao.findeveryScore(bname, eList.get(i).getEid()));
				List<answer> aList=aDao.findeveryScore(bname, eList.get(i).getEid());
				List<student> sList=sDao.findfromclass(bname);
				List<Double> dlDoubles=aDao.findavgScore(bname, eList.get(i).getEid());
				List<Integer> integers=aDao.findsumScore(bname, eList.get(i).getEid());
				List<question> qList=qDao.findquestions(eList.get(i).getEid());
				int k=bDao.findclassnum(bname);
				request.setAttribute("qList", qList);
				request.setAttribute("integers", integers);
				request.setAttribute("dlDoubles",dlDoubles);
				request.setAttribute("num", k);
				request.setAttribute("alist", aList);
				request.setAttribute("slist", sList);
				String forward="SingleScore.jsp";
				System.out.println("1891");
				request.getRequestDispatcher(forward).forward(request, response);
				break;
			}else{
				String forward="SingleScore.jsp";
				System.out.println("1892");
				request.setAttribute("tip", "该班级暂无该科成绩");
				request.getRequestDispatcher(forward).forward(request, response);
				break;
			}
		}
		if(eList.size()==0){
			String forward="SingleScore.jsp";
			System.out.println("1892");
			request.setAttribute("tip", "该班级暂无该科成绩");
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

}
