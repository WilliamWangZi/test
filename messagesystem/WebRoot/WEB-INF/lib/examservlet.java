package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataDeal.datachange;
@WebServlet("/examservlet")
public class examservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public examservlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BufferedReader br=request.getReader();
		StringBuffer buffer=new StringBuffer();
		String s=null;
		while ((s=br.readLine()) != null) {
			buffer.append(s);
		}
		System.out.println(buffer.toString());
		JsonParser jsonParser=new JsonParser();
		JsonObject jsonObject=(JsonObject) jsonParser.parse(buffer.toString());
		if(jsonObject.has("type_score")){
////			datachange data=new datachange();
			String tip=datachange.jsontoDBexam(jsonObject.get("UserId").getAsString(),jsonObject.get("type_score").getAsJsonArray());
			System.out.println("еп╤о"+tip);
		}
		if(jsonObject.has("Scores")){
			String tip=datachange.jsontoDBscore(jsonObject.get("UserId").getAsString(),jsonObject.get("Scores").getAsJsonArray());
			System.out.println("еп╤о"+tip);
		}
		if (jsonObject.has("Updata")) {
			String tip=datachange.jsontoDBscore(jsonObject.get("UserId").getAsString(),jsonObject.get("Updata").getAsJsonArray());
			System.out.println("еп╤о"+tip);
		}
//		if (jsonObject.has("Updata")) {
//			String tip=datachange.jsontoupdate(jsonObject.get("UserId").getAsString(),jsonObject.get("Updata").getAsJsonArray());
//			System.out.println("еп╤о"+tip);
//		}
		PrintWriter output=response.getWriter();
		output.write("yes");
		output.flush();
		output.close();
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
