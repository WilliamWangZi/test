package DataDeal;

import java.util.ArrayList;
import java.util.List;

import DAO.answerDao;
import DAO.banjiDao;
import DAO.courseDao;
import DAO.examDao;
import DAO.questionDao;
import DAO.teacherDao;
import DAOImpl.answerDaoImpl;
import DAOImpl.banjiDaoImpl;
import DAOImpl.courseDaoImpl;
import DAOImpl.examDaoImpl;
import DAOImpl.questionDaoImpl;
import DAOImpl.teacherDaoImpl;
import JavaBean.answer;
import JavaBean.exam;
import JavaBean.question;
import JavaBean.teacher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.mail.util.QDecoderStream;

public class datachange {
	public static String jsontoDBexam(String s,JsonArray jsonArray){
		System.out.println(jsonArray.size());
		for(int j=0;j<jsonArray.size();j++){
			exam e=new exam();
			examDao eDao=new examDaoImpl();
			examDao eDao1=new examDaoImpl();
			teacherDao tDao=new teacherDaoImpl();
			courseDao cDao=new courseDaoImpl();
			teacher teacherperson=tDao.findId(s);
			int cid=cDao.findcid(jsonArray.get(j).getAsJsonObject().get("lesson").getAsString());
			System.out.println(jsonArray.get(j).getAsJsonObject().get("lesson").getAsString()+":"+cid);
			int examid=eDao.getid(jsonArray.get(j).getAsJsonObject().get("time").getAsString(),cid,teacherperson.getTid());
			if(examid!=0){
				JsonArray jArray=jsonArray.get(j).getAsJsonObject().get("Type&Score").getAsJsonArray();
				questionDao qDao=new questionDaoImpl();
				for (int i = 0; i < jArray.size(); i++) {
					question q=new question();
					q.setQtype(jArray.get(i).getAsJsonObject().get("type").getAsString());
					q.setQvalue(Integer.valueOf(jArray.get(i).getAsJsonObject().get("score").getAsString()).intValue());
					q.setQbeid(i+1);
					q.setQeid(examid);
					if(qDao.findqid(q)==0){
						qDao.save(q);
					}else{
						qDao.update(q);
					}
				}
				qDao.deletemore(examid,jArray.size());
			}else {
				System.out.print("examid="+examid+"    ");
				JsonArray jArray=jsonArray.get(j).getAsJsonObject().get("Type&Score").getAsJsonArray();
				e.setQuestionnum(jArray.size());
				e.setExamname(jsonArray.get(j).getAsJsonObject().get("time").getAsString());
				e.setEcid(cDao.findcid(jsonArray.get(j).getAsJsonObject().get("lesson").getAsString()));
				e.setEtid(teacherperson.getTid());
				eDao.save(e);
				String abString=jsonArray.get(j).getAsJsonObject().get("time").getAsString();			
				int eid=eDao.getid(abString,cid,teacherperson.getTid());
				questionDao qDao=new questionDaoImpl();
				for (int i = 0; i < jArray.size(); i++) {
					question q=new question();
					q.setQtype(jArray.get(i).getAsJsonObject().get("type").getAsString());
					q.setQvalue(Integer.valueOf(jArray.get(i).getAsJsonObject().get("score").getAsString()).intValue());
					q.setQbeid(i+1);
					q.setQeid(eid);
					qDao.save(q);
				}
			}
		}
		return "true";
	}
	public static String jsontoDBscore(String s,JsonArray jsonArray) {
		for (int i = 0; i < jsonArray.size(); i++) {
			examDao eDao=new examDaoImpl();
			banjiDao bDao=new banjiDaoImpl();
			teacherDao tDao=new teacherDaoImpl();
			courseDao cDao=new courseDaoImpl();
			teacher tea=tDao.findId(s);
			String coursename=jsonArray.get(i).getAsJsonObject().get("lesson").getAsString();
			int cid=cDao.findcid(coursename);
			int examid=eDao.getid(jsonArray.get(i).getAsJsonObject().get("time").getAsString(),cid,tea.getTid());
			JsonArray jArray=jsonArray.get(i).getAsJsonObject().get("Stscore").getAsJsonArray();
			System.out.println(examid);
			answerDao aDao=new answerDaoImpl();
			for (int j = 0; j < jArray.size(); j++) {
				int ques_no=Integer.valueOf(jArray.get(j).getAsJsonObject().get("ques_no").getAsString()).intValue();
				answer an=new answer();
				an.setAbeid(ques_no);
				an.setAeid(examid);
				an.setAstuid(jArray.get(j).getAsJsonObject().get("stid").getAsString());
				an.setAbid(bDao.findbanjiid(jsonArray.get(i).getAsJsonObject().get("cla").getAsString()));
				an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
				if(aDao.getid(ques_no,examid,jArray.get(j).getAsJsonObject().get("stid").getAsString())==0){
					aDao.save(an);
				}else{
					aDao.update(an);
				}
			}
//			System.out.println("num="+jArray.size()+"  banjinum"+bDao.findclassnum(bname));
			System.out.println("bnum="+jArray.size()/bDao.findclassnum(jsonArray.get(i).getAsJsonObject().get("cla").getAsString()));
			aDao.deletemore(examid, eDao.findque_num(examid));
		}
		return "true";
	}
	public static String jsontoupdate(String s,JsonArray jsonArray){
		for (int i = 0; i < jsonArray.size(); i++) {
			examDao eDao=new examDaoImpl();
			banjiDao bDao=new banjiDaoImpl();
			teacherDao tDao=new teacherDaoImpl();
			courseDao cDao=new courseDaoImpl();
			teacher tea=tDao.findId(s);
			int cid=cDao.findcid(jsonArray.get(i).getAsJsonObject().get("lesson").getAsString());
			int examid=eDao.getid(jsonArray.get(i).getAsJsonObject().get("time").getAsString(),cid,tea.getTid());
				JsonArray jArray=jsonArray.get(i).getAsJsonObject().get("Stscore").getAsJsonArray();
				for (int j = 0; j < jArray.size(); j++) {
					int ques_no=Integer.valueOf(jArray.get(j).getAsJsonObject().get("ques_no").getAsString()).intValue();
					answerDao aDao=new answerDaoImpl();
					answer an=new answer();
					an.setAbeid(ques_no);
					an.setAeid(examid);
					an.setAstuid(jArray.get(j).getAsJsonObject().get("stid").getAsString());
					an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
					aDao.update(an);					
				}
		}
		return "true";
//		for (int i = 0; i < jsonArray.size(); i++) {
//			examDao eDao=new examDaoImpl();
//			banjiDao bDao=new banjiDaoImpl();
//			teacherDao tDao=new teacherDaoImpl();
//			courseDao cDao=new courseDaoImpl();
//			teacher tea=tDao.findId(s);
//			int cid=cDao.findcid(jsonArray.get(i).getAsJsonObject().get("lesson").getAsString());
//			int examid=eDao.getid(jsonArray.get(i).getAsJsonObject().get("time").getAsString(),cid,tea.getTid());
//			if(jsonArray.get(i).getAsJsonObject().has("Update")){
//				JsonArray jArray=jsonArray.get(i).getAsJsonObject().get("Stscore").getAsJsonArray();
//				for (int j = 0; j < jArray.size(); j++) {
//					int ques_no=Integer.valueOf(jArray.get(j).getAsJsonObject().get("ques_no").getAsString()).intValue();
//					answerDao aDao=new answerDaoImpl();
//					answer an=new answer();
//					an.setAbeid(ques_no);
//					an.setAeid(examid);
//					an.setAstuid(jArray.get(j).getAsJsonObject().get("stid").getAsString());
//					an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
//					aDao.update(an);					
//				}
//			}else if(jsonArray.get(i).getAsJsonObject().has("Stscore")){
//				JsonArray jArray=jsonArray.get(i).getAsJsonObject().get("Stscore").getAsJsonArray();
//				for (int j = 0; j < jArray.size(); j++) {
//					int ques_no=Integer.valueOf(jArray.get(j).getAsJsonObject().get("ques_no").getAsString()).intValue();
//					answerDao aDao=new answerDaoImpl();
//					if(aDao.getid(ques_no,examid,jArray.get(j).getAsJsonObject().get("stid").getAsString())==0){
//						answer an=new answer();
//						an.setAbeid(ques_no);
//						an.setAeid(examid);
//						an.setAstuid(jArray.get(j).getAsJsonObject().get("stid").getAsString());
//						an.setAbid(bDao.findbanjiid(jsonArray.get(i).getAsJsonObject().get("cla").getAsString()));
//						an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
//						aDao.save(an);
//					}
//				}
//			}
//		}
//		return "true";
//		for (int i = 0; i < jsonArray.size(); i++) {
//			examDao eDao=new examDaoImpl();
//			banjiDao bDao=new banjiDaoImpl();
//			
//			teacherDao tDao=new teacherDaoImpl();
//			courseDao cDao=new courseDaoImpl();
//			teacher tea=tDao.findId(s);
//			int cid=cDao.findcid(jsonArray.get(i).getAsJsonObject().get("lesson").getAsString());
//			int examid=eDao.getid(jsonArray.get(i).getAsJsonObject().get("time").getAsString(),cid,tea.getTid());
//				JsonArray jArray=jsonArray.get(i).getAsJsonObject().get("Stscore").getAsJsonArray();
//				for (int j = 0; j < jArray.size(); j++) {
//					answerDao aDao=new answerDaoImpl();
//					int ques_no=Integer.valueOf(jArray.get(j).getAsJsonObject().get("ques_no").getAsString()).intValue();
//					int each_score=Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue();
//					answer an1=aDao.getanswer(ques_no,examid,jArray.get(j).getAsJsonObject().get("stid").getAsString());
//					if(an1.getAvalue()==each_score){
//						continue;
//					}else{
//						answer an=new answer();
//						an.setAbeid(ques_no);
//						an.setAeid(examid);
//						an.setAstuid(jArray.get(j).getAsJsonObject().get("stid").getAsString());
//						if(an1.getAid()==0){
//							an.setAbid(bDao.findbanjiid(jsonArray.get(i).getAsJsonObject().get("cla").getAsString()));
//							an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
//							aDao.save(an);
//						}else {
//							an.setAvalue(Integer.valueOf(jArray.get(j).getAsJsonObject().get("each_score").getAsString()).intValue());
//							aDao.update(an);
//						}
//					}
//				}
//		}
//		return "true";
	}
	public JsonObject composejson(String username) {
		banjiDao bDao=new banjiDaoImpl();
		examDao eDao=new examDaoImpl();
		courseDao cDao=new courseDaoImpl();
		answerDao aDao=new answerDaoImpl();
		JsonObject jsonObject=new JsonObject();
		List<exam> list=new ArrayList<exam>();
		list=eDao.findforexam(username);
		JsonArray arr=new JsonArray();
		System.out.println(list.get(0).getEcid()+"   "+cDao.findcourse(list.get(0).getEcid()));
		for (int i = 0; i < list.size(); i++) {
			JsonObject json=new JsonObject();
			
			json.addProperty("lesson", cDao.findcoursename(list.get(i).getEcid()));
			json.addProperty("time",list.get(i).getExamname());
			List<Integer> list1=aDao.getbid(list.get(i).getEid());
			for (int j = 0; j < list1.size(); j++) {
				String banji= bDao.findbanjiname(list1.get(j).intValue());
				json.addProperty("cla",banji);
				JsonArray jsonArray=new JsonArray();
				List<answer> list2=aDao.findeveryScore(banji, list.get(i).getEid());
				for (int k = 0; k <list2.size(); k++) {
					JsonObject jsonsubObject=new JsonObject();
					jsonsubObject.addProperty("ques_no", list2.get(k).getAbeid());
					jsonsubObject.addProperty("stid", list2.get(k).getAstuid());
					jsonsubObject.addProperty("each_score", list2.get(k).getAvalue());
					jsonArray.add(jsonsubObject);
//					System.out.println(jsonArray);
				}
				json.add("Stscore", jsonArray);
//				System.out.println(json);
			}
			arr.add(json);
		}
		jsonObject.add("Scores", arr);
		return jsonObject;
	}
	public JsonObject composeexam(String username){
		banjiDao bDao=new banjiDaoImpl();
		examDao eDao=new examDaoImpl();
		courseDao cDao=new courseDaoImpl();
		questionDao qDao=new questionDaoImpl();
		answerDao aDao=new answerDaoImpl();
		JsonObject jsonObject=new JsonObject();
		List<exam> list=new ArrayList<exam>();
		list=eDao.findforexam(username);
		JsonArray arr=new JsonArray();
		System.out.println(list.get(0).getEcid()+"   "+cDao.findcourse(list.get(0).getEcid()));
		for (int i = 0; i < list.size(); i++) {
			JsonObject json=new JsonObject();
			json.addProperty("lesson", cDao.findcoursename(list.get(i).getEcid()));
			json.addProperty("time",list.get(i).getExamname());
			List<question> list1=qDao.findquestions(list.get(i).getEid());
				JsonArray jsonArray=new JsonArray();
			for (int j = 0; j < list1.size(); j++) {
				JsonObject jsonsubObject=new JsonObject();
				jsonsubObject.addProperty("que_no", list1.get(j).getQbeid());
				jsonsubObject.addProperty("type", list1.get(j).getQtype());
				jsonsubObject.addProperty("score",list1.get(j).getQvalue());
				jsonArray.add(jsonsubObject);
			}
			json.add("Type&Score", jsonArray);
			arr.add(json);
		}
		jsonObject.add("type_score", arr);
		return jsonObject;
		
	}
}
