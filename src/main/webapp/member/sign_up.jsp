<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- sweetAlert2 CDN -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">

	#box{
		margin-top: 100px;
	}
	#t1{
		margin: auto;
	}

</style>



<script type="text/javascript">
	
	
	var korRegCheck = /[ㄱ-ㅎㅏ-ㅣ가-힣]/;
	
	function sign_up(f) {
		
		var m_name = f.m_name.value.trim();
		var m_id = f.m_id.value.trim();
		var m_pwd = f.m_pwd.value.trim();
		var m_nickname = f.m_nickname.value.trim();
		
		
		
		
		
		//이름 체크
		if (m_name == '') {
			Swal.fire({
				 icon	: 'error',
				  title : '이름를 입력하세요.',
				  didClose: () => {
						f.m_name.value = '';
						f.m_name.focus();
				  }
			})
			
			return;
		}
		

		
		//아이디 체크
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
		
		
		//사용할 수 없는 아이디로 가입을 시도 했을때
		if(!send_id){
			Swal.fire({
				 icon	: 'error',
				 title  : '사용할수 없는 아이디입니다.',
				 text   : '아이디를 변경해 주세요.',
				  didClose: () => {
						f.m_pwd.value = '';
						f.m_pwd.focus();
				  }
			})
			
			return;
		}
		
		
		//비번체크
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
		
		//닉네임체크
		if (m_nickname == '') {
			Swal.fire({
				 icon	: 'error',
				  title : '닉네임을 입력하세요.',
				  didClose: () => {
						f.m_nickname.value = '';
						f.m_nickname.focus();
				  }
			})
			
			return;
		}
		//사용할 수 없는 닉네임으로 가입을 시도 했을때
		if(!send_nickname){
			Swal.fire({
				 icon	: 'error',
				 title  : '사용할 수 없는 닉네임입니다.',
				 text   : '닉네임를 변경해 주세요.',
				  didClose: () => {
						f.m_nickname.value = '';
						f.m_nickname.focus();
				  }
			})
			
			return;
		}
		
		
		
		f.action = 'insert.do';
		
		f.submit();
		

	}
		
</script>

<script type="text/javascript">
	
	var send_id = false;
	var send_nickname = false;
	
	
	var regular_id = /^[a-zA-Z0-9]{3,15}$/;
	var regular_nickname = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{1,15}$/;
	
	$(function(){
		
		$("#m_id").keyup(function(e){
		
			var m_id = $(this).val();			
			
			console.log(m_id);
			
			if( !regular_id.test(m_id) ){
				
				$("#id_msg").html("영문자, 숫자 3~15자리만 입력가능 합니다.").css("color","red");
				send_id = false;
				return;
			}
			
			$.ajax({
				
				url		 :'check_id.do',
				type	 :'GET',
				data	 :{'m_id' : m_id },
				dataType : 'json',
				success  : function(result){
					
					if(result.res){
						
						$("#id_msg").html("사용가능한 ID입니다.").css("color","blue");
						
						send_id = true;
						
						/* console.log(send_id); */
						
					}else{
						
						$("#id_msg").html("이미 사용중인 ID입니다.").css("color","red");
						
						send_id = false;
						
						/* console.log(send_id); */
					
					}
					
				},
				error    : function(err){
					alert(err.responseText);
				}
				
			})

			
		});
		
	});

</script>

<script>

$(function(){
	
	
	$("#m_nickname").keyup(function(e){
	
		var m_nickname = $(this).val();			
		
		console.log(m_nickname);
		
		if( !regular_nickname.test(m_nickname) ){
			
			$("#nickname_msg").html("닉네임은 15자이상 사용불가").css("color","red");
			send_nickname = false;
			return;
		}
		
		$.ajax({
			
			url		 :'check_nickname.do',
			type	 :'GET',
			data	 :{'m_nickname' : m_nickname },
			dataType : 'json',
			success  : function(result){
				
				if(result.res){
					
					$("#nickname_msg").html("사용가능한 닉네임입니다.").css("color","blue");
					
					send_nickname = true;
					
				}else{
					
					$("#nickname_msg").html("이미 사용중인 닉네임입니다.").css("color","red");
				
					send_nickname = false;
				
				}
				
			},
			error    : function(err){
				alert(err.responseText);
			}
			
		})
		
		
		
		
		
	});
	
}); 

</script>


	




</head>
<body>
<form>
	<div id="box">
		<table id="t1">
			<tr>
				<th>이름</th>		
				<td><input type="text" name="m_name" placeholder="이름 입력"></td>	
			</tr>
			
			<tr>
				<th>ID</th>
				<td><input type="text" name="m_id" id="m_id" placeholder="ID 입력"> </td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align: center;"><span id="id_msg" ></span> </td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="m_pwd" placeholder="비밀번호 입력"> </td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="m_nickname" id="m_nickname" placeholder="닉네임 입력"> </td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align: center;"><span id="nickname_msg"></span> </td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="button" onclick="sign_up(this.form);" value="가입">
					<input type="button" onclick="location.href='../index.html'" value="취소">
				</th>
			</tr>
			
		</table>
	</div>
</form>
</body>
</html>