package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.courseDao;
import DAO.knowledgeDao;
import DAOImpl.courseDaoImpl;
import DAOImpl.knowledgeDaoImpl;
import JavaBean.knowledge;

/**
 * Servlet implementation class getKnowledgeservlet
 */
@WebServlet("/getKnowledgeservlet")
public class getKnowledgeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getKnowledgeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String course=request.getParameter("cname");
		String kcontent=request.getParameter("kcontent");
		String kid=request.getParameter("kid");
		System.out.println(course);
		System.out.println(kid);
		knowledgeDao kDao=new knowledgeDaoImpl();
		if(kid!=null){
			kDao.delete(kid);
		}
		if(kcontent!=null){
			courseDao cDao=new courseDaoImpl();
			int cid=cDao.findcid(course);
			knowledge kno=new knowledge();
			kno.setKcontent(kcontent);
			kno.setKcid(cid);
			kDao.save(kno);
		}
		List<knowledge> lKnowledges=kDao.findallKnowledges(course);
		request.setAttribute("lKnowledges", lKnowledges);
		String forward="BeforeScore.jsp";
		request.setAttribute("coursename", course);
		request.getRequestDispatcher(forward).forward(request, response);
	}

}
