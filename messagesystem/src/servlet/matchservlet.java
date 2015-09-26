package servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import DAO.GeneralDao;
import DAO.banjiDao;
import DAO.courseDao;
import DAO.studentDao;
import DAO.teacherDao;
import DAOImpl.banjiDaoImpl;
import DAOImpl.courseDaoImpl;
import DAOImpl.studentDaoImpl;
import DAOImpl.teacherDaoImpl;
import JavaBean.banji;
import JavaBean.teacher;

/**
 * Servlet implementation class matchservlet
 */
@WebServlet("/matchservlet")
public class matchservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public matchservlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("2");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("userName");
		String userPW=request.getParameter("userPW");
		System.out.println(userName);
		teacher tea=new teacher();
		teacherDao dao=new teacherDaoImpl();
		tea=dao.findId(userName);
		if(tea!=null&&tea.getPassword().equals(userPW)){
			banjiDao bDao=new banjiDaoImpl();
			List<banji> list=new ArrayList<banji>();
			list=bDao.findID(userName);
			JsonObject json=new JsonObject();
			JsonArray array=new JsonArray();
			for (int i = 0; i < list.size(); i++) {
				JsonObject jsonObject=new JsonObject();
				jsonObject.addProperty("Class", list.get(i).getBname());
				courseDao cDao=new courseDaoImpl();
				List<String> list1=new ArrayList<String>();
				list1=cDao.findfromBID(list.get(i).getBid());
				Gson gson=new Gson();
				JsonElement jsonElement=new JsonArray();
				jsonElement=gson.toJsonTree(list1);
				jsonObject.add("Lesson",jsonElement);
				studentDao sDao=new studentDaoImpl();
				JsonArray arr=new JsonArray();
				arr=sDao.findfromBID(list.get(i).getBid());
				jsonObject.add("Student", arr);
				array.add(jsonObject);
			}
			json.add("fuck", array);
			System.out.format(json.toString());
			PrintWriter output=response.getWriter();
			output.write(json.toString());
			output.flush();
			output.close();
		}else{
			PrintWriter output=response.getWriter();
			output.write("false");
			output.flush();
			output.close();
		}
		
	}

}
