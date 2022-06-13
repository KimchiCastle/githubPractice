package action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberCheckAction
 */
@WebServlet("/member/check_id.do")
public class MemberIDCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.parameter받기
		String m_id = request.getParameter("m_id");
		
		MemberVo vo = MemberDao.getInstance().checkId(m_id);
		
//		System.out.println(vo.getM_id());
		
		boolean result = (vo==null);
		
	
		JSONObject json = new JSONObject();
		
		json.put("res", result);
		
		String json_str = json.toJSONString();
		
		
		response.setContentType("text/json; charset=utf-8;");
		response.getWriter().print(json_str);

	}

}

