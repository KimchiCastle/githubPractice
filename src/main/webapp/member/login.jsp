<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script type="text/javascript">
	
	function send(f){
		
		var m_id  = f.m_id.value.trim();
		var m_pwd = f.m_pwd.value.trim();
		
		if (m_id == '') {
			Swal.fire({
				 icon	: 'error',
				  title : 'ID를 입력하세요.',
				  didClose: () => {
						f.m_id.value = '';
						f.m_id.focus();
				  }
			})
			
			return;
		}
	
		
		if (m_pwd == '') {
			Swal.fire({
				 icon	: 'error',
				  title : '비밀번호를 입력하세요.',
				  didClose: () => {
						f.m_pwd.value = '';
						f.m_pwd.focus();
				  }
			})
			
			return;
		}
		
		f.action="login.do";
		f.submit();
		
	}
	


</script>

<script type="text/javascript">

	$(document).ready(function(){
		
		//0.1초후에 showMessage함수 호출
		setTimeout(showMessage, 100);
		
	})
	
	function showMessage(){
		
		// /member/login_form.do?reason=fail_id
		//jquery와 el 모두 $를 쓰기때문에
		//데이터를 나타낼땐 헷갈리지 않게 ""를 쓴다
		if("${ param.reason eq 'fail_id'}" == "true"){
			alert('아이디가 틀렸습니다.');
			return;
		}
		
		if("${ param.reason eq 'fail_pwd'}" == "true"){
			alert('비밀번호가 틀렸습니다.');
			return;
		}
		if("${ param.reason eq 'session_timeout'}" == "true"){
			alert('로그아웃 되었습니다.');
			return;
		}
		
		
	}
</script>



</head>
<body>

<form>
	<div style="margin-top: 100px;">
		<table style="margin: auto;">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="m_id" id="m_id"></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="m_pwd"></td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="button" value="로그인" onclick="send(this.form)">
					<input type="button" value="취소" onclick="location.href='../index.html'">
				</th>
			</tr>
			
		</table>
	</div>
</form>

</body>
</html>