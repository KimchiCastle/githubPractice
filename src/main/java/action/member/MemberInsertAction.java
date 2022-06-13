package action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/insert.do")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//1. encoding 설정
		request.setCharacterEncoding("utf-8");
		
		//2. parameter
		String m_name 		= request.getParameter("m_name");
		String m_id	  		= request.getParameter("m_id");
		String m_pwd  		= request.getParameter("m_pwd");
		String m_nickname	= request.getParameter("m_nickname");
		
		String m_ip	= request.getRemoteAddr();
		
		//3. vo로 포장
		MemberVo vo = new MemberVo(m_name, m_id, m_pwd, m_nickname,"일반" ,m_ip);
		
		int res = MemberDao.getInstance().insert(vo);
		
		response.sendRedirect("../movie/list.do");
		
		
	}

}
