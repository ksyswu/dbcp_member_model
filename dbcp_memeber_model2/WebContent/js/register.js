/**
 * joinForm.jsp 검증 = validate (plugin 사용)
 */
$(function() {
	$("#register").validate({
		errorPlacement:function(error,element){
			$(element).closest("form")
			.find("small[id='"+element.attr("id")+"']")
			.append(error);
		},
		rules:{
			userid:{
				required:true,
				validId:true,
				remote:{ //ajax
					url:"../checkId.do",
					type:"post",
					data:{
						userid:function(){
							return $("#userid").val();
						}
					}
				}
			},
			password:{
				required:true,
				validPwd:true,
				validpwdLetters:true
			},
			confirm_password:{
				required:true,
				validPwd:true,
				validpwdLetters:true,
				equalTo:"#password"
			},
			name:{
				required:true,
				minlength:2
			},
			gender:{
				required:true
			},
			email:{
				required:true,
				validEmail:true
			}
		},
		messages:{
			userid:{
				required:"아이디는 필수 입니다,",
				remote:"아이디가 중복되었습니다."
			},
			password:{
				required:"비밀번호는 필수 입니다."
			},
			confirm_password:{
				required:"비밀번호는 필수 입니다.",
				equalTo:"비밀번호가 일치 하지 않습니다."
			},
			name:{
				required:"이름은 필수 입니다.",
				minlenght:"이름은 최소 2자리이상입니다."
			},
			gender:"성별은 필수 입니다.",
			email:{
				required:"이메일은 필수 입니다."
			}
		}
	});
});

//검증메소드 추가
$.validator.addMethod("validId",function(value){
	var regId=/^(?=.*?[A-Za-z])(?=.*?[0-9]).{5,12}$/;
	return regId.test(value);
},"아이디는  (대소문자 상관 없음)영문자와 숫자를 포함해서 5~12자 입력.");

$.validator.addMethod("validPwd",function(value){
	var regPwd=/^(?=.*\d)(?=.*?[A-Za-z])(?=.*?[#?!@$%^&*-]).{8,15}$/;
	return regPwd.test(value);
},"비밀번호는 (대소문자 상관 없음)문자,숫자,특수문자 8~15");

$.validator.addMethod("validpwdLetters",function(value){
	var regPwd=/(\w)\1\1\1/;
	return !regPwd.test(value);
},"동일한 숫자와 문자를 연속으로 사용할 수 없습니다.");

$.validator.addMethod("validEmail",function(value){
	var regEmail = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regEmail.test(value);
},"이메일 형식을 확인해 주세요");

