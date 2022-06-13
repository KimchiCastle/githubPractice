package action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberLoginAction
 */
@WebServlet("/member/login.do")
public class MemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//m_id=sdsd&m_pwd=sdsd
		
		//1.Encoding 처리
		request.setCharacterEncoding("utf-8");
		
		String m_id 	= request.getParameter("m_id");
		String m_pwd 	= request.getParameter("m_pwd");
		
		MemberVo user = MemberDao.getInstance().selectOne(m_id);
		
		if(user==null) {
			//Session Tracking
			response.sendRedirect("login_form.do?reason=fail_id");
			return;
		}
		if(user.getM_pwd().equals(m_pwd)==false) {
			//Session Tracking
			response.sendRedirect("login_form.do?reason=fail_pwd&m_id"+m_id);
			return;
			
		}
		
		//로그인 정보 세션에 넣기
		HttpSession session = request.getSession();
		session.setAttribute("user", user);		
		
		//메인페이지 이동
		//현재 경로 :			 /member/login.do
		response.sendRedirect("../movie/list.do");
		

	}

}
