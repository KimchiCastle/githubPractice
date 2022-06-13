package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.MemberVo;

public class MemberDao {

	//single-ton : 객체 1개만 생성해서 사용하자!
	//스태틱객체는 무조건 하나만 만들어진다.
	static MemberDao single = null;

	//스태틱은 무조건 스태틱으로만
	public static MemberDao getInstance() {

		//객체가 없으면 생성해라 호출된 한번만 객체를 생성
		if (single == null)
			single = new MemberDao();

		return single;
	}

	//외부에서 객체를 생성하지 못하게 막음
	private MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member";

		try {
			//1.connection 얻어오기
			//				 커낵션 객체생성, DB에게 커낵션얻기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.ResultSet 얻어오기

			rs = pstmt.executeQuery();

			//4.포장(record -> Vo -> list)

			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어온다.

				//Vo로 포장
				MemberVo vo = new MemberVo();
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pwd(rs.getString("m_pwd"));
				vo.setM_nickname(rs.getString("m_nickname"));
				vo.setM_grade(rs.getString("m_grade"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
				
				
				//list에 추가

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//연결(생성) 되었으면 닫아라.(생성 역순으로 닫기)
				if (rs != null)
					rs.close(); // 3
				if (pstmt != null)
					pstmt.close(); // 2
				if (conn != null)
					conn.close(); // 1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	
	
	
	
	public int insert(MemberVo vo) { // 호출한 사용자가 전달한 값
		// TODO Auto-generated method stub
		
		int res = 0;
		
		Connection		   conn  = null;
		PreparedStatement  pstmt = null;
		
		//																  1  2  3  4  5  6
		String sql = "insert into member values(seq_member_m_idx.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
		
		
		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();
			
			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql); // 캐싱
			
			//3.pstmt의 변수처리된 parameter 설정과정
			pstmt.setString(1, vo.getM_name());
			pstmt.setString(2, vo.getM_id());
			pstmt.setString(3, vo.getM_pwd());
			pstmt.setString(4, vo.getM_nickname());
			pstmt.setString(5, vo.getM_grade());
			pstmt.setString(6, vo.getM_ip());
			
			
			
			//4.DML(insert/update/delete)명령 실행, res는 처리된 행수를반환
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			
			try {
				//닫기 (열린 역순)
				if(pstmt != null) pstmt.close(); // 2
				if(conn != null) conn.close();   // 1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//리턴을 0으로 받으면 명령 실패!
		return res;
	}
	
	//ID 체크
	public MemberVo checkId(String m_id) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	parameter(전달 인자) index
		String sql = "select m_id from member where m_id = ?";

		try {
			//1.connection 얻어오기
			//				 커낵션 객체생성, DB에게 커낵션얻기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt셋팅
			pstmt.setString(1, m_id);
			
			
			//4.ResultSet 얻어오기

			rs = pstmt.executeQuery();

			//5.포장(record -> Vo)
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어온다.

				//Vo로 포장
				vo = new MemberVo();
				vo.setM_id(rs.getString("m_id"));
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//연결(생성) 되었으면 닫아라.(생성 역순으로 닫기)
				if (rs != null)
					rs.close(); // 3
				if (pstmt != null)
					pstmt.close(); // 2
				if (conn != null)
					conn.close(); // 1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	//닉네임 체크
	public MemberVo checknickname(String m_nickname) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	parameter(전달 인자) index
		String sql = "select m_nickname from member where m_nickname = ?";

		try {
			//1.connection 얻어오기
			//				 커낵션 객체생성, DB에게 커낵션얻기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt셋팅
			pstmt.setString(1, m_nickname);
			
			//4.ResultSet 얻어오기

			rs = pstmt.executeQuery();

			//5.포장(record -> Vo)
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어온다.

				//Vo로 포장
				vo = new MemberVo();
				vo.setM_nickname(rs.getString("m_nickname"));
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//연결(생성) 되었으면 닫아라.(생성 역순으로 닫기)
				if (rs != null)
					rs.close(); // 3
				if (pstmt != null)
					pstmt.close(); // 2
				if (conn != null)
					conn.close(); // 1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}

	//idx에 해당되는 객체 1건 구하기
	public MemberVo selectOne(String m_id) {

		MemberVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	parameter(전달 인자) index
		String sql = "select * from member where m_id = ?";

		try {
			//1.connection 얻어오기
			//				 커낵션 객체생성, DB에게 커낵션얻기
			conn = DBService.getInstance().getConnection();

			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.pstmt셋팅
			pstmt.setString(1, m_id);
			
			//4.ResultSet 얻어오기

			rs = pstmt.executeQuery();

			//5.포장(record -> Vo)
			if (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어온다.

				//Vo로 포장
				vo = new MemberVo();
				vo.setM_idx(rs.getInt("m_idx"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pwd(rs.getString("m_pwd"));
				vo.setM_nickname(rs.getString("m_nickname"));
				vo.setM_grade(rs.getString("m_grade"));
				vo.setM_ip(rs.getString("m_ip"));
				vo.setM_regdate(rs.getString("m_regdate"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//연결(생성) 되었으면 닫아라.(생성 역순으로 닫기)
				if (rs != null)
					rs.close(); // 3
				if (pstmt != null)
					pstmt.close(); // 2
				if (conn != null)
					conn.close(); // 1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
}
